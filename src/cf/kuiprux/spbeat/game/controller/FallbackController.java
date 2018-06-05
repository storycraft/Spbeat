package cf.kuiprux.spbeat.game.controller;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

//테스트용 키보드 컨트롤러
public class FallbackController extends GameController {
	
	private static Map<Integer, Integer> convertMap;
	
	static {
		convertMap = new HashMap<>();
		
		convertMap.put(2, 0); convertMap.put(3, 1); convertMap.put(4, 2); convertMap.put(5, 3);
		convertMap.put(16, 4); convertMap.put(17, 5); convertMap.put(18, 6); convertMap.put(19, 7);
		convertMap.put(30, 8); convertMap.put(31, 9); convertMap.put(32, 10); convertMap.put(33, 11);
		convertMap.put(44, 12); convertMap.put(45, 13); convertMap.put(46, 14); convertMap.put(47, 15);
	}

	@Override
	protected void updateLoop() {
		while (Keyboard.next()) {
			boolean pressed = Keyboard.getEventKeyState();
			
			convertAndCallEvent(Keyboard.getEventKey(), pressed);
	    }
	}
	
	private void convertAndCallEvent(int charKey, boolean pressed) {
		if (!convertMap.containsKey(charKey))
			return;
		
		int key = convertMap.get(charKey);
		
		if (pressed)
			callPressEvent(key);
		else
			callUpEvent(key);
	}
}
