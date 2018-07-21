package cf.kuiprux.spbeat.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import cf.kuiprux.spbeat.SpBeAt;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;

public class MapManager {
	
	private SpBeAt game;
	private IMapManagerListener listener;
	
	private List<Beatmap> mapList;
	
	public MapManager(SpBeAt game) {
		this.game = game;
		this.mapList = new ArrayList<>();
	}
	
	public SpBeAt getGame() {
		return game;
	}

	public IMapManagerListener getListener(){
		return listener;
	}

	public void setListener(IMapManagerListener listener) {
		this.listener = listener;
	}

	public void addBeatmap(Beatmap map){
		if (contains(map))
			return;

		mapList.add(map);

		if (getListener() != null)
			getListener().onAdded(map);
	}

	public void removeBeatmap(Beatmap map){
		mapList.remove(map);

		if (getListener() != null)
			getListener().onRemoved(map);
	}

	public boolean contains(Beatmap map){
		return mapList.contains(map);
	}

	public List<Beatmap> getMapList(){
		return mapList;
	}
}
