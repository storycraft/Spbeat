package cf.kuiprux.spbeat.game.gui.marker.hit;

import cf.kuiprux.spbeat.game.beatmap.INote;

public class HitStatement {

    private INote note;

    private int score;
    private boolean calculated;

    public HitStatement(INote note){
        this.note = note;
        this.score = 0;
        this.calculated = false;
    }

    public INote getNote() {
        return note;
    }

    public void calculateState(long time){
        this.calculated = true;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public int getScore(){
        return score;
    }
}
