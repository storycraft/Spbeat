package cf.kuiprux.spbeat.gui.element;

import cf.kuiprux.spbeat.gui.Drawable;
import cf.kuiprux.spbeat.gui.IHasColor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Sprite extends Drawable implements IHasColor {

	private Color color;
	private Color borderColor;
	
	private float borderWidth;
	
	public Sprite() {
		this.color = Color.CLEAR;
		this.borderColor = Color.CLEAR;
		
		this.borderWidth = 0;
	}

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

	@Override
	protected void applyProperties(Batch batch) {
		batch.setColor(getColor().mul(new Color(1, 1, 1, (getOpacity()))));
	}
}
