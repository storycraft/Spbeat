package cf.kuiprux.spbeat.game.beatmap.parsing.legacy;

import java.util.*;

import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.beatmap.HoldNote;
import cf.kuiprux.spbeat.game.beatmap.INote;
import cf.kuiprux.spbeat.game.beatmap.Note;

public class LegacyMapParser {
	
	public static final char BLANK = '□';
	public static final char BEAT_SEPARATOR = '-';
	public static final char LOCAL_VARIABLE = '*';
	//이클립스가 이래서 위험합니다. euc-kr 거름, shift-jis 도 거름
	public static final char[] NUMBERS = { '①', '②', '③', '④', '⑤', '⑥', '⑦', '⑧', '⑨', '⑩', '⑪', '⑫', '⑬', '⑭', '⑮', 9327 };

	public static final int NOTE_ROW = 4;//한 줄당 노트 수
	public static final int NOTE_COLUMN = 4;//한 마디당 줄 수
	public static final int NOTE_COUNT = NOTE_ROW * NOTE_COLUMN;//한 배치당 노트 수

	public LegacyMapParser() {
		
	}
	
	public Beatmap parseRawMap(String rawText) throws Exception {
		LegacyMapLexer lexer = new LegacyMapLexer();

		List<List<LegacyMapLexer.Token>> tokenLineList = lexer.separateRawMap(rawText);

		Map<String, String> optionMap = new HashMap<>();
		List<INote> noteList = new ArrayList<>();

		LegacyMapLexer.Token lastToken = null;

		int noteLine = 0;
		int noteRow = 0;
		int noteIndex = 0;
		for (int line = 0; line < tokenLineList.size(); line++){
			List<LegacyMapLexer.Token> tokenList = tokenLineList.get(line);
			Map<String, Float> timingVarMap = new HashMap<>();
			noteRow = 0;

			for (int i = 0; i < tokenList.size(); i++){
				LegacyMapLexer.Token token = tokenList.get(i);
				LegacyMapLexer.Token nextToken = null;

				if (i + 1 < tokenList.size())
					nextToken = tokenList.get(i + 1);

				//주석 제외
				if (token.getTokenType() == LegacyMapLexer.TokenType.ANNOTATION)
					break;

				//옵션 파싱
				else if (token.getTokenType() == LegacyMapLexer.TokenType.EQUALS){
					if (lastToken != null && nextToken != null && lastToken.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER
							&& nextToken.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER){
						if (optionMap.containsKey(lastToken.getValue()))
							optionMap.replace(lastToken.getValue(), nextToken.getValue());
						else
							optionMap.put(lastToken.getValue(), nextToken.getValue());
					}
					else{
						throw new Exception("beatmap option parsing failed at line " + line);
					}
				}

				//로컬 변수 처리
				else if (token.getTokenType() == LegacyMapLexer.TokenType.LOCAL_VARIABLE_EQUALS){
					if (lastToken != null && nextToken != null && nextToken.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER && lastToken.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER){
						LegacyMapLexer.Token checkToken = tokenList.get(i - 2);

						if (checkToken == null || checkToken.getTokenType() != LegacyMapLexer.TokenType.LOCAL_VARIABLE)
							throw new Exception("beatmap parsing failed at line " + line + " unknown local variable");
						try {
							timingVarMap.put(lastToken.getValue(), Float.parseFloat(nextToken.getValue()));
						} catch (Exception e){
							throw new Exception("beatmap parsing failed at line " + line + " " + e.getLocalizedMessage());
						}
					}
				}

				//비트 나누기
				else if (token.getTokenType() == LegacyMapLexer.TokenType.BEAT_SEPARATOR){
					noteLine++;
					noteIndex = 0;
				}

				//시간 & 빈문자 처리
				else if (token.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER && timingVarMap.containsKey(token.getValue()) || token.getTokenType() == LegacyMapLexer.TokenType.BLANK || token.getTokenType() == LegacyMapLexer.TokenType.TIME_CHARACTER
						|| token.getTokenType() == LegacyMapLexer.TokenType.HOLD_SLIDER){

					if (token.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER || token.getTokenType() == LegacyMapLexer.TokenType.TIME_CHARACTER){
						float tempo = getCurrentTempo(optionMap);
						float time = getSync(optionMap) + tempo * noteLine;
						if (token.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER){
							time += tempo * (timingVarMap.get(token.getValue()) / NOTE_COUNT);
						}
						else if (token.getTokenType() == LegacyMapLexer.TokenType.TIME_CHARACTER) {
							time += tempo * ((float) getTimingNumber(token) / NOTE_COUNT);
						}

						noteList.add(new Note(noteIndex, time));
					}
					else if (token.getTokenType() == LegacyMapLexer.TokenType.HOLD_SLIDER){
						float tempo = getCurrentTempo(optionMap);
						float startTime = getSync(optionMap) + tempo * noteLine;
						//홀드 마커 방향
						int dx = 0;
						int dy = 0;

						float length = 0;

						switch(token.getValue()){
							case "＞":
								dx++;
								break;

							case "＜":
								dx--;
								break;

							case "∨":
								dy++;
								break;

							case "∧":
								dy--;
								break;

							default:
								throw new Exception("beatmap parsing failed at line " + line + " hold marker is unknown");
						}

						int xOffset = 0;
						int yOffset = 0;

						int yLine = noteIndex / NOTE_COLUMN;
						LegacyMapLexer.Token endToken = null;

						while(endToken == null){
							xOffset += dx;
							yOffset += dy;

							if (noteRow + xOffset >= NOTE_ROW || noteRow + xOffset < 0 || yLine + yOffset < 0 || yLine + yOffset >= NOTE_COLUMN) {
								throw new Exception("beatmap parsing failed at line " + line + " hold marker doesn't have end marker");
							}

							List<LegacyMapLexer.Token> tokenYOffsetList = tokenLineList.get(line + yOffset);

							if (tokenYOffsetList == null){
								throw new Exception("beatmap parsing failed at line " + line + " hold marker doesn't have end marker");
							}

							LegacyMapLexer.Token xOffsetToken = tokenYOffsetList.get(noteRow + xOffset);
							if (xOffsetToken == null){
								throw new Exception("beatmap parsing failed at line " + line + " hold marker doesn't have end marker");
							}

							if (xOffsetToken.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER && timingVarMap.containsKey(token.getValue()) || xOffsetToken.getTokenType() == LegacyMapLexer.TokenType.TIME_CHARACTER) {
								tokenYOffsetList.remove(xOffsetToken);
								tokenYOffsetList.add(noteRow + xOffset, new LegacyMapLexer.Token(LegacyMapLexer.TokenType.BLANK, BLANK + ""));
								endToken = xOffsetToken;
							}
						}

						if (endToken.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER){
							length = tempo * (timingVarMap.get(endToken.getValue()) / NOTE_COUNT);
						}
						else if (endToken.getTokenType() == LegacyMapLexer.TokenType.TIME_CHARACTER) {
							length = tempo * ((float) getTimingNumber(endToken) / NOTE_COUNT);
						}

						noteList.add(new HoldNote(noteIndex, noteIndex + xOffset + yOffset * NOTE_ROW, startTime, startTime + length));

					}

					noteIndex = (noteIndex + 1) % NOTE_COUNT;
					noteRow++;

					if (noteRow > NOTE_ROW){
						throw new Exception("beatmap parsing failed at line " + line + " note in row is over " + NOTE_ROW + "?!");
					}
				}

				lastToken = token;
			}
		}

		String title = getTitle(optionMap);
		String artist = getArtist(optionMap);
		String songPath = getSongPath(optionMap);
		String jacketPath = getJacketPath(optionMap);
		float diff = getDifficulty(optionMap);
		float tempo = getCurrentTempo(optionMap);

		if (songPath == null)
			throw new Exception("Song path is not exist");

		//debug
		/*
		for (String key : optionMap.keySet()){
			System.out.println(key + " : " + optionMap.get(key));
		}
		*/

		/*
		for (INote note : noteList){
			System.out.println(note.toString());
		}
		*/

		//TODO
		Beatmap map = new Beatmap(title, artist, songPath, jacketPath, tempo, diff, noteList);

		return map;
		
	}

	private int getTimingNumber(LegacyMapLexer.Token timeCharactorToken){
		for (int i = 0; i < NUMBERS.length; i++){
			char c = NUMBERS[i];
			if ((c + "").equals(timeCharactorToken.getValue()))
				return i + 1;
		}

		return 0;
	}

	//파싱 도중 바뀔 수 있음
	private float getCurrentTempo(Map<String, String> optionMap){
		return Float.parseFloat(optionMap.getOrDefault("t", "0"));
	}

	private float getSync(Map<String, String> optionMap){
		return Float.parseFloat(optionMap.getOrDefault("r", "0"));
	}

	private float getDifficulty(Map<String, String> optionMap){
		return Float.parseFloat(optionMap.getOrDefault("#dif", "0"));
	}

	private String getSongPath(Map<String, String> optionMap){
		return optionMap.getOrDefault("m", null);
	}

	private String getTitle(Map<String, String> optionMap){
		return optionMap.getOrDefault("#title", "Untitled");
	}

	private String getArtist(Map<String, String> optionMap){
		return optionMap.getOrDefault("#artist", "");
	}

	private String getJacketPath(Map<String, String> optionMap){
		return optionMap.getOrDefault("#jacket", "");
	}
}
