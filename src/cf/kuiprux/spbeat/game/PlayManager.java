package cf.kuiprux.spbeat.game;

import cf.kuiprux.spbeat.SpBeAt;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.play.PlayScreen;
import cf.kuiprux.spbeat.util.AsyncTask;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;

public class PlayManager {

	private SpBeAt game;
	private Beatmap beatmap;
	private boolean isPlaying;

	private Player player;

	private PlayScreen screen;
	
	public PlayManager(SpBeAt game) {
		this.game = game;
		this.isPlaying = false;
		this.screen = null;
		this.player = null;
	}

	public SpBeAt getGame() {
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
			this.player = new Player(new FileInputStream(MapManager.SONG_PATH.resolve(map.getSongPath()).toFile()));
		} catch (Exception e) {
			System.out.println("재생 중 " + getCurrentTime() + " 위치에서 오류가 발생했습니다. " + e.getLocalizedMessage());
			return false;
		}

		new AsyncTask<>(new AsyncTask.AsyncCallable<Void>() {
			@Override
			public Void get() {
				try {
					player.play();
				} catch (JavaLayerException e) {
					System.out.println("재생 중 " + getCurrentTime() + " 위치에서 오류가 발생했습니다. " + e.getLocalizedMessage());
				}
				return null;
			}
		}).run();

		return true;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public long getCurrentTime() {
		if (player == null)
			return -1;

		return player.getPosition();
	}

	public void stop(){
		if (isPlaying()) {
			player.close();
			isPlaying = false;
		}
	}
}
