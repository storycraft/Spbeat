package cf.kuiprux.spbeat.game.beatmap;

import java.util.ArrayList;
import java.util.List;

public class Beatmap {
	
	private String title;
	private String artist;
	private String songPath;
	private String jacketPath;

	private float sync;
	
	private float beatTime;
	private float difficulty;
	
	private List<BeatList> beatListArray;
	
	public Beatmap(String title, String artist, String songPath, String jacketPath, float beatTime, float sync, float difficulty, List<BeatList> beatListArray) {
		this.title = title;
		this.artist = artist;
		this.songPath = songPath;
		this.jacketPath = jacketPath;
		this.beatTime = beatTime;
		this.difficulty = difficulty;
		this.sync = sync;
		
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

	public float getBeatTime() {
		return beatTime;
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

	public INote getFirstNote(){
		for (BeatList beatList : beatListArray){
			for (INote note : beatList.getNoteList()) {
				return note;
			}
		}

		return null;
	}

	public List<BeatList> getBeatListArray() {
		return new ArrayList<>(beatListArray);
	}

	public int getNoteCount() {
		int count = 0;
		for (int i = 0; i < beatListArray.size(); i++)
			count += beatListArray.get(i).getNoteList().size();

		return count;
	}

	public float getSync() {
		return sync;
	}
}
