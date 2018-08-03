package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.containers.SimpleContainer;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.gui.element.Text;
import org.newdawn.slick.UnicodeFont;

public class BeatmapSelectBox extends SimpleContainer {

    private float fadeCount;

    private Square fadeBox;
    private Text songTitleText;

    public BeatmapSelectBox(float x, float y, float width, float height, UnicodeFont font){
        setLocation(x, y);
        this.fadeCount = 0;

        this.fadeBox = new Square(0, 0, width, height);
        this.songTitleText = new Text(font, "");

        getSongTitleText().setAnchor(AlignMode.CENTRE);
        getSongTitleText().setOrigin(AlignMode.CENTRE);
        getSongTitleText().setFontSize(12);
        getSongTitleText().setLocation(width / 2, height / 2);

        addChild(fadeBox);
        addChild(songTitleText);

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

    public Text getSongTitleText() {
        return songTitleText;
    }
}
