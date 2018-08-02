package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.beatmap.HoldNote;
import cf.kuiprux.spbeat.game.gui.ButtonPanel;
import cf.kuiprux.spbeat.game.gui.marker.hit.HitStatement;
import cf.kuiprux.spbeat.gui.containers.FixedContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class HoldMarkerDrawable extends FixedContainer implements IMarkerDrawable {

    private HoldNote note;
    private HitStatement hitStatement;

    public HoldMarkerDrawable(HoldNote note){
        this.note = note;
        this.hitStatement = new HitStatement(note);

        int sx = note.getStartIndex() % ButtonPanel.COLUMN;
        int sy = note.getStartIndex() / ButtonPanel.ROW;

        int ex = note.getNoteIndex() % ButtonPanel.COLUMN;
        int ey = note.getNoteIndex() / ButtonPanel.ROW;

        setLocation(sx - ex, sy - ey);
        setSize((sx - ex + 1) * ButtonPanel.BUTTON_WIDTH, (sx - ex + 1) * ButtonPanel.BUTTON_HEIGHT);
    }

    @Override
    protected void updateInternal(int delta) {

    }

    @Override
    protected void drawInternal(Graphics graphics) {

    }

    public float getSliderX(long time){
        return 0;
    }

    public float getSliderY(long time){
        return 0;
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

    @Override
    public boolean isOnScreen(long time) {
        return getNote().isOnScreen(time);
    }
}
