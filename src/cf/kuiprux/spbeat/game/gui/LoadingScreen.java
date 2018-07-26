package cf.kuiprux.spbeat.game.gui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.ResourceManager;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.loader.BeatmapLoader;
import cf.kuiprux.spbeat.game.loader.ResourceLoader;
import org.newdawn.slick.Color;

import cf.kuiprux.spbeat.game.gui.ButtonPanel.ButtonArea;
import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.element.Square;

public class LoadingScreen extends ScreenPreset {

	@Override
	public void onPress(int keyIndex) {
		
	}

	@Override
	public void onUp(int keyIndex) {
		
	}

	@Override
	protected void onLoad() {
		BeatmapLoader beatmapLoader = new BeatmapLoader(getGame().getMapManager());
		ResourceLoader resourceLoader = new ResourceLoader(getGame().getResourceManager());

		try {
			//와!
			CompletableFuture.allOf(beatmapLoader.loadAll(MapManager.SONG_PATH).run(), resourceLoader.loadAll(ResourceManager.RESOURCE_PATH).run()).get();
		} catch (Exception e) {
			System.out.println("리소스 로드가 실패 했습니다. " + e.getLocalizedMessage());
			e.printStackTrace();
		}

		getScreenManager().setCurrentScreen(new BeatmapSelectScreen(getGame().getMapManager()));
	}

	@Override
	protected void onUnload() {
		
	}

	@Override
	protected void update(int delta) {
		
	}

}
