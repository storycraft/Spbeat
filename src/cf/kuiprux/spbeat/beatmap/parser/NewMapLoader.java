package cf.kuiprux.spbeat.beatmap.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cf.kuiprux.spbeat.FileHandler;
import cf.kuiprux.spbeat.beatmap.BasicInfo;
import cf.kuiprux.spbeat.beatmap.BeatMap;
import cf.kuiprux.spbeat.beatmap.Bpm;
import cf.kuiprux.spbeat.beatmap.Note;
import cf.kuiprux.spbeat.beatmap.PlayInfo;

public class NewMapLoader {

	private FileHandler fh = new FileHandler();
	private List<BeatMap> maps;
	
	private File root;
	
	JsonParser parser = new JsonParser();
	
	public static final String INFO_FILE = "info.txt";
	public static final String NOTES_FILE = "notes.txt";
	public static final String ICON_FILE = "icon.png";
	public static final String MUSIC_FILE = "music.ogg";
	
	public NewMapLoader(File root) {
		this.root = root;
	}
	
	public void loadMaps() {
		maps = new ArrayList<BeatMap>();
		if(!root.exists()) root.mkdirs();
		
		File[] files = root.listFiles();
		for(File aFile : files) {
			if(aFile.isDirectory()) loadAMap(aFile);
		}
	}
	
	private void loadAMap(File folder) {
		File[] files = folder.listFiles();
		File infoFile = null;
		File notesFile = null;
		File iconFile = null;
		File musicFile = null;
		
		for(File aFile : files) {
			if(aFile.isDirectory()) loadAMap(aFile);
			else {
				switch(aFile.getName()) {
				case INFO_FILE:
					infoFile = aFile;
					break;
				case NOTES_FILE:
					notesFile = aFile;
					break;
				case ICON_FILE:
					iconFile = aFile;
					break;
				case MUSIC_FILE:
					musicFile = aFile;
				}
			}
		}
		if (infoFile != null && notesFile != null && iconFile != null && musicFile != null) {
			try {
				parse(infoFile, notesFile, iconFile, musicFile);
			} catch (IOException | SlickException e) {
				System.out.println("An error occurred during loading " + folder.getAbsolutePath());
				e.printStackTrace();
			}
		}
	}
	
	public static final String NAME_INFO = "name:";
	public static final String DESCRIPTION_INFO = "description:";
	public static final String SONG_AUTHOR_INFO = "SongAuthor:";
	public static final String MAP_AUTHOR_INFO = "MapAuthor:";

	public static final String NAME_DEFAULT = "Untitled";
	public static final String DESCRIPTION_DEFAULT = "No Description";
	public static final String SONG_AUTHOR_DEFAULT = "None";
	public static final String MAP_AUTHOR_DEFAULT = "None";
	
	private void parse(File infoFile, File notesFile, File iconFile, File musicFile) throws IOException, SlickException{
		BasicInfo basicInfo = parseInfo(infoFile);
		PlayInfo playInfo = parseNotes(notesFile);
		System.out.println(iconFile.getAbsolutePath());
		Image image = new Image(iconFile.getAbsolutePath());
		maps.add(new BeatMap(basicInfo, playInfo, image, musicFile.getAbsolutePath()));
	}
	
	private BasicInfo parseInfo(File infoFile) throws IOException {
		String[] lines = fh.readFile(infoFile).split("\n");
		String name = NAME_DEFAULT;
		String description = DESCRIPTION_DEFAULT;
		String songAuthor = SONG_AUTHOR_DEFAULT;
		String mapAuthor = MAP_AUTHOR_DEFAULT;
		for(String line : lines) {
			if(line.startsWith(NAME_INFO)) name = line.substring(NAME_INFO.length()).trim();
			if(line.startsWith(DESCRIPTION_INFO)) description = line.substring(DESCRIPTION_INFO.length()).trim();
			if(line.startsWith(SONG_AUTHOR_INFO)) songAuthor = line.substring(SONG_AUTHOR_INFO.length()).trim();
			if(line.startsWith(MAP_AUTHOR_INFO)) mapAuthor = line.substring(MAP_AUTHOR_INFO.length()).trim();
		}
		return new BasicInfo(name, description, songAuthor, mapAuthor);
	}
	
	private PlayInfo parseNotes(File notesFile) throws IOException {
		String content = fh.readFile(notesFile);
		System.out.println(notesFile.getAbsolutePath());
		JsonObject rootObject = parser.parse(content).getAsJsonObject();
		long offset = 0;
		List<Bpm> bpms = new ArrayList<Bpm>();
		List<Note> notes = new ArrayList<Note>();
		if(rootObject.has("offset")) {
			offset = rootObject.get("offset").getAsLong();
		}
		if(rootObject.has("bpms")) {
			JsonArray bpmArray = rootObject.get("bpms").getAsJsonArray();
			for(JsonElement bpmElement : bpmArray) {
				JsonObject bpmObject = bpmElement.getAsJsonObject();
				if(bpmObject.has("bpm")) {
					int bpm = bpmObject.get("bpm").getAsInt();
					long until = -1;
					if(bpmObject.has("until")) {
						until = bpmObject.get("until").getAsLong();
					}
					bpms.add(new Bpm(bpm, until));
				}
			}
		}
		if(rootObject.has("notes")) {
			JsonArray noteArray = rootObject.get("notes").getAsJsonArray();
			for(JsonElement noteElement : noteArray) {
				JsonObject noteObject = noteElement.getAsJsonObject();
				if(noteObject.has("location") && noteObject.has("ready") && noteObject.has("time")) {
					int location;
					long ready, time;
					long duration = 0;
					location = noteObject.get("location").getAsInt();
					ready = noteObject.get("ready").getAsLong();
					time = noteObject.get("time").getAsLong();
					if(noteObject.has("duration"))
						duration = noteObject.get("duration").getAsLong();
					notes.add(new Note(location, ready, time, duration));
				}
			}
		}
		return new PlayInfo(offset, bpms, notes);
	}
	
	public List<BeatMap> getMaps() {
		return maps;
	}
	
}
