package cf.kuiprux.spbeat.game.gui.marker.hit;

import cf.kuiprux.spbeat.game.beatmap.Note;

public class NoteHitStatement implements IHitStatement {

    public static final int HIT_TIMING_PERFECT = 41;
    public static final int HIT_TIMING_GREAT = 82;
    public static final int HIT_TIMING_GOOD = 123;
    public static final int HIT_TIMING_POOR = 164;

    private Note note;

    private boolean isCaclulated;
    private boolean isMissed;

    private HitState hitState;

    public NoteHitStatement(Note note){
        this.note = note;

        this.isCaclulated = false;
        this.isMissed = false;
        this.hitState = HitState.MISS;
    }

    @Override
    public Note getNote() {
        return note;
    }

    //41 mili
    @Override
    public void calculateState(long time) {
        this.isCaclulated = true;

        float timing = Math.abs(note.getExactTime() - time);

        if (timing <= HIT_TIMING_PERFECT) {
            hitState = HitState.PERFECT;
        }
        else if (timing <= HIT_TIMING_GREAT) {
            hitState = HitState.GREAT;
        }
        else if (timing <= HIT_TIMING_GOOD) {
            hitState = HitState.GOOD;
        }
        else if (timing <= HIT_TIMING_POOR) {
            hitState = HitState.POOR;
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

}
