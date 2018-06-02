package cf.kuiprux.spbeat.beatmap.object;

public class Note {

	//Button location
	public final int location;
	//The time that note begins to show
	//New map system can manage this
	//Old map system will be fixed to specific time
	public final long ready;
	//The time that note should be pressed
	public final long time;
	//Note pressing duration
	public final long duration;
	
	public Note(int location, long ready, long time, long duration) {
		this.location = location;
		this.ready = ready;
		this.time = time;
		this.duration = duration;
	}
}
