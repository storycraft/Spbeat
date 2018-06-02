package cf.kuiprux.beatmap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cf.kuiprux.FileHandler;
import cf.kuiprux.beatmap.BeatMap;
import cf.kuiprux.beatmap.parser.NewMapLoader;

public class MapHandler {
	
	private NewMapLoader mapLoader = new NewMapLoader(new File("BeatMaps"));
	private List<BeatMap[][]> BeatMaps;
	
	public void loadBeatMaps() {
		this.BeatMaps = new ArrayList<BeatMap[][]>();
		mapLoader.loadMaps();
		List<BeatMap> BeatMaps = mapLoader.getMaps();
		for(int i = 0; i <= BeatMaps.size()/12; i++) {
			BeatMap[][] BeatMapArray = new BeatMap[4][3];
			for(int j = 0; j < 12; j++) {
				if(i*12+j == BeatMaps.size()) break;
				BeatMapArray[j%4][j/4] = BeatMaps.get(i*12+j);
			}
			this.BeatMaps.add(BeatMapArray);
		}
		if(this.BeatMaps.size() == 0) {
			this.BeatMaps.add(new BeatMap[4][3]);
		}
	}
	
	public int getSize() {
		return BeatMaps.size();
	}
	
	public BeatMap[][] getBeatMaps(int index) {
		if(index >= BeatMaps.size()) return null;
		return BeatMaps.get(index);
	}
}
