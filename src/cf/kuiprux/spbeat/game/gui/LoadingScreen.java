package cf.kuiprux.spbeat.game.gui;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Color;

import cf.kuiprux.spbeat.game.gui.ButtonPanel.ButtonArea;
import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.element.Square;

public class LoadingScreen extends ScreenPreset {

	@Override
	public void onPress(int keyIndex) {
		
	}

	@Override
	public void onUp(int keyIndex) {
		
	}

	@Override
	protected void onLoad() {
		getButtonPanel().getBackground().setColor(Color.green);
		
		int i = 0;
		for (ButtonArea area : getButtonPanel().getButtonAreaList()) {
			i++;

			Square square = new Square(0, 0, 101, 101);
			
			square.setColor(Color.blue);
			square.setOpacity(0);
			square.setOrigin(AlignMode.CENTRE);
			
			//»÷∏Æ∏Ø
			square.fadeIn(EasingType.LINEAR, i * 500).fadeOut(EasingType.LINEAR, 1000).expire();
			
			area.addChild(square);
		}
		
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				getScreenManager().setCurrentScreen(new BeatmapSelectScreen());
			}
		}, i * 500 + 1000);
	}

	@Override
	protected void onUnload() {
		
	}

}
