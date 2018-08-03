package cf.kuiprux.spbeat;

import org.newdawn.slick.Image;

public class Button {
	Image image;
	boolean available = false;
	boolean selected = false;
	
	public Button(Image image, boolean available) {
		this.image = image;
		this.available  = available;
	}
	
	public void setAvailable(boolean state) {
		available = state;
	}
	
	public void setSelected(boolean state) {
		selected = state;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public Image getImage() {
		return image;
	}
}
