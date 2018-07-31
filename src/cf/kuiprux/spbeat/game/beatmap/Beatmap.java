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
	
	private List<BeatList> beatListArray;
	
	public Beatmap(String title, String artist, String songPath, String jacketPath, float tempo, float difficulty, List<BeatList> beatListArray) {
		this.title = title;
		this.artist = artist;
		this.songPath = songPath;
		this.jacketPath = jacketPath;
		this.tempo = tempo;
		this.difficulty = difficulty;
		
		this.beatListArray = beatListArray;
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

	public boolean hasHoldMarker(){
		for (BeatList beatList : beatListArray){
			for (INote note : beatList.getNoteList()) {
				if (note instanceof HoldNote)
					return true;
			}
		}

		return false;
	}

	public List<BeatList> getBeatListArray() {
		return new ArrayList<>(beatListArray);
	}
}
