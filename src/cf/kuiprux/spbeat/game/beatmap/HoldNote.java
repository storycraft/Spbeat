package cf.kuiprux.spbeat.game.beatmap;

public class HoldNote extends Note {

    private int startIndex;

    private float startTime;

    public HoldNote(int startIndex, int endIndex, float startTime, float endTime) {
        super(endIndex, endTime);
        this.startIndex = startIndex;
        this.startTime = startTime;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public float getStartTime() {
        return startTime;
    }

    @Override
    public boolean isOnScreen(long time) {
        return getStartTime() <= time && getExactTime() >= time;
    }

    @Override
    public String toString(){
        return "HoldNote startIndex: " + getStartIndex() + " endIndex: " + getNoteIndex() + " startTime: " + getStartTime() + " endTime: " + getExactTime();
    }
}
