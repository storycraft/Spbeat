package cf.kuiprux.spbeat.gui.element;

import cf.kuiprux.spbeat.gui.container.IContainerDrawable;
import com.badlogic.gdx.math.Rectangle;

public class Square extends Shape {
	
	private int cornerRadius;
	
	public Square() {
		this(0, 0, 0, 0);
	}
	
	public Square(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.cornerRadius = 0;
	}
	
	public Square(float x, float y) {
		this(x, y, 0, 0);
	}
	
	public int getCornerRadius() {
		return cornerRadius;
	}
	
	public void setCornerRadius(int radius) {
		this.cornerRadius = radius;
	}

	@Override
	protected Rectangle getShape() {
		return new Rectangle(getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight());
	}

	@Override
	public void onAdded(IContainerDrawable container) {

	}
}
