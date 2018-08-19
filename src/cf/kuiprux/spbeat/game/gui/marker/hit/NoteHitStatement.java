package cf.kuiprux.spbeat.game.gui.marker.hit;

import cf.kuiprux.spbeat.game.beatmap.Note;

public class NoteHitStatement implements IHitStatement {

    public static final int HIT_TIMING = 200;

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

    @Override
    public void calculateState(long time) {
        this.isCaclulated = true;

        float timing = note.getExactTime() - time;

        hitState = HitState.MISS;
        return;

        /*
        //TODO: fix timing and score
        if (Math.abs(timing) < HIT_TIMING){
            this.score = 100;
        }
        else{
            this.isMissed = true;
        }
        */
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