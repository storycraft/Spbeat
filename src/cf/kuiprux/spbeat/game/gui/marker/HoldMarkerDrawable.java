package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.beatmap.HoldNote;
import cf.kuiprux.spbeat.game.gui.marker.hit.HitStatement;
import cf.kuiprux.spbeat.gui.Container;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class HoldMarkerDrawable extends Container implements IMarkerDrawable {

    private HoldNote note;
    private HitStatement hitStatement;

    public HoldMarkerDrawable(HoldNote note){
        this.note = note;
        this.hitStatement = new HitStatement(note);
    }

    @Override
    protected void updateInternal(int delta) {

    }

    @Override
    protected void drawInternal(Graphics graphics) {

    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    @Override
    public Rectangle getBoundingBox() {
        return null;
    }

    @Override
    public HoldNote getNote() {
        return note;
    }

    @Override
    public HitStatement getHitStatement() {
        return hitStatement;
    }
}
