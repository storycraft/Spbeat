package cf.kuiprux.spbeat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cf.kuiprux.spbeat.object.Map;

public class MapHandler {
	
	private MapLoader mapLoader = new MapLoader(new File("maps"));
	private List<Map[][]> maps;
	
	public void loadMaps() {
		this.maps = new ArrayList<Map[][]>();
		mapLoader.loadMaps();
		List<Map> maps = mapLoader.getMaps();
		for(int i = 0; i <= maps.size()/12; i++) {
			Map[][] mapArray = new Map[4][3];
			for(int j = 0; j < 12; j++) {
				if(i*12+j == maps.size()) break;
				mapArray[j%4][j/4] = maps.get(i*12+j);
			}
			this.maps.add(mapArray);
		}
		if(this.maps.size() == 0) {
			this.maps.add(new Map[4][3]);
		}
	}
	
	public int getSize() {
		return maps.size();
	}
	
	public Map[][] getMaps(int index) {
		if(index >= maps.size()) return null;
		return maps.get(index);
	}
}
