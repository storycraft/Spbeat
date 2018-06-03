package cf.kuiprux.spbeat.gui.containers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import cf.kuiprux.spbeat.gui.Container;
import cf.kuiprux.spbeat.gui.Drawable;

public class SimpleContainer extends Container {
	
	private Rectangle cachedRectangle;
	
	public SimpleContainer() {
		this.cachedRectangle = new Rectangle(0, 0, 0, 0);
	}

	@Override
	protected void updateInternal(int delta) {
		
	}

	@Override
	protected void drawInternal(Graphics graphics) {
		
	}
	
	@Override
	protected void addInternal(Drawable drawable) {
		super.addInternal(drawable);
		
		updateChildShape();
	}
	
	@Override
	protected void removeInternal(Drawable drawable) {
		super.removeInternal(drawable);
		
		updateChildShape();
	}
	
	@Override
	public void onLoaded() {
		super.onLoaded();
		
		updateChildShape();
	}
	
	@Override
	protected void onChildUpdate(Drawable child) {
		updateChildShape();
	}
	
	protected void updateChildShape() {
		float minX = Float.MAX_VALUE;
		float minY = Float.MAX_VALUE;
		float maxX = 0;
		float maxY = 0;
		
		for (Drawable shape : getChildrenInternal()) {
			Rectangle boundingBox = shape.getBoundingBox();
			
			minX = Math.min(boundingBox.getX(), minX);
			minY = Math.min(boundingBox.getY(), minY);
			maxX = Math.max(boundingBox.getX() + boundingBox.getWidth(), maxX);
			maxY = Math.max(boundingBox.getY() + boundingBox.getHeight(), maxY);
		}
		
		cachedRectangle = new Rectangle(getX() + minX, getY() + minY, maxX - minX, maxY - minY);
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return cachedRectangle;
	}

	@Override
	public float getWidth() {
		return cachedRectangle.getWidth();
	}

	@Override
	public float getHeight() {
		return cachedRectangle.getHeight();
	}

}
