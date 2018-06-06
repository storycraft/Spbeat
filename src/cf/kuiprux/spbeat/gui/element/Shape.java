package cf.kuiprux.spbeat.gui.element;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import cf.kuiprux.spbeat.gui.IHasTexture;
import cf.kuiprux.spbeat.gui.TextureFillMode;

public abstract class Shape extends Sprite implements IHasTexture {
	
	private float width;
	private float height;
	
	private Image texture;
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
	public Image getTexture() {
		return texture;
	}

	@Override
	public void setTexture(Image texture) {
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
	public void draw(Graphics graphics) {
		applyProperties(graphics);
		applyTransform(graphics);
		
		org.newdawn.slick.geom.Shape shape = getShape();
		
		//텍스쳐 존재시 color는 필터로 적용
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
		//TODO:: TextureFillMode에 따라 크기 계산
		
		graphics.texture(shape, getTexture());
	}
	
	//draw 시 사용될 모양
	protected abstract org.newdawn.slick.geom.Shape getShape();
}
