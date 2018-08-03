package cf.kuiprux.spbeat.game.gui;

import cf.kuiprux.spbeat.game.PlayManager;
import cf.kuiprux.spbeat.game.beatmap.*;
import cf.kuiprux.spbeat.game.gui.element.PlayShutter;
import cf.kuiprux.spbeat.game.gui.marker.HoldMarkerDrawable;
import cf.kuiprux.spbeat.game.gui.marker.IMarkerDrawable;
import cf.kuiprux.spbeat.game.gui.marker.MarkerDrawable;
import cf.kuiprux.spbeat.game.gui.marker.hit.HitStatement;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayScreen extends ScreenPreset {

	private Map<INote, IMarkerDrawable> drawableMap;

	private PlayManager playManager;
	private Beatmap beatmap;

	private PlayShutter shutter;

	private int currentCombo;

	public static final int NOTE_VISIBLE_TIME = 750;
	public static final int AFTER_VISIBLE_TIME = 250;

	public PlayScreen(PlayManager playManager, Beatmap beatmap) {
		this.playManager = playManager;
		this.beatmap = beatmap;

		this.drawableMap = new HashMap<>();

		this.shutter = new PlayShutter(this);
		this.currentCombo = 0;
	}

	public PlayManager getPlayManager() {
		return playManager;
	}

	public Beatmap getBeatmap() {
		return beatmap;
	}

	public int getCurrentCombo() {
		return currentCombo;
	}

	public void setCurrentCombo(int currentCombo) {
		this.currentCombo = currentCombo;
	}

	@Override
	public void onPress(int keyIndex) {

	}

	@Override
	public void onRelease(int keyIndex) {

	}

	@Override
	protected void onLoad() {
		getButtonPanel().getBackground().setColor(Color.gray);

		shutter.setLocation(0, 0);
		shutter.setSize(getButtonPanel().getBackground().getWidth(), getButtonPanel().getBackground().getHeight());

		getButtonPanel().getBackground().addChild(shutter);

		play();
	}

	@Override
	protected void onUnload() {
		getButtonPanel().getBackground().removeChild(shutter);
	}

	@Override
	protected void update(int delta) {
		long time = getPlayManager().getCurrentTime();
		List<INote> noteList = getVisibleNoteList(time);

		for (INote note : noteList){
			IMarkerDrawable drawable = getNoteDrawable(note);

			if (drawable.getHitStatement().isCalculated()) {
				if (drawable.isLoaded()){
					drawable.expire();
				}

				continue;
			}

			if (!drawable.isLoaded()){
				getButtonPanel().getButtonAreaAt(note.getNoteIndex()).addChild(drawable);
			}
		}

		if (getPlayManager().getCurrentTime() - NOTE_VISIBLE_TIME - AFTER_VISIBLE_TIME < 0)
			return;

		List<INote> lastNoteList = getVisibleNoteList(getPlayManager().getCurrentTime() - NOTE_VISIBLE_TIME - AFTER_VISIBLE_TIME);
		for (INote note : lastNoteList){
			IMarkerDrawable drawable = getNoteDrawable(note);
			HitStatement hitStatement = drawable.getHitStatement();

			if (!hitStatement.isCalculated()) {
				hitStatement.calculateState(time);
			}

			if (!note.isOnScreen(time)){
				drawable.expire();
			}
		}
	}

	public void play(){
		if (getPlayManager().isPlaying())
			return;

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

	protected IMarkerDrawable getNoteDrawable(INote note){
		if (drawableMap.containsKey(note))
			return drawableMap.get(note);

		IMarkerDrawable markerDrawable;

		if (note instanceof Note){
			markerDrawable = new MarkerDrawable((Note) note, getPlayManager());
		}
		else if (note instanceof HoldNote){
			markerDrawable = new HoldMarkerDrawable((HoldNote) note, getPlayManager());
		}
		else{
			return null;
		}

		drawableMap.put(note, markerDrawable);
		return markerDrawable;
	}

}