package cf.kuiprux.spbeat.game.gui;

import cf.kuiprux.spbeat.SpBeAt;
import cf.kuiprux.spbeat.game.controller.IControllerListener;
import cf.kuiprux.spbeat.game.gui.ScreenPreset;

public class ScreenManager implements IControllerListener {
	
	private ScreenPreset currentScreen;
	
	private SpBeAt game;
	
	public ScreenManager(SpBeAt game) {
		this.game = game;
	}
	
	public SpBeAt getGame() {
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
	public void onUp(int keyIndex) {
		if (getCurrentScreen() == null)
			return;
		
		currentScreen.onUp(keyIndex);
	}

	public void update(int delta) {
		if (getCurrentScreen() != null) {
			getCurrentScreen().update(delta);
		}
	}
}
