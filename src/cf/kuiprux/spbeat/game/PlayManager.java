package cf.kuiprux.spbeat.game;

import org.newdawn.slick.openal.OpenALStreamPlayer;

import cf.kuiprux.spbeat.beatmap.BeatMap;

public class PlayManager {
	
	private BeatMap currentMap;
	private OpenALStreamPlayer songPlayer;
	
	public PlayManager(BeatMap currentMap) {
		this.currentMap = currentMap;
		//this.songPlayer
	}

	public BeatMap getCurrentMap() {
		return currentMap;
	}
	
	public boolean isPlaying() {
		return songPlayer.done();
	}
	
	public float getCurrentTime() {
		return songPlayer.getPosition();
	}
	
	public void play() {
		
	}
	
	public void stop() {
		
	}
	
}
