package cf.kuiprux.beatmap;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class BeatMap {

	private BasicInfo basicInfo;
	private PlayInfo playInfo;
	private Image icon;
	private Image background; //TODO load this
	private Music music;
	
	private String musicPath;

	public BeatMap(BasicInfo basicInfo, PlayInfo playInfo, Image icon, String musicPath) {
		this.basicInfo = basicInfo;
		this.playInfo = playInfo;
		this.icon = icon;
		this.musicPath = musicPath;
	}

	public BasicInfo getBasicInfo() {
		return basicInfo;
	}

	public PlayInfo getPlayInfo() {
		return playInfo;
	}

	public Image getIcon() {
		return icon;
	}

	public Music getMusic() {
		return music;
	}
	
	public void loadMusic() {
		try {
			music = new Music(musicPath);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
