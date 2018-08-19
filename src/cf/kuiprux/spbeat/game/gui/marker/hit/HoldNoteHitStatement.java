package cf.kuiprux.spbeat.game.gui.marker.hit;

import cf.kuiprux.spbeat.game.beatmap.HoldNote;

public class HoldNoteHitStatement implements IHitStatement {

    private HoldNote note;

    private boolean isCaclulated;

    private long clickStarted;

    private HitState hitState;

    public HoldNoteHitStatement(HoldNote note){
        this.note = note;

        this.isCaclulated = false;
        this.clickStarted = 0;
        this.hitState = HitState.MISS;
    }

    @Override
    public HoldNote getNote() {
        return note;
    }

    @Override
    public void calculateState(long time) {
        this.isCaclulated = true;

        float startError = Math.abs(clickStarted - getNote().getStartTime());
        float endError = time - getNote().getExactTime();

        if (startError < 100 && endError > 0) {
            hitState = HitState.PERFECT;
        }
    }

    @Override
    public boolean isCalculated() {
        return isCaclulated;
    }

    @Override
    public HitState getHitState() {
        return hitState;
    }

    public long getClickStarted() {
        return clickStarted;
    }

    public void setClickStarted(long clickStarted) {
        this.clickStarted = clickStarted;
    }
}
