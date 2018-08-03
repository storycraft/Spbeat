package cf.kuiprux.spbeat.object;

public class Note {

	public final int location;
	public final long ready;
	public final long time;
	public final long duration;
	
	public Note(int location, long ready, long time, long duration) {
		this.location = location;
		this.ready = ready;
		this.time = time;
		this.duration = duration;
	}
}
