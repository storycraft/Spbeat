package cf.kuiprux.spbeat.game.gui;

import cf.kuiprux.spbeat.game.PlayManager;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;

public class PlayScreen extends ScreenPreset {
	
	private PlayManager playManager;
	private Beatmap beatmap;
	
	public PlayScreen(PlayManager playManager, Beatmap beatmap) {
		this.playManager = playManager;
		this.beatmap = beatmap;
	}
	
	public PlayManager getPlayManager() {
		return playManager;
	}

	@Override
	public void onPress(int keyIndex) {
		
	}

	@Override
	public void onUp(int keyIndex) {
		
	}

	@Override
	protected void onLoad() {
		
	}

	@Override
	protected void onUnload() {
		
	}

	@Override
	protected void update(int delta) {
		
	}

}
