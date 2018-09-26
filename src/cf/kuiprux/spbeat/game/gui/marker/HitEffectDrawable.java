package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.gui.ButtonPanel;
import cf.kuiprux.spbeat.gui.DrawMode;
import cf.kuiprux.spbeat.gui.element.Square;
import org.newdawn.slick.Color;
import org.newdawn.slick.SpriteSheet;

public class HitEffectDrawable extends Square {

    private static final int EFFECT_TIME = 500;
    private static final int SPRITE_COUNT = 15;

    private SpriteSheet effectSheet;

    private int time;

    private int lastIndex;

    public HitEffectDrawable(SpriteSheet effectSheet) {
        super(0, 0, ButtonPanel.BUTTON_WIDTH, ButtonPanel.BUTTON_HEIGHT);
        setColor(Color.white);
        this.time = 0;
        this.effectSheet = effectSheet;
        setDrawMode(DrawMode.ADD);
    }

    @Override
    public void update(int delta) {
        super.update(delta);

        this.time += delta;

        int index = (int) Math.floor((((float) this.time) / EFFECT_TIME) * SPRITE_COUNT);

        if (index >= SPRITE_COUNT) {
            this.expire();
            return;
        }
        else if (index != lastIndex) {
            setTexture(effectSheet.getSprite(index % 5, index / 5));
        }

        lastIndex = index;
    }
}
