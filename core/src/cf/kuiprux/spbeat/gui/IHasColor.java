package cf.kuiprux.spbeat.gui;

import com.badlogic.gdx.graphics.Color;

public interface IHasColor {
	Color getColor();
	Color getBorderColor();
	float getBorderWidth();
	
	void setColor(Color color);
	void setBorderColor(Color color);
	void setBorderWidth(float width);
}
