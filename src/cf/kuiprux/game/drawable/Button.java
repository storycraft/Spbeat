package cf.kuiprux.game.drawable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import cf.kuiprux.Reference;

public class Button {

	Image image;
	boolean available = false;

	public Button(Image image, boolean available) {
		this.image = image;
		this.available  = available;
	}

	public void render(Graphics g, int x, int y, boolean selected) {
		//Renders background of button
		renderButton(g, Reference.getButtonBase(), x, y);
		//Renders button image
		renderButton(g, image, x, y);
		//Renders if button is available
		if (available)
			renderButton(g, Reference.getButtonAvailable(), x, y);
		//Renders if button is selected
		if (selected) {
			renderButton(g, Reference.getButtonSelected(), x, y);
		}
	}
	
	public void renderButton(Graphics g, Image image, int x, int y) {
		float x1 = (float) (Reference.FRAME_GAP_WIDTH+(Reference.BUTTON_GAP_WIDTH+Reference.BUTTON_WIDTH)*y);
		float y1 = (float) (Reference.BUTTON_START+Reference.FRAME_GAP_HEIGHT+(Reference.BUTTON_GAP_HEIGHT+Reference.BUTTON_HEIGHT)*x);
		float x2 = (float) (x1+Reference.BUTTON_WIDTH);
		float y2 = (float) (y1+Reference.BUTTON_HEIGHT);
		g.drawImage(image, x1, y1, x2, y2, 0f, 0f, 1024f, 1024f);
	}

	public void setAvailable(boolean state) {
		available = state;
	}

	public boolean isAvailable() {
		return available;
	}
}
