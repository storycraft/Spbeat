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
        this.fadeCount = (fadeCount + delta) % 4000;
        setOpacity((float) Math.sin((fadeCount / 4000) * Math.PI * 2) * 0.75f);
    }
}
