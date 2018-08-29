package cf.kuiprux.spbeat.game.gui.marker.hit;

import cf.kuiprux.spbeat.game.beatmap.HoldNote;

public class HoldNoteHitStatement implements IHitStatement {

    private HoldNote note;

    private boolean isCaclulated;

    private boolean clicked;
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

        if (startError < NoteHitStatement.HIT_TIMING_PERFECT && endError > 0) {
            hitState = HitState.PERFECT;
        }
        else if (startError < NoteHitStatement.HIT_TIMING_GREAT && endError <= NoteHitStatement.HIT_TIMING_PERFECT) {
            hitState = HitState.GREAT;
        }
        else if (startError < NoteHitStatement.HIT_TIMING_GOOD && endError <= NoteHitStatement.HIT_TIMING_POOR) {
            hitState = HitState.GOOD;
        }
        else if (startError < NoteHitStatement.HIT_TIMING_POOR && endError <= NoteHitStatement.HIT_TIMING_POOR) {
            hitState = HitState.POOR;
        }
        else {
            hitState = HitState.MISS;
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

    public boolean isClicked() {
        return clicked;
    }

    public void setClickStarted(long clickStarted) {
        this.clickStarted = clickStarted;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
