package cf.kuiprux.spbeat.gui;

import org.newdawn.slick.Color;

public interface IHasColor {
	Color getColor();
	Color getBorderColor();
	float getBorderWidth();
	
	void setColor(Color color);
	void setBorderColor(Color color);
	void setBorderWidth(float width);
}
