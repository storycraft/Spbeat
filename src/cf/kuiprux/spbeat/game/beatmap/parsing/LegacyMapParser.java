package cf.kuiprux.spbeat.game.beatmap.parsing;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import cf.kuiprux.spbeat.game.beatmap.Beatmap;

public class LegacyMapParser {
	
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
		for (String line : rawText.split("\n")) {
			
		}
		
		return null;
	}
}
