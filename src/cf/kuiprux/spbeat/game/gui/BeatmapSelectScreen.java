package cf.kuiprux.spbeat.game.gui;

import org.newdawn.slick.Color;

import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.element.Square;

public class BeatmapSelectScreen extends ScreenPreset {
	
	private Square selectHighlight;

	public BeatmapSelectScreen() {
		this.selectHighlight = new Square(0, 0, 101, 101);
		selectHighlight.setBorderColor(Color.red);
		selectHighlight.setBorderWidth(3);
	}

	@Override
	public void onPress(int keyIndex) {
		
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
