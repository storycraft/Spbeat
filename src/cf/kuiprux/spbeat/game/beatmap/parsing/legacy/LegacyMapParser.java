package cf.kuiprux.spbeat.game.beatmap.parsing.legacy;

import java.lang.reflect.Type;
import java.util.*;

import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.beatmap.Note;

public class LegacyMapParser {
	
	public static final char BLANK = '□';
	public static final char BEAT_SEPARATOR = '-';
	public static final char LOCAL_VARIABLE = '*';
	public static final char[] NUMBERS = { '①', '②', '③', '④', '⑤', '⑥', '⑦', '⑧', '⑨', '⑩', '⑪', '⑫', '⑬', '⑭', '⑮', '?' };
	
	private static final Map<String, Type> parseMap; 
	
	static {
		parseMap = new HashMap<>();

		//bpm
		parseMap.put("t", Float.class);
		//song path
		parseMap.put("m", String.class);
		//sync
		parseMap.put("r", String.class);
		//title
		parseMap.put("#title", String.class);
		//artist
		parseMap.put("#artist", String.class);
		//jacket
		parseMap.put("#jacket", String.class);
	}
	
	public LegacyMapParser() {
		
	}
	
	public Beatmap parseRawMap(String rawText) throws Exception {
		LegacyMapLexer lexer = new LegacyMapLexer();

		List<List<LegacyMapLexer.Token>> tokenLineList = lexer.separateRawMap(rawText);

		Map<String, String> optionMap = new HashMap<>();
		List<Note> noteList = new ArrayList<>();

		LegacyMapLexer.Token lastToken = null;

		int noteLine = 0;
		int noteIndex = 0;
		for (int line = 0; line < tokenLineList.size(); line++){
			List<LegacyMapLexer.Token> tokenList = tokenLineList.get(line);
			Map<String, Float> timingVarList = new HashMap<>();
			noteIndex = 0;

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
						optionMap.put(lastToken.getValue(), nextToken.getValue());
					}
					else{
						throw new Exception("option parsing failed at line " + line);
					}
				}

				//로컬 변수 처리
				else if (token.getTokenType() == LegacyMapLexer.TokenType.LOCAL_VARIABLE_EQUALS){
					if (lastToken != null && nextToken != null && nextToken.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER && lastToken.getTokenType() == LegacyMapLexer.TokenType.IDENTIFIER){
						timingVarList.put(nextToken.getValue());
					}
				}

				//비트 나누기
				else if (token.getTokenType() == LegacyMapLexer.TokenType.BEAT_SEPARATOR){
					noteLine++;
					break;
				}

				//시간 & 빈문자 처리
				else if (token.getTokenType() == LegacyMapLexer.TokenType.BLANK || token.getTokenType() == LegacyMapLexer.TokenType.TIME_CHARACTER
						|| token.getTokenType() == LegacyMapLexer.TokenType.HOLD_SLIDER)

				lastToken = token;
			}
		}

		String title = optionMap.getOrDefault("#title", "Untitled");
		String artist = optionMap.getOrDefault("#artist", "");
		String songPath = optionMap.getOrDefault("m", null);
		String jacketPath = optionMap.getOrDefault("#jacket", "");
		float diff = Float.parseFloat(optionMap.getOrDefault("#dif", "0"));
		float bpm = Float.parseFloat(optionMap.getOrDefault("t", "0"));

		if (songPath == null)
			throw new Exception("Song path is not exist");

		//debug
		/*
		for (String key : optionMap.keySet()){
			System.out.println(key + " : " + optionMap.get(key));
		}
		*/

		//TODO
		Beatmap map = new Beatmap(title, songPath, jacketPath, bpm, diff, null);

		return map;
		
	}
}
