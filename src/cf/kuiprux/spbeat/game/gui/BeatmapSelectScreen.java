package cf.kuiprux.spbeat.game.gui;

import org.newdawn.slick.Color;

import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.element.Square;

public class BeatmapSelectScreen extends ScreenPreset {
	
	private int selectedIndex;
	private boolean selected;
	private Square selectHighlight;

	public BeatmapSelectScreen() {
		this.selectHighlight = new Square(0, 0, 100, 100);
		selectHighlight.setBorderColor(Color.red);
		selectHighlight.setBorderWidth(3);
		selectHighlight.setVisible(false);
		
		this.selected = false;
		this.selectedIndex = 0;
	}

	@Override
	public void onPress(int keyIndex) {
		int y = keyIndex / 4;
		int x = keyIndex % 4;
		
		//맨 마지막 줄엔 비트맵을 표시 하지 않음
		if (y > 2)
			return;
		
		if (!this.selected)
			this.selected = true;
		this.selectedIndex = keyIndex;
		
		selectHighlight.setVisible(true);
		
		getButtonPanel().addChild(selectHighlight);
		selectHighlight.setLocation(getButtonPanel().getButtonPosX(x), getButtonPanel().getButtonPosY(y));
		//selectHighlight.moveTo(getButtonPanel().getButtonPosX(x), getButtonPanel().getButtonPosY(y), EasingType.LINEAR, 100);
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public int getSelectedIndex() {
		return selectedIndex;
	}

	@Override
	public void onUp(int keyIndex) {
		
	}

	@Override
	protected void onLoad() {
		getButtonPanel().getBackground().setColor(Color.cyan);
	}

	@Override
	protected void onUnload() {
		
		
	}

}
