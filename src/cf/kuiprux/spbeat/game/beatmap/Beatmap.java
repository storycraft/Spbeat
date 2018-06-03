package cf.kuiprux.spbeat.game.beatmap;

public class Beatmap {
	
	private String title;
	private String songPath;
	private String jacketPath;
	
	private int bpm;
	private int difficulty;
	
	public Beatmap(String title, String songPath, String jacketPath, int bpm, int difficulty) {
		this.title = title;
		this.songPath = songPath;
		this.jacketPath = jacketPath;
		this.bpm = bpm;
		this.difficulty = difficulty;
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

	public int getBpm() {
		return bpm;
	}

	public int getDifficulty() {
		return difficulty;
	}
}
