package cf.kuiprux.spbeat.beatmap;

import java.util.List;

import cf.kuiprux.spbeat.beatmap.object.Bpm;
import cf.kuiprux.spbeat.beatmap.object.Note;

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
