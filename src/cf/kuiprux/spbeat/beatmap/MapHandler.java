package cf.kuiprux.spbeat.beatmap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cf.kuiprux.spbeat.beatmap.parser.NewMapLoader;

public class MapHandler {
	
	private NewMapLoader mapLoader = new NewMapLoader(new File("maps"));
	private List<BeatMap[][]> mapList;
	
	public void loadMaps() {
		this.mapList = new ArrayList<BeatMap[][]>();
		mapLoader.loadMaps();
		List<BeatMap> BeatMaps = mapLoader.getMaps();
		for(int i = 0; i <= BeatMaps.size()/12; i++) {
			BeatMap[][] BeatMapArray = new BeatMap[4][3];
			for(int j = 0; j < 12; j++) {
				if(i*12+j == BeatMaps.size()) break;
				BeatMapArray[j%4][j/4] = BeatMaps.get(i*12+j);
			}
			this.mapList.add(BeatMapArray);
		}
		if(this.mapList.size() == 0) {
			this.mapList.add(new BeatMap[4][3]);
		}
	}
	
	public int getSize() {
		return mapList.size();
	}
	
	public BeatMap[][] getMaps(int index) {
		if(index >= mapList.size()) return null;
		return mapList.get(index);
	}
}
