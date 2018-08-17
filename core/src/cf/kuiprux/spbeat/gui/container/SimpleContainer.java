package cf.kuiprux.spbeat.gui.container;

import cf.kuiprux.spbeat.gui.IDrawable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import cf.kuiprux.spbeat.gui.Drawable;

/*
 * 간단한 Container 클래스
 * 
 * 자식 drawable은 해당 컨테이너의 x, y 좌표 뒤로 이동되며, 컨테이너의 크기는 자식 drawable들의 위치, 크기에 따라 자동 조절됨
 */
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
	public void addInternal(IDrawable drawable) {
		super.addInternal(drawable);
		
		updateChildShape();
	}
	
	@Override
	public void removeInternal(IDrawable drawable) {
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
		float minX = getDrawX();
		float minY = getDrawY();
		float maxX = minX;
		float maxY = minY;
		
		for (IDrawable drawable : getChildrenInternal()) {
			Rectangle boundingBox = drawable.getBoundingBox();
			
			minX = Math.min(boundingBox.getX(), minX);
			minY = Math.min(boundingBox.getY(), minY);
			maxX = Math.max(boundingBox.getX() + boundingBox.getWidth(), maxX);
			maxY = Math.max(boundingBox.getY() + boundingBox.getHeight(), maxY);
		}
		
		cachedRectangle = new Rectangle(minX, minY, maxX - minX, maxY - minY);
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
