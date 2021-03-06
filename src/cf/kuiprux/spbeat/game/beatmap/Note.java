package cf.kuiprux.spbeat.game.beatmap;

import cf.kuiprux.spbeat.game.play.PlayScreen;

public class Note implements INote {

	//노트가 나타날 위치
	private int noteIndex;
	//노트가 클릭되야 되는 정확한 시간
	private float exactTime;

	public Note(int noteIndex, float exactTime) {
		this.noteIndex = noteIndex;
		this.exactTime = exactTime;
	}

	public int getNoteIndex() {
		return noteIndex;
	}

	public float getExactTime() {
		return exactTime;
	}

	@Override
	public boolean isOnScreen(long time) {
		float timing = getExactTime() - time;
		return timing <= PlayScreen.NOTE_VISIBLE_TIME && timing >= -PlayScreen.AFTER_VISIBLE_TIME;
	}

	@Override
	public String toString(){
		return "Note index: " + getNoteIndex() + " time: " + getExactTime();
	}
}