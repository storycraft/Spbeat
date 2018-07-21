package cf.kuiprux.spbeat.game.beatmap;

import java.util.ArrayList;
import java.util.List;

public class Beatmap {
	
	private String title;
	private String artist;
	private String songPath;
	private String jacketPath;
	
	private float tempo;
	private float difficulty;
	
	private List<INote> noteList;
	
	public Beatmap(String title, String artist, String songPath, String jacketPath, float tempo, float difficulty, List<INote> noteList) {
		this.title = title;
		this.artist = artist;
		this.songPath = songPath;
		this.jacketPath = jacketPath;
		this.tempo = tempo;
		this.difficulty = difficulty;
		
		this.noteList = noteList;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getSongPath() {
		return songPath;
	}

	public String getJacketPath() {
		return jacketPath;
	}

	public float getTempo() {
		return tempo;
	}

	public float getDifficulty() {
		return difficulty;
	}

	public List<INote> getNoteList() {
		return new ArrayList<>(noteList);
	}
}
