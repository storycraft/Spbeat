package cf.kuiprux.spbeat.game;

import org.newdawn.slick.Color;

import cf.kuiprux.spbeat.gui.Drawable;
import cf.kuiprux.spbeat.gui.containers.SimpleContainer;
import cf.kuiprux.spbeat.gui.element.Square;

// 4x4 크기의 사각형 버튼 셋
public class ButtonPanel extends SimpleContainer {
	
	public static final int BUTTON_WIDTH = 100;
	public static final int BUTTON_HEIGHT = 100;
	
	public static final int BUTTON_GAP_X = 10;
	public static final int BUTTON_GAP_Y = 10;
	
	public ButtonPanel() {
		initPanel();
	}
	
	
	// !! hardcoded !!
	private void initPanel() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				addInternal(new PanelButton((BUTTON_WIDTH + BUTTON_GAP_X) * x, (BUTTON_HEIGHT + BUTTON_GAP_Y) * y, BUTTON_WIDTH, BUTTON_HEIGHT));
			}
		}
		
		setLocation(85.5f, 285.5f);
	}
	
	@Override
	public boolean addChild(Drawable drawable) {
		//외부에서 자식 추가 불가능
		return false;
	}
	
	@Override
	public boolean removeChild(Drawable drawable) {
		//외부에서 자식 제거 불가능
		return false;
	}
	
	public PanelButton getButtonAt(int index) {
		return (PanelButton) getChildrenInternal().get(index);
	}
	
	@Override
	protected void updateInternal(int delta) {
		//Test Code XD
		getButtonAt((int) (Math.random() * 16)).setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255),(int) (Math.random() * 255) ,255));
	}
	
	private class PanelButton extends Square {
		public PanelButton(int x, int y, int width, int height) {
			super(x, y, width, height);

			setCornerRadius(10);
			
			//Test Code here too
			setBorderColor(Color.yellow);
			setBorderWidth(5f);
		}
	}
}
