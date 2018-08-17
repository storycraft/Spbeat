package cf.kuiprux.spbeat.game.beatmap;

import java.util.List;

public class BeatList {

    private float beatTime;
    private List<INote> noteList;

    public BeatList(float beatTime, List<INote> noteList){
        this.beatTime = beatTime;
        this.noteList = noteList;
    }

    //비트가 시작하는 시간
    public float getBeatTime() {
        return beatTime;
    }

    public List<INote> getNoteList() {
        return noteList;
    }
}
