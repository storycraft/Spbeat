package cf.kuiprux.spbeat.game.controller;

import java.util.ArrayList;
import java.util.List;

public class SpbeatController implements IGameController {
	
	private List<IControllerListener> listenerList;
	private List<Integer> pressedKeyList;
	
	private boolean listening;
	
	//index ¹üÀ§ 0 ~ 15
	public SpbeatController() {
		this.listenerList = new ArrayList<>();
		this.pressedKeyList = new ArrayList<>();
		
		this.listening = false;
	}
	
	public void listen() throws Exception {
		if (isListening()) {
			
		}
	}
	
	public boolean isListening() {
		return listening;
	}

	@Override
	public void simulatePress(int index) {
		
	}

	@Override
	public boolean isPressed(int index) {
		return pressedKeyList.contains(index);
	}

	@Override
	public void addListener(IControllerListener listener) {
		if (containsListener(listener))
			return;
		
		listenerList.add(listener);
	}

	@Override
	public void removeListener(IControllerListener listener) {
		if (!containsListener(listener))
			return;
		
		listenerList.remove(listener);
	}
	
	private void updateLoop() {
		
	}
	
	private void updateFallback() {
		
	}

	@Override
	public boolean containsListener(IControllerListener listener) {
		return listenerList.contains(listener);
	}

}
