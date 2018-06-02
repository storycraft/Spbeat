package cf.kuiprux.beatmap;

import java.util.List;

public class PlayInfo {
	
	public final long offset;
	public final List<Bpm> bpms;
	public final List<Note> notes;
	
	public PlayInfo(long offset, List<Bpm> bpms, List<Note> notes) {
		this.offset = offset;
		this.bpms = bpms;
		this.notes = notes;
	}

}
