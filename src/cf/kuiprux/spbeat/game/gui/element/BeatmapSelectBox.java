package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.containers.SimpleContainer;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.gui.element.Text;
import org.newdawn.slick.UnicodeFont;

public class BeatmapSelectBox extends SimpleContainer {

    private float fadeCount;

    private Square fadeBox;

    public BeatmapSelectBox(float x, float y, float width, float height){
        setLocation(x, y);
        this.fadeCount = 0;

        this.fadeBox = new Square(0, 0, width, height);

        addChild(fadeBox);

        setMasking(true);
    }

    @Override
    public void update(int delta) {
        this.fadeCount = (fadeCount + delta) % 3500;
        getFadeBox().setOpacity((float) Math.sin((fadeCount / 1750) * Math.PI) * 0.5f + 0.25f);
    }

    public Square getFadeBox() {
        return fadeBox;
    }
}
