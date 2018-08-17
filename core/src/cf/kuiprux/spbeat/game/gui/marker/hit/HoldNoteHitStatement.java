package cf.kuiprux.spbeat.game.gui.marker.hit;

import cf.kuiprux.spbeat.game.beatmap.HoldNote;
import cf.kuiprux.spbeat.game.beatmap.INote;

public class HoldNoteHitStatement implements IHitStatement {

    private HoldNote note;

    private boolean isCaclulated;
    private boolean isMissed;

    private int score;

    public HoldNoteHitStatement(HoldNote note){
        this.note = note;

        this.isCaclulated = false;
        this.isMissed = false;
        this.score = 0;
    }

    @Override
    public HoldNote getNote() {
        return note;
    }

    @Override
    public void calculateState(long time) {
        this.isCaclulated = true;
        score = 100;
    }

    @Override
    public boolean isCalculated() {
        return isCaclulated;
    }

    @Override
    public boolean isMissed() {
        return isMissed;
    }

    @Override
    public int getScore() {
        return score;
    }
}
