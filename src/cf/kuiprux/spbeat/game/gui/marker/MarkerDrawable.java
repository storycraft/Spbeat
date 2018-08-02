package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.beatmap.Note;
import cf.kuiprux.spbeat.game.gui.marker.hit.HitStatement;
import cf.kuiprux.spbeat.gui.element.Square;
import org.newdawn.slick.Color;

public class MarkerDrawable extends Square implements IMarkerDrawable {

    private HitStatement hitStatement;
    private Note note;

    public MarkerDrawable(Note note){
        this.note = note;
        this.hitStatement = new HitStatement(note);

        setLocation(0, 0);
        setSize(100, 100);
        setColor(Color.white);
    }

    public Note getNote() {
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

    @Override
    public void update(int delta){
        super.update(delta);
    }
}
