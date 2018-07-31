package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.beatmap.INote;
import cf.kuiprux.spbeat.game.gui.marker.hit.HitStatement;
import cf.kuiprux.spbeat.gui.IDrawable;

public interface IMarkerDrawable extends IDrawable {
    INote getNote();
    HitStatement getHitStatement();
}
