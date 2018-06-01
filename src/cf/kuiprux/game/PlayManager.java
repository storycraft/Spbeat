package cf.kuiprux.game;

import org.newdawn.slick.openal.OpenALStreamPlayer;

import cf.kuiprux.beatmap.Beatmap;

public class PlayManager {
	
	private Beatmap currentMap;
	private OpenALStreamPlayer songPlayer;
	
	public PlayManager(Beatmap currentMap) {
		this.currentMap = currentMap;
		//this.songPlayer
	}

	public Beatmap getCurrentMap() {
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
