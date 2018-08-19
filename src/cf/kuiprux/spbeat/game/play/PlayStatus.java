package cf.kuiprux.spbeat.game.play;

import cf.kuiprux.spbeat.game.beatmap.Beatmap;

public class PlayStatus {

    private Beatmap map;

    private boolean isPlaying;

    private int perfectCount;
    private int greatCount;
    private int goodCount;
    private int poorCount;
    private int missCount;

    private int currentCombo;

    public PlayStatus(Beatmap map) {
        this.map = map;
        this.currentCombo = this.perfectCount = this.greatCount = this.goodCount = this.poorCount = this.missCount = 0;
        this.isPlaying = false;
    }

    public Beatmap getMap() {
        return map;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    protected void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public int getCurrentCombo() {
        return currentCombo;
    }

    public void setCurrentCombo(int currentCombo) {
        this.currentCombo = currentCombo;
    }

    public int getTotalNoteCount() {
        return map.getNoteCount();
    }

    public int getPerfectCount() {
        return perfectCount;
    }

    public int getGreatCount() {
        return greatCount;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public int getPoorCount() {
        return poorCount;
    }

    public int getMissCount() {
        return missCount;
    }

    public void setPerfectCount(int perfectCount) {
        this.perfectCount = perfectCount;
    }

    public void setGreatCount(int greatCount) {
        this.greatCount = greatCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public void setPoorCount(int poorCount) {
        this.poorCount = poorCount;
    }

    public void setMissCount(int missCount) {
        this.missCount = missCount;
    }

    public int getScore() {
        return (int) Math.floor(0.9 * Math.floor(1000000 * (getPerfectCount() + 0.7 * getGreatCount() + 0.4 * getGoodCount() + 0.1 * getPoorCount()) / getTotalNoteCount()));
    }

    //0 ~ 1 사이
    public double getShutterPercent() {
        double rating = 1024d / Math.min(1024, getTotalNoteCount());

        return Math.max(Math.min((Math.floor((getPerfectCount() + getGreatCount()) * rating * 2) + Math.floor(getGoodCount() * rating) - Math.floor(rating * 8 * (getMissCount() + getPoorCount()))) / 1024d, 1), 0);
    }

    public int getShutterScore() {
        return (int) Math.floor(getShutterPercent() * 100000);
    }

    public int getTotalScore() {
        return getScore() + getShutterScore();
    }

    public PlayRating getPlayRating() {
        return PlayRating.EXC;//TODO
    }
}