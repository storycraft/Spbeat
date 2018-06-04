package cf.kuiprux.spbeat.gui.element;

import org.newdawn.slick.geom.Polygon;

public class Triangle extends Shape {
	
	public Triangle() {
		this(0, 0, 0, 0);
	}
	
	public Triangle(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public Triangle(float x, float y) {
		this(x, y, 0, 0);
	}
	
	@Override
	protected org.newdawn.slick.geom.Shape getShape() {
		Polygon triangle = new Polygon();
		
		triangle.addPoint(getDrawX() + getDrawWidth() / 2, getDrawY());
		triangle.addPoint(getDrawX(), getDrawY() + getDrawHeight());
		triangle.addPoint(getDrawX() + getDrawWidth(), getDrawY() + getDrawHeight());
		triangle.setClosed(true);
		
		return triangle;
	}
}
