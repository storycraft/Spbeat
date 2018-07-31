package cf.kuiprux.spbeat.game.beatmap;

public class HoldNote implements INote {

    private int startIndex;
    private int endIndex;

    private float startTime;
    private float endTime;

    public HoldNote(int startIndex, int endIndex, float startTime, float endTime) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getNoteIndex() {
        return endIndex;
    }

    public float getExactTime() {
        return startTime;
    }

    public float getEndTime() {
        return endTime;
    }

    @Override
    public String toString(){
        return "HoldNote startIndex: " + getStartIndex() + " endIndex: " + getNoteIndex() + " startTime: " + getExactTime() + " endTime: " + getEndTime();
    }
}
