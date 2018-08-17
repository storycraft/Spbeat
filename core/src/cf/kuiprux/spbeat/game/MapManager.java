package cf.kuiprux.spbeat.game;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import cf.kuiprux.spbeat.SpBeat;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;

public class MapManager {

	public static final Path SONG_PATH;

	static {
		SONG_PATH = Paths.get("fumens");
	}
	
	private SpBeat game;
	private IMapManagerListener listener;
	
	private List<Beatmap> mapList;
	
	public MapManager(SpBeat game) {
		this.game = game;
		this.mapList = new ArrayList<>();
	}
	
	public SpBeat getGame() {
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
