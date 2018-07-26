package cf.kuiprux.spbeat.game.gui;

import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.ResourceManager;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.gui.element.BeatmapInfoDrawable;
import cf.kuiprux.spbeat.game.gui.element.BeatmapSelectBox;
import cf.kuiprux.spbeat.gui.Drawable;
import cf.kuiprux.spbeat.util.AsyncTask;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.newdawn.slick.Color;

import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.element.Square;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BeatmapSelectScreen extends ScreenPreset {

	//한 페이지당 표시할 맵의 수
	private static final int MAPS_ON_A_PAGE = 12;

	private static final int SETTINGS = 12;
	private static final int LAST_PAGE = 13;
	private static final int NEXT_PAGE = 14;
	private static final int PLAY = 15;

	private MapManager mapManager;
	private int selectedIndex;

	private int beatmapPage;
	private boolean pageChanged;

	private boolean selected;

	private BeatmapSelectBox selectHighlight;

	private Square settingButton;
	private Square previousButton;
	private Square nextButton;
	private Square playButton;

	private Player previewPlayer;

	public BeatmapSelectScreen(MapManager mapManager) {
		this.mapManager = mapManager;
		this.previewPlayer = null;
		this.selectHighlight = new BeatmapSelectBox(0, 0, 100, 100);
		selectHighlight.setColor(Color.white);
		selectHighlight.setVisible(false);

		this.settingButton = new Square(0, 0, 100, 100);
		settingButton.setColor(Color.white);
		this.previousButton = new Square(0, 0, 100, 100);
		previousButton.setColor(Color.white);
		this.nextButton = new Square(0, 0, 100, 100);
		nextButton.setColor(Color.white);
		this.playButton = new Square(0, 0, 100, 100);
		playButton.setColor(Color.white);

		this.selected = false;
		this.selectedIndex = 0;
		this.beatmapPage = 0;
	}

	@Override
	public void onPress(int keyIndex) {
		int y = keyIndex / 4;
		int x = keyIndex % 4;

		if (!this.selected)
			this.selected = true;
		this.selectedIndex = keyIndex;

		if (!selectHighlight.isVisible()) {
			selectHighlight.setVisible(true);
			getButtonPanel().addChild(selectHighlight);
		}

		selectHighlight.moveTo(getButtonPanel().getButtonPosX(x), getButtonPanel().getButtonPosY(y), EasingType.LINEAR, 10);

		//맨 마지막 줄엔 툴바 표시
		if (keyIndex > MAPS_ON_A_PAGE){
			switch (keyIndex){
				case SETTINGS:
					if (getBeatmapPage() < getMaxPage())
						setBeatmapPage(getBeatmapPage() + 1);
					break;

				case NEXT_PAGE:
					if (getBeatmapPage() < getMaxPage())
						setBeatmapPage(getBeatmapPage() + 1);
					break;
				case LAST_PAGE:
					if (getBeatmapPage() > 0)
						setBeatmapPage(getBeatmapPage() - 1);
					break;
				case PLAY:
					break;
			}

			return;
		}

		Beatmap map = getBeatmap(getBeatmapPage(), keyIndex);
		if (map == null)
			return;

		if (getPreviewPlayer() != null)
			getPreviewPlayer().close();

		try {
			previewPlayer = new Player(new FileInputStream(MapManager.SONG_PATH.resolve(map.getSongPath()).toAbsolutePath().toFile()));
			new AsyncTask<>(new AsyncTask.AsyncCallable<Void>() {
				@Override
				public Void get() {
					try {
						getPreviewPlayer().play();
					} catch (JavaLayerException e) {
						System.out.println("채보 미리 듣기 재생 실패 " + e.getLocalizedMessage());
					}
					return null;
				}
			}).run();

		} catch (Exception e) {
			System.out.println("채보 미리 듣기 로드 실패 " + e.getLocalizedMessage());
		}
	}

	public int getBeatmapPage() {
		return beatmapPage;
	}

	public Player getPreviewPlayer() {
		return previewPlayer;
	}

	public void setBeatmapPage(int beatmapPage) {
		this.beatmapPage = beatmapPage;
		this.pageChanged = true;
	}

	public Beatmap getBeatmap(int page, int index){
		List<Beatmap> list = mapManager.getMapList();

		int listIndex = index + page * MAPS_ON_A_PAGE;

		if (list.size() <= listIndex)
			return null;

		return list.get(listIndex);
	}

	public int getMaxIndex(){
		if (getBeatmapPage() < getMaxPage())
			return MAPS_ON_A_PAGE;
		else if (getBeatmapPage() > getMaxPage())
			return 0;

		List<Beatmap> list = new ArrayList<>();
		return list.size() % MAPS_ON_A_PAGE;
	}

	public int getMaxPage(){
		List<Beatmap> list = mapManager.getMapList();

		return (int) Math.ceil((float) list.size() / MAPS_ON_A_PAGE);
	}

	public boolean isSelected() {
		return selected;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	@Override
	public void onUp(int keyIndex) {

	}

	@Override
	protected void onLoad() {
		getButtonPanel().getBackground().setColor(Color.white);
		this.pageChanged = true;

		ResourceManager manager = getGame().getResourceManager();

		try {
			this.settingButton.setTexture(new Image(manager.getStream("texture.btn.settings"), "texture.btn.settings", false));
			this.previousButton.setTexture(new Image(manager.getStream("texture.btn.previous"), "texture.btn.previous", false));
			this.nextButton.setTexture(new Image(manager.getStream("texture.btn.next"), "texture.btn.next", false));
			this.playButton.setTexture(new Image(manager.getStream("texture.btn.play"), "texture.btn.play", false));
		} catch (SlickException e) {
			System.out.println("리소스 로드 오류 " + e.getLocalizedMessage());
		}


		getButtonPanel().getButtonAreaAt(SETTINGS).addChild(settingButton);
		getButtonPanel().getButtonAreaAt(LAST_PAGE).addChild(previousButton);
		getButtonPanel().getButtonAreaAt(NEXT_PAGE).addChild(nextButton);
		getButtonPanel().getButtonAreaAt(PLAY).addChild(playButton);

	}

	@Override
	protected void onUnload() {

	}

	@Override
	protected void update(int delta) {
		if (pageChanged) {
			this.pageChanged = false;
			updatePage();
		}
	}

	private void updatePage() {
		ButtonPanel panel = getButtonPanel();
		for (int i = 0; i < MAPS_ON_A_PAGE; i++){
			ButtonPanel.ButtonArea area = panel.getButtonAreaAt(i);
			Beatmap map = getBeatmap(getBeatmapPage(), i);

			for (Drawable drawable : area.getChildren()) {
				drawable.fadeOut(EasingType.LINEAR,  250).expire();
			}

			if (map == null)
				continue;

			BeatmapInfoDrawable infoDrawable = new BeatmapInfoDrawable(map);
			infoDrawable.setOpacity(0);
			infoDrawable.fadeIn(EasingType.LINEAR, 500);

			area.addChild(infoDrawable);
		}
	}

}
