package cf.kuiprux.spbeat;

import cf.kuiprux.spbeat.gui.IDrawable;
import cf.kuiprux.spbeat.gui.container.AbstractDrawableContainer;
import cf.kuiprux.spbeat.gui.container.Container;
import cf.kuiprux.spbeat.gui.container.IContainerDrawable;
import cf.kuiprux.spbeat.gui.font.FontManager;

import cf.kuiprux.spbeat.logging.LogManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleGame extends ApplicationAdapter implements IContainerDrawable<IDrawable> {

	private LogManager logManager;
	private FontManager fontManager;
	private boolean isStarted;

	private List<IDrawable> children;

	private boolean rounded;
	private boolean masked;

	private int width;
	private int height;
	
	private String title;
	
	public SimpleGame(String title) {
		this.isStarted = false;

		this.rounded = false;
		this.masked = false;

		this.title = title;

		this.children = new ArrayList<>();
		
		this.logManager = new LogManager(title);
		this.fontManager = new FontManager();

		init(this);
	}
	
	public LogManager getLogManager() {
		return logManager;
	}

	public FontManager getFontManager() {
		return fontManager;
	}

	public boolean isStarted() {
		return isStarted;
	}

	@Override
	public void create () {
		isStarted = true;
	}

	@Override
	public void resize (int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void render () {

	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	public String getTitle() {
		return title;
	}

	@Override
	public boolean isMasking() {
		return masked;
	}

	@Override
	public void setMasking(boolean flag) {
		this.masked = flag;
	}

	@Override
	public boolean isRounded() {
		return rounded;
	}

	@Override
	public void setRound(boolean flag) {
		this.rounded = flag;
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {

	}

	@Override
	public List<IDrawable> getChildren() {
		return new ArrayList<>(children);
	}

	protected List<IDrawable> getChildrenInternal() {
		return children;
	}

	@Override
	public void update(int delta) {

	}

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
			((AbstractDrawableContainer) drawable).init(this);
		}

		if (isStarted())
			drawable.onLoaded();
	}

	protected void removeInternal(IDrawable drawable) {
		getChildrenInternal().remove(drawable);
		drawable.onRemoved();

		if (isStarted())
			drawable.onUnloaded();
	}

	@Override
	public boolean containsChild(IDrawable drawable) {
		return children.contains(drawable);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
