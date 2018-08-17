package cf.kuiprux.spbeat.gui.container;

import cf.kuiprux.spbeat.SimpleGame;
import cf.kuiprux.spbeat.gui.Drawable;
import cf.kuiprux.spbeat.gui.IDrawable;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Container extends AbstractDrawableContainer<IDrawable> {
	
	private SimpleGame game;
	
	public Container() {

	}

	@Override
	public SimpleGame getGame() {
		return game;
	}

	@Override
	public void init(SimpleGame game) {
		this.game = game;
	}

	@Override
	public void update(int delta) {
		for (IDrawable child : getChildren())
			child.update(delta);
		
		updateInternal(delta);
	}
	
	private void drawChild(Batch batch, float x, float y, float width, float height , IDrawable child) {
		//안보일경우 렌더링 x
		if (!child.isVisible())
			return;

		child.draw(batch, x, y, width, height);
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		applyProperties(batch);
		applyTransform(batch);

		drawInternal(batch, x, y, width, height);

		applyTransform(batch);

		boolean isMasking = isMasking();

		for (IDrawable child : getChildren())
			drawChild(batch, x, y, width, height, child);
	}
	
	
	/*
	 * 이벤트 구역 시작
	 */
	
	@Override
	public void onAdded(Container container) {
		super.onAdded(container);
	}
	
	@Override
	public void onLoaded() {
		super.onLoaded();
		
		for (IDrawable child : getChildren())
			child.onLoaded();
	}
	
	@Override
	public void onUnloaded() {
		super.onUnloaded();
		
		for (IDrawable child : getChildren())
			child.onUnloaded();
	}
	
	public void onChildUpdate(Drawable child) {
		
	}
	
	/*
	 * 이벤트 구역 끝
	 */
	
	//자식 추가가 되었을시 true 반환
	public boolean addChild(IDrawable drawable) {
		if (containsChild(drawable))
			return false;
		
		addInternal(drawable);
		return true;
	}
	
	//자식 제거 되었을시 true 반환
	public boolean removeChild(IDrawable drawable) {
		if (!containsChild(drawable))
			return false;
		
		removeInternal(drawable);
		return true;
	}
	
	protected void addInternal(IDrawable drawable) {
		getChildrenInternal().add(drawable);
		drawable.onAdded(this);
		
		if (drawable instanceof Container) {
			((Container) drawable).init(getGame());
		}
		
		if (isLoaded())
			drawable.onLoaded();
	}
	
	protected void removeInternal(IDrawable drawable) {
		getChildrenInternal().remove(drawable);
		drawable.onRemoved();
		
		if (isLoaded())
			drawable.onUnloaded();
	}
	
	public boolean containsChild(IDrawable drawable) {
		return getChildrenInternal().contains(drawable);
	}
	
	//해당 container update 메서드
	protected abstract void updateInternal(int delta);
	
	//해당 container draw 메서드
	protected abstract void drawInternal(Batch batch, float x, float y, float width, float height);
}
