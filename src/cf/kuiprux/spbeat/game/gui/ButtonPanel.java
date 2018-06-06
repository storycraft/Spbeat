package cf.kuiprux.spbeat.game.gui;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.Container;
import cf.kuiprux.spbeat.gui.Drawable;
import cf.kuiprux.spbeat.gui.IHasColor;
import cf.kuiprux.spbeat.gui.IHasTexture;
import cf.kuiprux.spbeat.gui.TextureFillMode;
import cf.kuiprux.spbeat.gui.containers.SimpleContainer;
import cf.kuiprux.spbeat.gui.element.Square;

// 4x4 크기의 사각형 버튼 셋
public class ButtonPanel extends SimpleContainer {
	
	public static final int BUTTON_WIDTH = 100;
	public static final int BUTTON_HEIGHT = 100;
	
	public static final int BUTTON_GAP_X = 10;
	public static final int BUTTON_GAP_Y = 10;
	
	public static final int ROW = 4;
	public static final int COLUMN = 4;
	
	private BackgroundContainer background;
	private List<ButtonArea> buttonList;
	
	public ButtonPanel() {
		this.buttonList = new ArrayList<>();
		initPanel();
	}
	
	private void initPanel() {
		addInternal(background = new BackgroundContainer());
		
		setLocation(85.5f, 285.5f);
		setMasking(true);
		
		for (int y = 0; y < getRowCount(); y++) {
			for (int x = 0; x < getColumnCount(); x++) {
				//float 오차값
				ButtonArea button = new ButtonArea(getButtonPosX(x), getButtonPosY(y));

				addInternal(button);
				buttonList.add(button);
			}
		}
	}
	
	public BackgroundContainer getBackground() {
		return background;
	}
	
	//기본 자식 수정 방지 처리
	@Override
	public boolean removeChild(Drawable child) {
		if (child == background || buttonList.contains(child))
			return false;
		
		return removeChild(child);
	}
	
	//해당 x y 번째 에 있는 Button오브젝트 반환
	public ButtonArea getButtonAreaAt(int x, int y) {
		return getButtonAreaAt(y * getColumnCount() + x);
	}

	public ButtonArea getButtonAreaAt(int index) {
		return buttonList.get(index);
	}
	
	public List<ButtonArea> getButtonAreaList() {
		return new ArrayList<ButtonArea>(buttonList);
	}

	@Override
	public void draw(Graphics graphics) {
		super.draw(graphics);
		
		graphics.pushTransform();
		
		applyTransform(graphics);
		applyProperties(graphics);
		
		// !! TRICK !! :(
		graphics.setColor(Color.black);
		
		float drawX = getDrawX();
		float drawY = getDrawY();
		
		//가로 구분선
		for (int x = 1; x < getRowCount(); x++) {
			graphics.fillRect(drawX + getButtonPosX(x) - getButtonGapColumn(), drawY, getButtonGapColumn(), getDrawHeight());
		}
		
		//세로 구분선
		for (int y = 1; y < getColumnCount(); y++) {
			graphics.fillRect(drawX, drawY + getButtonPosY(y) - getButtonGapRow(), getDrawWidth(), getButtonGapRow());
		}
		
		graphics.popTransform();
	}
	
	@Override
	protected void updateInternal(int delta) {
		
	}
	
	//가로서 x 번째 버튼 x 좌표
	public float getButtonPosX(int x) {
		return (getButtonWidth() + getButtonGapColumn()) * x;
	}

	//가로서 x 번째 버튼 y 좌표
	public float getButtonPosY(int y) {
		return (getButtonHeight() + getButtonGapRow()) * y;
	}
	
	public float getButtonWidth() {
		return BUTTON_WIDTH;
	}

	public float getButtonHeight() {
		return BUTTON_HEIGHT;
	}
	
	public float getButtonGapColumn() {
		return BUTTON_GAP_X;
	}
	
	public float getButtonGapRow() {
		return BUTTON_GAP_Y;
	}
	
	public int getRowCount() {
		return ROW;
	}
	
	public int getColumnCount() {
		return COLUMN;
	}
	
	public class ButtonArea extends Container {
		
		public ButtonArea(float x, float y) {
			super();
			
			setX(x);
			setY(y);
			
			setOrigin(AlignMode.CENTRE);
		}
		
		@Override
		public float getWidth() {
			return getButtonWidth();
		}

		@Override
		public float getHeight() {
			return getButtonHeight();
		}
		
		//오차 값 수정
		@Override
		public float getDrawWidth() {
			return super.getDrawWidth()  +1;
		}

		@Override
		public float getDrawHeight() {
			return super.getDrawHeight() + 1;
		}

		@Override
		protected void updateInternal(int delta) {
			
		}

		@Override
		protected void drawInternal(Graphics graphics) {
			
		}

		@Override
		public Rectangle getBoundingBox() {
			return new Rectangle(getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight());
		}
	}
	
	public class BackgroundContainer extends Container implements IHasColor, IHasTexture {
		
		private Square backgroundSquare;
		
		public BackgroundContainer() {
			backgroundSquare = new Square() {
				@Override
				public float getX() {
					return 0;
				}
				
				@Override
				public float getY() {
					return 0;
				}
				
				@Override
				public float getWidth() {
					return BackgroundContainer.this.getWidth();
				}
				
				@Override
				public float getHeight() {
					return BackgroundContainer.this.getHeight();
				}
				
				@Override
				public float getDrawX() {
					return BackgroundContainer.this.getDrawX();
				}
				
				@Override
				public float getDrawY() {
					return BackgroundContainer.this.getDrawY();
				}
				
				@Override
				public float getDrawWidth() {
					return BackgroundContainer.this.getDrawWidth();
				}
				
				@Override
				public float getDrawHeight() {
					return BackgroundContainer.this.getDrawHeight();
				}
			};
			
			addInternal(backgroundSquare);
		}

		@Override
		protected void updateInternal(int delta) {
			
		}

		@Override
		protected void drawInternal(Graphics graphics) {
			
		}
		
		@Override
		public boolean removeChild(Drawable child) {
			if (child == backgroundSquare)
				return false;
			
			return removeChild(child);
		}

		@Override
		public float getWidth() {
			return getButtonPosX(getColumnCount() - 1) + getButtonWidth();
		}

		@Override
		public float getHeight() {
			return getButtonPosY(getRowCount() - 1) + getButtonHeight();
		}

		@Override
		public Rectangle getBoundingBox() {
			return ButtonPanel.this.getBoundingBox();
		}

		@Override
		public TextureFillMode getTextureFillMode() {
			return backgroundSquare.getTextureFillMode();
		}

		@Override
		public Image getTexture() {
			return backgroundSquare.getTexture();
		}

		@Override
		public void setTexture(Image texture) {
			backgroundSquare.setTexture(texture);
		}

		@Override
		public void setTextureFillMode(TextureFillMode mode) {
			backgroundSquare.setTextureFillMode(mode);
		}

		@Override
		public Color getColor() {
			return backgroundSquare.getColor();
		}

		@Override
		public Color getBorderColor() {
			return backgroundSquare.getBorderColor();
		}

		@Override
		public float getBorderWidth() {
			return backgroundSquare.getBorderWidth();
		}

		@Override
		public void setColor(Color color) {
			backgroundSquare.setColor(color);
		}

		@Override
		public void setBorderColor(Color color) {
			backgroundSquare.setBorderColor(color);
		}

		@Override
		public void setBorderWidth(float width) {
			backgroundSquare.setBorderWidth(width);
		}
	}
}
