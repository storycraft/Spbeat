package cf.kuiprux.spbeat.game.beatmap;

import java.util.ArrayList;
import java.util.List;

public class Beatmap {
	
	private String title;
	private String songPath;
	private String jacketPath;
	
	private float bpm;
	private float difficulty;
	
	private List<Note> noteList;
	
	public Beatmap(String title, String songPath, String jacketPath, float bpm, float difficulty, List<Note> noteList) {
		this.title = title;
		this.songPath = songPath;
		this.jacketPath = jacketPath;
		this.bpm = bpm;
		this.difficulty = difficulty;
		
		this.noteList = noteList;
	}

	public String getTitle() {
		return title;
	}

	public String getSongPath() {
		return songPath;
	}

	public String getJacketPath() {
		return jacketPath;
	}

	public float getBpm() {
		return bpm;
	}

	public float getDifficulty() {
		return difficulty;
	}

	public List<Note> getNoteList() {
		return new ArrayList<>(noteList);
	}
}
