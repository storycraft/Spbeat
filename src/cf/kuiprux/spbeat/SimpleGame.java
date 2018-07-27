package cf.kuiprux.spbeat;

import cf.kuiprux.spbeat.gui.font.FontManager;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import cf.kuiprux.spbeat.gui.Container;
import cf.kuiprux.spbeat.logging.LogManager;

public abstract class SimpleGame extends Container implements Game {
	
	private GameContainer windowHost;
	private LogManager logManager;
	private FontManager fontManager;
	private boolean isStarted;
	
	private String title;
	
	public SimpleGame(String title) {
		this.isStarted = false;
		this.title = title;
		
		this.logManager = new LogManager(title);
		this.fontManager = new FontManager();
	}
	
	public GameContainer getWindowHost() {
		return windowHost;
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
	public void init(GameContainer container) throws SlickException {
		if (isStarted())
			throw new SlickException("Game is already started");
		
		windowHost = container;
		
		onLoaded();
	}
	
	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		if (container != windowHost)
			throw new SlickException("called from another GameContainer?!");
	
		draw(graphics);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (container != windowHost)
			throw new SlickException("called from another GameContainer?!");
		
		update(delta);
	}
	
	@Override
	public void onLoaded() {
		isStarted = true;
		init(this);
		super.onLoaded();
	}

	@Override
	public boolean closeRequested() {
		return true;
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public float getWidth() {
		if (windowHost == null)
			return 0;
		return windowHost.getWidth();
	}

	@Override
	public float getHeight() {
		if (windowHost == null)
			return 0;
		return windowHost.getHeight();
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(0, 0, getWidth(), getHeight());
	}
}
