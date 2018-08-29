package cf.kuiprux.spbeat.game.play;

import cf.kuiprux.spbeat.game.PlayManager;
import cf.kuiprux.spbeat.game.beatmap.*;
import cf.kuiprux.spbeat.game.gui.BeatmapSelectScreen;
import cf.kuiprux.spbeat.game.gui.ButtonPanel;
import cf.kuiprux.spbeat.game.gui.ScreenPreset;
import cf.kuiprux.spbeat.game.gui.element.PlayShutter;
import cf.kuiprux.spbeat.game.gui.marker.HoldNoteDrawable;
import cf.kuiprux.spbeat.game.gui.marker.INoteDrawable;
import cf.kuiprux.spbeat.game.gui.marker.NoteDrawable;
import cf.kuiprux.spbeat.game.gui.marker.hit.HitState;
import cf.kuiprux.spbeat.game.gui.marker.hit.IHitStatement;
import cf.kuiprux.spbeat.gui.IDrawable;
import cf.kuiprux.spbeat.gui.element.Square;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayScreen extends ScreenPreset {

	private Map<INote, INoteDrawable> drawableMap;
	private List<INote> addedNoteList;

	private PlayManager playManager;
	private Beatmap beatmap;

	private PlayShutter shutter;

	private Square firstNoteNoti;

	private PlayStatus playStatus;

	public static final int NOTE_VISIBLE_TIME = 750;
	public static final int AFTER_VISIBLE_TIME = 250;

	public PlayScreen(PlayManager playManager, Beatmap beatmap) {
		this.playManager = playManager;
		this.beatmap = beatmap;

		this.drawableMap = new HashMap<>();

		this.shutter = new PlayShutter(this);
		this.firstNoteNoti = new Square(0, 0, ButtonPanel.BUTTON_WIDTH, ButtonPanel.BUTTON_HEIGHT);

		this.playStatus = new PlayStatus(getBeatmap());
	}

	public PlayManager getPlayManager() {
		return playManager;
	}

	public Beatmap getBeatmap() {
		return beatmap;
	}

	public PlayStatus getPlayStatus() {
		return playStatus;
	}

	@Override
	public void onPress(int keyIndex) {
		for (INote note : addedNoteList){
			if (note.getNoteIndex() == keyIndex){
				getNoteDrawable(note).onkeyDown(getPlayManager().getCurrentTime());
				break;
			}
		}
	}

	@Override
	public void onRelease(int keyIndex) {
		for (INote note : addedNoteList){
			if (note.getNoteIndex() == keyIndex){
				getNoteDrawable(note).onkeyUp(getPlayManager().getCurrentTime());
				break;
			}
		}
	}

	@Override
	protected void onLoad() {
		getButtonPanel().getBackground().setColor(new Color(33, 33, 33));

		shutter.setSize(getButtonPanel().getBackground().getWidth(), getButtonPanel().getBackground().getHeight());

		try {
			firstNoteNoti.setTexture(new Image(getGame().getResourceManager().getStream("texture.marker.start"), "texture.marker.start", false));
		} catch (SlickException e) {
			System.out.println("텍스쳐 texture.marker.start 로드가 실패 했습니다.");
		}

		firstNoteNoti.setColor(Color.white);

		INote firstNote = getBeatmap().getFirstNote();
		if (firstNote != null)
			getButtonPanel().getButtonAreaAt(firstNote.getNoteIndex()).addChild(firstNoteNoti);

		getButtonPanel().getBackground().addChild(shutter);

		play();
	}

	@Override
	protected void onUnload() {
		clear();
	}

	@Override
	protected void update(int delta) {
		long time = getPlayManager().getCurrentTime();

		addedNoteList = getVisibleNoteList(time);

		if (firstNoteNoti.isLoaded() && !addedNoteList.isEmpty()){
			firstNoteNoti.expire();
		}
		else if (isFinished(time)){
			onFinished();
		}

		for (INote note : addedNoteList){
			INoteDrawable drawable = getNoteDrawable(note);
			IHitStatement hitStatement = drawable.getHitStatement();

			if (hitStatement.isCalculated()) {
				if (drawable.isLoaded()){
					judgeNote(drawable);
					drawable.expire();
				}

				continue;
			}
			else if (!drawable.isLoaded()){
				ButtonPanel.ButtonArea area = getButtonPanel().getButtonAreaAt(note.getNoteIndex());
				if (area != null)
					area.addChild(drawable);
			}
		}

		if (time - NOTE_VISIBLE_TIME - AFTER_VISIBLE_TIME < 0)
			return;

		List<INote> lastNoteList = getVisibleNoteList(time - NOTE_VISIBLE_TIME - AFTER_VISIBLE_TIME);
		for (INote note : lastNoteList){
			INoteDrawable drawable = getNoteDrawable(note);
			IHitStatement hitStatement = drawable.getHitStatement();

			if (drawable.isLoaded()){
				drawable.expire();
			}

			if (!hitStatement.isCalculated()) {
				hitStatement.calculateState(time);

				judgeNote(drawable);
			}
		}
	}

	protected void onFinished() {
		this.getPlayStatus().setPlaying(false);
		getScreenManager().setCurrentScreen(new BeatmapSelectScreen(getGame().getMapManager()));
	}

	public boolean isFinished(long time) {
		List<BeatList> beatListList = getBeatmap().getBeatListArray();

		if (beatListList.size() <= 0)
			return true;

		int i = 1;
		BeatList lastBeatList;
		while ((lastBeatList = beatListList.get(beatListList.size() - i++)).getNoteList().size() <= 0);

		List<INote> noteList = lastBeatList.getNoteList();
		if (time > noteList.get(noteList.size() - 1).getExactTime() + AFTER_VISIBLE_TIME + 2000)
			return true;

		return false;
	}

	protected void clear() {
		if (shutter.isLoaded())
			shutter.expire();

		if (firstNoteNoti.isLoaded())
			firstNoteNoti.expire();

		for (IDrawable drawable : drawableMap.values()){
			if (drawable.isLoaded())
				drawable.expire();
		}
	}

	protected void judgeNote(INoteDrawable drawable){
		IHitStatement hitStatement = drawable.getHitStatement();

		switch (hitStatement.getHitState()) {
			case MISS:
				getPlayStatus().setCurrentCombo(0);
				getPlayStatus().setMissCount(getPlayStatus().getMissCount() + 1);
				break;

			case PERFECT:
				getPlayStatus().setCurrentCombo(getPlayStatus().getCurrentCombo() + 1);
				getPlayStatus().setPerfectCount(getPlayStatus().getPerfectCount() + 1);
				break;

			case GREAT:
				getPlayStatus().setCurrentCombo(getPlayStatus().getCurrentCombo() + 1);
				getPlayStatus().setGreatCount(getPlayStatus().getGreatCount() + 1);
				break;

			case GOOD:
				getPlayStatus().setCurrentCombo(getPlayStatus().getCurrentCombo() + 1);
				getPlayStatus().setGoodCount(getPlayStatus().getGoodCount() + 1);
				break;

			case POOR:
				getPlayStatus().setCurrentCombo(getPlayStatus().getCurrentCombo() + 1);
				getPlayStatus().setPoorCount(getPlayStatus().getPoorCount() + 1);
				break;
		}

		updateScoreText();
	}

	protected void updateScoreText() {

	}

	public void play(){
		if (getPlayManager().isPlaying())
			return;

		this.getPlayStatus().setPlaying(true);
		getPlayManager().play(getBeatmap());
	}

	protected List<INote> getVisibleNoteList(long time){
		List<INote> list = new ArrayList<>();

		for (BeatList beatList : getBeatmap().getBeatListArray()){
			float beatTiming = beatList.getBeatTime() - time;
			if (beatTiming < -AFTER_VISIBLE_TIME)
				continue;
			else if (beatTiming > NOTE_VISIBLE_TIME)
				break;

			for (INote note : beatList.getNoteList()){
				if (note.isOnScreen(time)) {
					list.add(note);
				}
			}

		}

		return list;
	}

	protected INoteDrawable getNoteDrawable(INote note){
		if (drawableMap.containsKey(note))
			return drawableMap.get(note);

		INoteDrawable markerDrawable;

		if (note.getClass().equals(Note.class)){
			markerDrawable = new NoteDrawable(note, getPlayManager());
		}
		else if (note.getClass().equals(HoldNote.class)){
			markerDrawable = new HoldNoteDrawable((HoldNote) note, getPlayManager());
		}
		else{
			return null;
		}

		drawableMap.put(note, markerDrawable);
		return markerDrawable;
	}
}