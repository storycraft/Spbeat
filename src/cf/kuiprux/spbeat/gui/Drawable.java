package cf.kuiprux.spbeat.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

public abstract class Drawable {
	
	private float x;
	private float y;
	
	//0 ~ 1
	private float opacity;
	
	private Container parent;
	
	private boolean loaded;
	
	public Drawable() {
		this.loaded = false;
		
		this.x = 0;
		this.y = 0;
		
		this.opacity = 1;
	}
	
	/*
	 * getter / setter 구역 시작
	 */
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getOpacity() {
		return opacity;
	}
	
	public void setX(float x) {
		this.x = x;
		
		sendParentUpdate();
	}
	
	public void setY(float y) {
		this.y = y;
		
		sendParentUpdate();
	}
	
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
		
		sendParentUpdate();
	}
	
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
	
	public abstract float getWidth();
	public abstract float getHeight();
	/*
	 * 그리기용 이므로 width / height setter는 따로 선언할 필요가 없게함
	 * 필요 할 경우 따로 선언
	 */
	
	public abstract Rectangle getBoundingBox();
	
	public void sendParentUpdate() {
		if (isLoaded())
			getParent().onChildUpdate(this);
	}
	
	public Container getParent() {
		return parent;
	}
	
	public boolean isLoaded() {
		return loaded;
	}
	
	//Parent Container에서 제거
	public void expire() {
		if (getParent() == null)
			return;
		
		getParent().removeChild(this);
	}

	//TODO:: 구현
	public Transform computeTransform() {
		return Transform.createTranslateTransform(0, 0);
	}
	
	/*
	 * getter 구역 끝
	 */
	
	/*
	 * 이벤트 구역 시작
	 */
	
	//container에 자식으로 넣어졌을시 호출
	protected void onAdded(Container container) {
		//다른 parent에 소속되어 있을시 제거
		if (parent != null)
			expire();
		
		parent = container;
	}
	
	//해당 Drawable이 로드(update와 draw가 호출 될 수 있는 상태) 될때
	protected void onLoaded() {
		loaded = true;
	}
	
	//해당 Drawable이 언 로드(update와 draw가 호출 될 수 없는 상태) 될때
	protected void onUnloaded() {
		loaded = false;
	}
	
	//container에서 제거 되었을 시 호출
	protected void onRemoved() {
		parent = null;
	}
	
	/*
	 * 이벤트 구역 끝
	 */
	
	public abstract void update(int delta);
	
	public abstract void draw(Graphics graphics);
}
