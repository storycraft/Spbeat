package cf.kuiprux.spbeat.game;

import cf.kuiprux.spbeat.SpBeAt;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.gui.PlayScreen;
import cf.kuiprux.spbeat.util.AsyncTask;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayManager {

	private SpBeAt game;
	private Beatmap beatmap;
	private boolean isPlaying;

	private Player player;

	private PlayScreen screen;

	private long currentTime;
	
	public PlayManager(SpBeAt game) {
		this.game = game;
		this.isPlaying = false;
		this.screen = null;
		this.currentTime = -1;
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
			this.player = new Player(new FileInputStream(map.getSongPath()));
		} catch (JavaLayerException e) {
			System.out.println("재생 중 " + getCurrentTime() + " 위치에서 오류가 발생했습니다. " + e.getLocalizedMessage());
			return false;
		} catch (FileNotFoundException e) {
			System.out.println(map.getSongPath() + " 이 존재 하지 않습니다.");
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
		});
		getGame().getScreenManager().setCurrentScreen(new PlayScreen(this, getBeatmap()));

		return true;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public long getCurrentTime() {
		return currentTime;
	}

	public void stop(){
		if (isPlaying)
			player.close();
	}
}
