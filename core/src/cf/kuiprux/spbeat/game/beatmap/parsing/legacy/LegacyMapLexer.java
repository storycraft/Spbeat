package cf.kuiprux.spbeat.game.beatmap.parsing.legacy;

import java.util.ArrayList;
import java.util.List;

public class LegacyMapLexer {
	
	//줄 별로 잘라서 반환
	public List<List<Token>> separateRawMap(String rawMap) {
		List<List<Token>> tokenList = new ArrayList<>();

		String[] lineList = rawMap.split(System.lineSeparator());
		boolean stringMode = false;
		for (int l = 0; l < lineList.length; l++) {
			String line = lineList[l];

			List<Token> localTokenList = new ArrayList<>();

			char[] array = line.toCharArray();
			String buffer = "";
			for (int i = 0; i < array.length; i++) {
				char c = array[i];
				char lastChar = 0 <= (i - 1) ? array[i - 1] : 0;

				if (c == '"' && lastChar != '\\'){
					stringMode = !stringMode;
					continue;
				}

				//주석 토큰
				if (!stringMode) {
					if (buffer.equals("//")) {
						localTokenList.add(new Token(TokenType.ANNOTATION, buffer));
						buffer = "";
					} else if (c == '=') {
						if (buffer != "") {
							localTokenList.add(new Token(TokenType.IDENTIFIER, buffer));
							buffer = "";
						}
						localTokenList.add(new Token(TokenType.EQUALS, "="));
					} else if (c == LegacyMapParser.LOCAL_VARIABLE) {
						if (buffer != "") {
							localTokenList.add(new Token(TokenType.IDENTIFIER, buffer));
							buffer = "";
						}
						localTokenList.add(new Token(TokenType.LOCAL_VARIABLE, c + ""));
					} else if(c == ':'){
						if (buffer != "") {
							localTokenList.add(new Token(TokenType.IDENTIFIER, buffer));
							buffer = "";
						}
						localTokenList.add(new Token(TokenType.LOCAL_VARIABLE_EQUALS, c + ""));
					} else if (c == LegacyMapParser.BEAT_SEPARATOR) {
						if (buffer != "") {
							localTokenList.add(new Token(TokenType.IDENTIFIER, buffer));
							buffer = "";
						}
						localTokenList.add(new Token(TokenType.BEAT_SEPARATOR, c + ""));
						break; // If there's a beat separator, skip that line. Unless, the beatlist will be added according to this characters' count.
					} else if (c == LegacyMapParser.BLANK) {
						if (buffer != "") {
							localTokenList.add(new Token(TokenType.IDENTIFIER, buffer));
							buffer = "";
						}
						localTokenList.add(new Token(TokenType.BLANK, c + ""));
					} else if (c == '＞' || c == '＜' || c == '∨' || c == '∧') {
						if (buffer != "") {
							localTokenList.add(new Token(TokenType.IDENTIFIER, buffer));
							buffer = "";
						}
						localTokenList.add(new Token(TokenType.HOLD_SLIDER, c + ""));
					} else if (isNumberChar(c)) {
						if (buffer != "") {
							localTokenList.add(new Token(TokenType.IDENTIFIER, buffer));
							buffer = "";
						}
						localTokenList.add(new Token(TokenType.TIME_CHARACTER, c + ""));
					} else {
						buffer += c;
					}
				} else {
					buffer += c;
				}
			}

			//나머지 값 추가
			if (buffer != "") {
				localTokenList.add(new Token(TokenType.IDENTIFIER, buffer));
				buffer = "";
			}

			tokenList.add(localTokenList);
		}

		//debug
		/*
		for (List<Token> tokenLineList : tokenList){
			for (Token token : tokenLineList) {
				System.out.print(token.getTokenType() + " = " + token.getValue() + ", ");
			}
			System.out.print("\n");
		}
		*/

		return tokenList;
	}
	
	private boolean isNumberChar(char c) {
		for (char character : LegacyMapParser.NUMBERS) {
			if (character == c) {
				return true;
			}
		}

		return false;
	}
	
	public static class Token {
		
		private TokenType tokenType;
		private String value;
		
		public Token(TokenType tokenType, String value) {
			this.tokenType = tokenType;
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		public TokenType getTokenType() {
			return tokenType;
		}
		
		@Override
		public String toString() {
			return getTokenType().name() + " = " + value;
		}
	}
	
	public enum TokenType {
		IDENTIFIER, EQUALS, BEAT_SEPARATOR, BLANK, TIME_CHARACTER, ANNOTATION, LOCAL_VARIABLE, HOLD_SLIDER, LOCAL_VARIABLE_EQUALS;
	}
}
