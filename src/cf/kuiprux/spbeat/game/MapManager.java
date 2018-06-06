package cf.kuiprux.spbeat.game;

import java.util.ArrayList;
import java.util.List;

import cf.kuiprux.spbeat.SpBeAt;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;

public class MapManager {
	
	private SpBeAt game;
	
	private List<Beatmap> mapList;
	
	public MapManager(SpBeAt game) {
		this.game = game;
		this.mapList = new ArrayList<>();
	}
	
	public SpBeAt getGame() {
		return game;
	}
}
