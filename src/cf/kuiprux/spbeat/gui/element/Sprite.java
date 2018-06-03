package cf.kuiprux.spbeat.gui.element;

import org.newdawn.slick.Color;

import cf.kuiprux.spbeat.gui.Drawable;
import cf.kuiprux.spbeat.gui.IHasColor;

public abstract class Sprite extends Drawable implements IHasColor{

	private Color color;
	private Color borderColor;
	
	private float borderWidth;

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		if (color == null)
			return;
		
		this.color = color;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}
	
	public float getBorderWidth() {
		return borderWidth;
	}
	
	public void setBorderColor(Color color) {
		if (color == null)
			return;
		
		this.borderColor = color;
	}
	
	public void setBorderWidth(float width) {
		this.borderWidth = width;
	}

}
