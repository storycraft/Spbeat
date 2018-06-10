package cf.kuiprux.spbeat.game;

import cf.kuiprux.spbeat.game.beatmap.Beatmap;

public class PlayManager {
	
	private Beatmap beatmap;
	
	public PlayManager(Beatmap beatmap) {
		this.beatmap = beatmap;
	}
	
	public Beatmap getBeatmap() {
		return beatmap;
	}
}
