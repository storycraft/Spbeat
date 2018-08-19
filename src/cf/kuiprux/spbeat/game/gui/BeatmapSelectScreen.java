package cf.kuiprux.spbeat.game.gui;

import cf.kuiprux.spbeat.game.MainThreadExecutor;
import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.ResourceManager;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.gui.element.BeatmapInfoDrawable;
import cf.kuiprux.spbeat.game.gui.element.BeatmapSelectBox;
import cf.kuiprux.spbeat.game.play.PlayScreen;
import cf.kuiprux.spbeat.gui.IDrawable;
import cf.kuiprux.spbeat.gui.effect.IAnimatable;
import org.newdawn.slick.Color;

import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.element.Square;
import org.newdawn.slick.Image;

import java.util.ArrayList;
import java.util.List;

public class BeatmapSelectScreen extends ScreenPreset {

	//한 페이지당 표시할 맵의 수
	private static final int MAPS_ON_A_PAGE = 12;

	private static final int SETTINGS = 12;
	private static final int LAST_PAGE = 13;
	private static final int NEXT_PAGE = 14;
	private static final int PLAY = 15;

	private MapManager mapManager;
	private int selectedIndex;
	private Beatmap selectedMap;

	private int beatmapPage;
	private boolean pageChanged;

	private boolean selected;

	private BeatmapSelectBox selectHighlight;

	private Image defaultJacket;

	private Square settingButton;
	private Square previousButton;
	private Square nextButton;
	private Square playButton;

	public BeatmapSelectScreen(MapManager mapManager) {
		this.mapManager = mapManager;
		this.selectHighlight = new BeatmapSelectBox(0, 0, 100, 100, mapManager.getGame().getFontManager().getFontByName("나눔바른고딕"));
		selectHighlight.getFadeBox().setColor(Color.lightGray);
		selectHighlight.getSongTitleText().setColor(Color.black);
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
		else if (getSelectedIndex() == keyIndex){
			onDoublePress(keyIndex);
			return;
		}

		this.selectedIndex = keyIndex;

		if (!selectHighlight.isLoaded()) {
			selectHighlight.setVisible(true);
			getButtonPanel().addChild(selectHighlight);
		}

		selectHighlight.moveTo(getButtonPanel().getButtonPosX(x), getButtonPanel().getButtonPosY(y), EasingType.LINEAR, 10);

		if (keyIndex >= MAPS_ON_A_PAGE){
			if (selectHighlight.getSongTitleText().getText() != "")
				selectHighlight.getSongTitleText().setText("");
			return;
		}

		Beatmap map = getBeatmap(getBeatmapPage(), keyIndex);

		if (map == null)
			return;

		this.selectedMap = map;
		selectHighlight.getSongTitleText().setText(map.getTitle());

		if (getGame().getPlayManager().getBeatmap() == null || getSelectedMap().getSongPath() != getGame().getPlayManager().getBeatmap().getSongPath())
			getGame().getPlayManager().play(getSelectedMap());
	}

	public void onDoublePress(int keyIndex){
		//맨 마지막 줄엔 툴바 표시
		if (keyIndex >= MAPS_ON_A_PAGE){
			switch (keyIndex){
				case SETTINGS:

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
					if (getSelectedMap() != null){
						clear();
						MainThreadExecutor.addTask(() -> getScreenManager().setCurrentScreen(new PlayScreen(getGame().getPlayManager(), getSelectedMap())));
					}
					break;
			}

			return;
		}
	}

	public int getBeatmapPage() {
		return beatmapPage;
	}

	public Beatmap getSelectedMap() {
		return selectedMap;
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
	public void onRelease(int keyIndex) {

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
			this.defaultJacket = new Image(manager.getStream("texture.jacket.default"), "texture.jacket.default", false);
		} catch (Exception e) {
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

			for (IDrawable drawable : area.getChildren()) {
				if (drawable instanceof IAnimatable) {
					((IAnimatable) drawable).fadeOut(EasingType.LINEAR, 250).expire();
				}
				else {
					drawable.expire();
				}
			}

			if (map == null)
				continue;

			BeatmapInfoDrawable infoDrawable = new BeatmapInfoDrawable(map, defaultJacket);
			infoDrawable.setOpacity(0);
			infoDrawable.fadeIn(EasingType.LINEAR, 500);
			selectHighlight.setVisible(false);

			area.addChild(infoDrawable);
		}
	}

	private void clear(){
		for (int i = 0; i < ButtonPanel.COLUMN * ButtonPanel.ROW; i++){
			ButtonPanel.ButtonArea area = getButtonPanel().getButtonAreaAt(i);
			for (IDrawable drawable : area.getChildren()) {
				if (drawable instanceof IAnimatable) {
					((IAnimatable) drawable).fadeOut(EasingType.LINEAR, 250).expire();
				}
				else {
					drawable.expire();
				}
			}
		}

		selectHighlight.expire();

		getGame().getPlayManager().stop();
	}

}
