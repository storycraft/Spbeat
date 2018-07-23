package cf.kuiprux.spbeat.game.gui;

import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.gui.element.BeatmapInfoDrawable;
import cf.kuiprux.spbeat.gui.Drawable;
import org.newdawn.slick.Color;

import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.element.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BeatmapSelectScreen extends ScreenPreset {

	//한 페이지당 표시할 맵의 수
	private static final int MAPS_ON_A_PAGE = 12;

	private MapManager mapManager;
	private int selectedIndex;

	private int beatmapPage;
	private boolean pageChanged;

	private boolean selected;
	private Square selectHighlight;

	public BeatmapSelectScreen(MapManager mapManager) {
		this.mapManager = mapManager;
		this.selectHighlight = new Square(0, 0, 100, 100);
		selectHighlight.setBorderColor(Color.red);
		selectHighlight.setBorderWidth(3);
		selectHighlight.setVisible(false);

		this.selected = false;
		this.selectedIndex = 0;
		this.beatmapPage = 0;
	}

	@Override
	public void onPress(int keyIndex) {
		int y = keyIndex / 4;
		int x = keyIndex % 4;

		//맨 마지막 줄엔 비트맵을 표시 하지 않음
		if (y > 2)
			return;

		if (!this.selected)
			this.selected = true;
		this.selectedIndex = keyIndex;

		selectHighlight.setVisible(true);

		getButtonPanel().addChild(selectHighlight);
		selectHighlight.moveTo(getButtonPanel().getButtonPosX(x), getButtonPanel().getButtonPosY(y), EasingType.LINEAR, 100);
	}

	public int getBeatmapPage() {
		return beatmapPage;
	}

	public void setBeatmapPage(int beatmapPage) {
		this.beatmapPage = beatmapPage;
		this.pageChanged = true;
	}

	public Beatmap getBeatmap(int page, int index){
		List<Beatmap> list = new ArrayList<>();

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
		List<Beatmap> list = new ArrayList<>();

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
		getButtonPanel().getBackground().setColor(Color.cyan);
		this.pageChanged = true;

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

			if (map == null)
				break;

			BeatmapInfoDrawable infoDrawable = new BeatmapInfoDrawable(map);
			infoDrawable.setOpacity(0);

			for (Drawable drawable : area.getChildren()) {
				drawable.fadeOut(EasingType.LINEAR,  250).expire();
			}
			infoDrawable.wait(250).fadeIn(EasingType.LINEAR, 250);

			area.addChild(infoDrawable);
		}
	}

}
