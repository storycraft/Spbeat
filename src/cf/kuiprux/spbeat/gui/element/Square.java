package cf.kuiprux.spbeat.gui.element;

import org.newdawn.slick.geom.RoundedRectangle;

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
	protected org.newdawn.slick.geom.Shape getShape() {
		return new RoundedRectangle(getX(), getY(), getWidth(), getHeight(), getCornerRadius());
	}
}
