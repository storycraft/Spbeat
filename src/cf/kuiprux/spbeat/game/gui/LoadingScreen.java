package cf.kuiprux.spbeat.game.gui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.BiConsumer;

import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.loader.BeatmapLoader;
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
		BeatmapLoader loader = new BeatmapLoader();

		try {
			//와!
			List<Beatmap> beatmapList = loader.loadAll(MapManager.SONG_PATH).run().get();

            for (Beatmap map : beatmapList){
                System.out.println("채보 " + map.getTitle() + " 가 추가 되었습니다.");
                getGame().getMapManager().addBeatmap(map);
            }

            getScreenManager().setCurrentScreen(new BeatmapSelectScreen(getGame().getMapManager()));
		} catch (Exception e) {
			System.out.println("비트맵 폴더가 존재 하지 않거나 손상 되었습니다. " + e.getLocalizedMessage());
		}
	}

	@Override
	protected void onUnload() {
		
	}

	@Override
	protected void update(int delta) {
		
	}

}
