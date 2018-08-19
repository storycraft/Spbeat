package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.beatmap.INote;
import cf.kuiprux.spbeat.game.gui.marker.hit.IHitStatement;
import cf.kuiprux.spbeat.gui.IDrawable;

public interface INoteDrawable extends IDrawable {
    INote getNote();
    IHitStatement getHitStatement();
    //노트가 나타나야하면 true 반환
    boolean isOnScreen(long time);

    void onkeyDown(long time);
    void onkeyUp(long time);
}
