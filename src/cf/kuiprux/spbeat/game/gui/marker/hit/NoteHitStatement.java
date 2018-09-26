package cf.kuiprux.spbeat.game.gui.marker.hit;

import cf.kuiprux.spbeat.game.beatmap.INote;

public class NoteHitStatement implements IHitStatement {

    public static final int HIT_TIMING_PERFECT = 41;
    public static final int HIT_TIMING_GREAT = 82;
    public static final int HIT_TIMING_GOOD = 123;
    public static final int HIT_TIMING_POOR = 164;

    private INote note;

    private boolean isCaclulated;

    private HitState hitState;

    public NoteHitStatement(INote note){
        this.note = note;

        this.isCaclulated = false;
        this.hitState = HitState.MISS;
    }

    @Override
    public INote getNote() {
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
        else {
            hitState = HitState.MISS;
        }

        System.out.println(hitState);
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
