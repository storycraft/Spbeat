package cf.kuiprux.spbeat.game.gui.marker.hit;

import cf.kuiprux.spbeat.game.beatmap.INote;

public interface IHitStatement {

    INote getNote();

    void calculateState(long time);

    boolean isCalculated();

    HitState getHitState();
}
