package cf.kuiprux.spbeat.game.gui;

import org.newdawn.slick.Color;

import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.element.Square;

public class BeatmapSelectScreen extends ScreenPreset {
	
	private Square selectHighlight;

	public BeatmapSelectScreen() {
		this.selectHighlight = new Square(0, 0, 98, 98);
		selectHighlight.setBorderColor(Color.red);
		selectHighlight.setBorderWidth(3);
		selectHighlight.setVisible(false);
	}

	@Override
	public void onPress(int keyIndex) {
		int y = keyIndex / 4;
		int x = keyIndex % 4;
		
		selectHighlight.setVisible(true);
		
		getButtonPanel().addChild(selectHighlight);
		selectHighlight.moveTo(getButtonPanel().getButtonPosX(x), getButtonPanel().getButtonPosX(y), EasingType.LINEAR, 250);
	}

	@Override
	public void onUp(int keyIndex) {
		
	}

	@Override
	protected void onLoad() {
		
	}

	@Override
	protected void onUnload() {
		
		
	}

}
