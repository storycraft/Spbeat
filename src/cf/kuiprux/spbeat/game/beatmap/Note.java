package cf.kuiprux.spbeat.game.beatmap;

public class Note {
	
	//노트가 나타날 위치
	private int noteIndex;
	//노트가 클릭되야 되는 정확한 시간
	private int exactTime;
	
	public Note(int noteIndex, int exactTime) {
		this.noteIndex = noteIndex;
		this.exactTime = exactTime;
	}
	
	public int getNoteIndex() {
		return noteIndex;
	}
	
	public int getExactTime() {
		return exactTime;
	}
}
