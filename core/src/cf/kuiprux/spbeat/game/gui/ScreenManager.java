package cf.kuiprux.spbeat.game.gui;

import cf.kuiprux.spbeat.SpBeat;
import cf.kuiprux.spbeat.game.controller.IControllerListener;

public class ScreenManager implements IControllerListener {
	
	private ScreenPreset currentScreen;
	
	private SpBeat game;
	
	public ScreenManager(SpBeat game) {
		this.game = game;
	}
	
	public SpBeat getGame() {
		return game;
	}

	public ScreenPreset getCurrentScreen() {
		return currentScreen;
	}

	public void setCurrentScreen(ScreenPreset currentScreen) {
		if (this.currentScreen != null)
			this.currentScreen.unload();
		
		this.currentScreen = currentScreen;
		currentScreen.load(this);
	}

	@Override
	public void onPress(int keyIndex) {
		if (getCurrentScreen() == null)
			return;
		
		currentScreen.onPress(keyIndex);
	}

	@Override
	public void onRelease(int keyIndex) {
		if (getCurrentScreen() == null)
			return;
		
		currentScreen.onRelease(keyIndex);
	}

	public void update(int delta) {
		if (getCurrentScreen() != null) {
			getCurrentScreen().update(delta);
		}
	}
}
