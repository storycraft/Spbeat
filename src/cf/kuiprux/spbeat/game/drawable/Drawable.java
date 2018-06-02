package cf.kuiprux.spbeat.game.drawable;

import org.newdawn.slick.Renderable;

public class Drawable implements Renderable {
	
	private Renderable texture;

	@Override
	public void draw(float x, float y) {
		//더 추가 되어야 됨
		if (texture != null)
			texture.draw(x, y);
	}

	public Renderable getTexture() {
		return texture;
	}

	public void setTexture(Renderable texture) {
		this.texture = texture;
	}
	
}
