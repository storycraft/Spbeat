package cf.kuiprux.spbeat.game;

import cf.kuiprux.spbeat.SpBeat;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.gui.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class PlayManager {

	private SpBeat game;
	private Beatmap beatmap;
	private boolean isPlaying;

	private Music player;

	private PlayScreen screen;
	
	public PlayManager(SpBeat game) {
		this.game = game;
		this.isPlaying = false;
		this.screen = null;
		this.player = null;
	}

	public SpBeat getGame() {
		return game;
	}

	public PlayScreen getPlayScreen() {
		return screen;
	}

	public Beatmap getBeatmap() {
		return beatmap;
	}

	//재생 붙가능일시 false 반환
	public boolean play(Beatmap map){
		stop();
		this.isPlaying = true;

		this.beatmap = map;
		try {
			this.player = Gdx.audio.newMusic(Gdx.files.absolute(MapManager.SONG_PATH.resolve(map.getSongPath()).toFile().getAbsolutePath()));
		} catch (Exception e) {
			System.out.println("재생 중 " + getCurrentTime() + " 위치에서 오류가 발생했습니다. " + e.getLocalizedMessage());
			return false;
		}

		player.play();

		return true;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public float getCurrentTime() {
		if (player == null)
			return -1;

		return player.getPosition();
	}

	public void stop(){
		if (isPlaying()) {
			player.stop();
			isPlaying = false;
		}
	}
}
