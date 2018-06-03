package cf.kuiprux.spbeat.gui;

import org.newdawn.slick.Image;

public interface IHasTexture {
	TextureFillMode getTextureFillMode();
	Image getTexture();
	//texture 가 null일시 텍스쳐 제거
	void setTexture(Image texture);
	void setTextureFillMode(TextureFillMode mode);
}
