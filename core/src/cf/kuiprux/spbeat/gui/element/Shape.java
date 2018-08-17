package cf.kuiprux.spbeat.gui.element;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import cf.kuiprux.spbeat.gui.IHasTexture;
import cf.kuiprux.spbeat.gui.TextureFillMode;
import com.badlogic.gdx.math.Shape2D;

public abstract class Shape extends Sprite implements IHasTexture {
	
	private float width;
	private float height;
	
	private Texture texture;
	private TextureFillMode mode;
	
	public Shape(float x, float y, float width, float height) {
		this(x, y);
		
		this.width = width;
		this.height = height;
	}
	
	public Shape(float x, float y) {
		super.setX(x);
		super.setY(y);
	}
	
	public Shape() {
		this(0, 0, 0, 0);
	}

	@Override
	public Texture getTexture() {
		return texture;
	}

	@Override
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}
	
	public void setWidth(float width) {
		this.width = width;
		
		sendParentUpdate();
	}

	public void setHeight(float height) {
		this.height = height;
		
		sendParentUpdate();
	}

	public void setSize(float width, float height){
		this.width = width;
		this.height = height;

		sendParentUpdate();
	}

	@Override
	public TextureFillMode getTextureFillMode() {
		return mode;
	}

	@Override
	public void setTextureFillMode(TextureFillMode mode) {
		if (mode == null)
			return;
		
		this.mode = mode;
	}
	
	@Override
	public Rectangle getBoundingBox() {

		org.newdawn.slick.geom.Shape shape = getShape().transform(getTransformData().getSlickTransform());
		
		return new Rectangle(shape.getMinX(), shape.getMinY(), shape.getWidth(), shape.getHeight());
	}
	
	@Override
	public void update(int delta) {
		
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		applyProperties(graphics);
		applyTransform(graphics);
		
		org.newdawn.slick.geom.Shape shape = getShape();

		graphics.setDrawMode(getDrawMode().getIntMode());
		
		//택스쳐 존재시 택스쳐 사용
		if (getTexture() != null) {
			drawAdjustedTexture(shape, graphics);
		}
		else {
			graphics.fill(shape);
		}
			
		if (getBorderWidth() != 0) {
			graphics.setColor(getBorderColor().multiply(new Color(255, 255, 255, (int) (getOpacity() * 255))));
			graphics.draw(shape);
		}
	}
	
	private void drawAdjustedTexture(org.newdawn.slick.geom.Shape shape, Graphics graphics) {
		//TODO:: TextureFillMode구현
		
		graphics.texture(shape, getTexture(), true);
	}
	
	//draw 할때 사용될 패스
	protected abstract Shape2D getShape();
}
