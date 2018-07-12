package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.beatmap.Note;
import cf.kuiprux.spbeat.gui.element.Square;

public class MarkerDrawable extends Square {

    private Note note;

    public MarkerDrawable(Note note){
        this.note = note;
    }

    public Note getNote() {
        return note;
    }
}
