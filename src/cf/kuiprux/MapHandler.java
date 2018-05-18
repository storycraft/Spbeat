package cf.kuiprux;

import java.io.File;
import java.io.IOException;

public class MapHandler {

	FileHandler fh = new FileHandler();
	
	public void loadMaps(File root) {
		if(!root.exists()) root.mkdirs();
		
		File[] files = root.listFiles();
		for(File aFile : files) {
			if(aFile.isDirectory()) loadMaps(aFile);
			else loadAMap(aFile);
		}
	}
	
	private void loadAMap(File file) {
		try {
			String content = fh.readFile(file);
			
		} catch (IOException e) {
			System.out.println("An error occurred during loading " + file.getAbsolutePath());
			e.printStackTrace();
		}
	}
}
