package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.gui.element.Square;

public class BeatmapSelectBox extends Square {

    private float fadeCount;

    public BeatmapSelectBox(float x, float y, float width, float height){
        super(x, y, width, height);

        this.fadeCount = 0;
    }

    @Override
    public void update(int delta) {
        this.fadeCount = (fadeCount + delta) % 3500;
        setOpacity((float) Math.sin((fadeCount / 1750) * Math.PI) * 0.75f);
    }
}
