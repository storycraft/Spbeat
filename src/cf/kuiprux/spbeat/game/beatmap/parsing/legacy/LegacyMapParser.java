package cf.kuiprux.spbeat.game.beatmap.parsing.legacy;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import cf.kuiprux.spbeat.game.beatmap.Beatmap;

public class LegacyMapParser {
	
	public static final char BLANK = '¡à';
	public static final char BEAT_SEPARATOR = '-';
	public static final char LOCAL_VARIABLE = '*';
	public static final char[] NUMBERS = { '¨ç', '¨è', '¨é', '¨ê', '¨ë', '¨ì', '¨í', '¨î', '¨ï', '¨ð', '¨ñ', '¨ò', '¨ó', '¨ô', '¨õ', '?' };
	
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
	
	public Beatmap parseRawMap(String rawText) {
		LegacyMapLexer lexer = new LegacyMapLexer();
		return null;
		
	}
}
