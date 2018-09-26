package cf.kuiprux.spbeat.game.beatmap;

import cf.kuiprux.spbeat.game.play.PlayScreen;

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

    public float getLength() {
        return getExactTime() - getStartTime();
    }

    @Override
    public boolean isOnScreen(long time) {
        return time >= getStartTime() - PlayScreen.NOTE_VISIBLE_TIME && time <= getExactTime() + PlayScreen.AFTER_VISIBLE_TIME;
    }

    @Override
    public String toString(){
        return "HoldNote startIndex: " + getStartIndex() + " endIndex: " + getNoteIndex() + " startTime: " + getStartTime() + " endTime: " + getExactTime();
    }
}
