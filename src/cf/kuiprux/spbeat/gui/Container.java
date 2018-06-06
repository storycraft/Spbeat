package cf.kuiprux.spbeat.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import javafx.scene.shape.Polygon;

public abstract class Container extends Drawable {
	
	private Game game;
	
	private boolean masking;
	
	private List<Drawable> children;
	
	public Container() {
		this.children = new ArrayList<>();
		this.masking = false;
	}

	protected void init(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
	
	public boolean isMasking() {
		return masking;
	}
	
	public void setMasking(boolean flag) {
		this.masking = flag;
	}
	
	//복사본
	//concurrent 발생 가능한 작업에 사용
	public List<Drawable> getChildren(){
		return new ArrayList<>(children);
	}
	
	//원본
	protected List<Drawable> getChildrenInternal(){
		return children;
	}
	
	@Override
	public void update(int delta) {
		for (Drawable child : getChildren())
			child.update(delta);
		
		updateInternal(delta);
	}
	
	private void drawChild(Graphics graphics, Drawable child) {
		//안보일경우 렌더링 x
		if (!child.isVisible())
			return;
		
		graphics.pushTransform();
		
		child.draw(graphics);
		
		graphics.popTransform();
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.pushTransform();
		
		applyProperties(graphics);
		applyTransform(graphics);
		drawInternal(graphics);
		
		graphics.popTransform();
		
		graphics.pushTransform();
		
		applyTransform(graphics);
		
		if (isMasking())
			graphics.setWorldClip(getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight());
		
		for (Drawable child : getChildren())
			drawChild(graphics, child);
		
		graphics.popTransform();
	}
	
	
	/*
	 * 이벤트 구역 시작
	 */
	
	@Override
	protected void onAdded(Container container) {
		super.onAdded(container);
	}
	
	@Override
	protected void onLoaded() {
		super.onLoaded();
		
		for (Drawable child : getChildren())
			child.onLoaded();
	}
	
	@Override
	protected void onUnloaded() {
		super.onUnloaded();
		
		for (Drawable child : getChildren())
			child.onUnloaded();
	}
	
	protected void onChildUpdate(Drawable child) {
		
	}
	
	/*
	 * 이벤트 구역 끝
	 */
	
	//자식 추가가 되었을시 true 반환
	public boolean addChild(Drawable drawable) {
		if (containsChild(drawable))
			return false;
		
		addInternal(drawable);
		return true;
	}
	
	//자식 제거 되었을시 true 반환
	public boolean removeChild(Drawable drawable) {
		if (!containsChild(drawable))
			return false;
		
		removeInternal(drawable);
		return true;
	}
	
	protected void addInternal(Drawable drawable) {
		getChildrenInternal().add(drawable);
		drawable.onAdded(this);
		
		if (drawable instanceof Container) {
			((Container) drawable).init(getGame());
		}
		
		if (isLoaded())
			drawable.onLoaded();
	}
	
	protected void removeInternal(Drawable drawable) {
		getChildrenInternal().remove(drawable);
		drawable.onRemoved();
		
		if (isLoaded())
			drawable.onUnloaded();
	}
	
	public boolean containsChild(Drawable drawable) {
		return getChildrenInternal().contains(drawable);
	}
	
	//해당 container update 메서드
	protected abstract void updateInternal(int delta);
	
	//해당 container draw 메서드
	protected abstract void drawInternal(Graphics graphics);
}
