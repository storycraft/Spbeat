package cf.kuiprux.spbeat.game.gui;

import cf.kuiprux.spbeat.SpBeAt;
import cf.kuiprux.spbeat.game.controller.IControllerListener;

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
		this.currentScreen = currentScreen;
		currentScreen.load(this);
	}

	@Override
	public void onPress(int keyIndex) {
		if (currentScreen == null)
			return;
		
		currentScreen.onPress(keyIndex);
	}

	@Override
	public void onUp(int keyIndex) {
		if (currentScreen == null)
			return;
		
		currentScreen.onUp(keyIndex);
	}
}
