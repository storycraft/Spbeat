package cf.kuiprux.spbeat.game.beatmap;

import cf.kuiprux.spbeat.game.gui.PlayScreen;

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

    public float getStartTime() {
        return startTime;
    }

    public float getExactTime() {
        return endTime;
    }

    @Override
    public boolean isOnScreen(long time) {
        return getStartTime() >= time && getExactTime() <= time;
    }

    @Override
    public String toString(){
        return "HoldNote startIndex: " + getStartIndex() + " endIndex: " + getNoteIndex() + " startTime: " + getStartTime() + " endTime: " + getExactTime();
    }
}
