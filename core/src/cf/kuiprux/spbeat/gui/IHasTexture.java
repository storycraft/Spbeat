package cf.kuiprux.spbeat.gui;

import com.badlogic.gdx.graphics.Texture;

public interface IHasTexture {
	TextureFillMode getTextureFillMode();
	Texture getTexture();
	//texture 가 null일시 텍스쳐 제거
	void setTexture(Texture texture);
	void setTextureFillMode(TextureFillMode mode);
}
