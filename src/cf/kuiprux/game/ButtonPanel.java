package cf.kuiprux.game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Renderable;

import cf.kuiprux.HBWindow;
import cf.kuiprux.Reference;
import cf.kuiprux.game.drawable.ButtonDrawable;

public class ButtonPanel implements Renderable {
	
	private List<ButtonDrawable> buttonList;
	/* 2차원 구조
	 * 
	 * 0  1  2  3
	 * 4  5  6  7
	 * 8  9  10 11
	 * 12 13 14 15
	 */
	
	public ButtonPanel(int count) {
		this.buttonList = new ArrayList<>();
		
		while (count-- > 0) {
			buttonList.add(new ButtonDrawable());
		}
	}
	
	public ButtonDrawable getButtonAt(int index) {
		return buttonList.get(index);
	}

	@Override
	public void draw(float x, float y) {
		for (int xOffset = 0; xOffset < Reference.PANEL_BUTTON_ROW; xOffset++) {
			for (int yOffset = 0; yOffset < Reference.PANEL_BUTTON_COLUMN; yOffset++) {
				int index = xOffset + yOffset;
				
				getButtonAt(index).draw((float) ((xOffset + 1) * Reference.BUTTON_GAP_WIDTH + xOffset * Reference.BUTTON_WIDTH),
						(float) ((yOffset + 1) * Reference.BUTTON_GAP_HEIGHT + yOffset * Reference.BUTTON_HEIGHT));
			}
		}
	}
}
