package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.beatmap.INote;
import cf.kuiprux.spbeat.game.gui.marker.hit.HitStatement;
import cf.kuiprux.spbeat.gui.IDrawable;

public interface IMarkerDrawable extends IDrawable {
    INote getNote();
    HitStatement getHitStatement();
    //노트가 나타나야하면 true 반환
    boolean isOnScreen(long time);

    void click(long time);
}
