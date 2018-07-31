package cf.kuiprux.spbeat.game.gui;

import cf.kuiprux.spbeat.game.PlayManager;
import cf.kuiprux.spbeat.game.beatmap.*;
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

    public static final int NOTE_VISIBLE_TIME = 750;
	
	public PlayScreen(PlayManager playManager, Beatmap beatmap) {
		this.playManager = playManager;
		this.beatmap = beatmap;

		this.drawableMap = new HashMap<>();
	}
	
	public PlayManager getPlayManager() {
		return playManager;
	}

	public Beatmap getBeatmap() {
		return beatmap;
	}

	@Override
	public void onPress(int keyIndex) {
		
	}

	@Override
	public void onUp(int keyIndex) {
		
	}

	@Override
	protected void onLoad() {
		getButtonPanel().getBackground().setColor(Color.gray);
		play();
	}

	@Override
	protected void onUnload() {
		
	}

	@Override
	protected void update(int delta) {
		long time = getPlayManager().getCurrentTime();
		List<INote> noteList = getVisibleNoteList(time);

		for (INote note : noteList){
			IMarkerDrawable drawable = getNoteDrawable(note);

			if (drawable.getHitStatement().isCalculated()) {
				if (drawable.isLoaded()){
					getButtonPanel().getButtonAreaAt(note.getNoteIndex()).removeChild(drawable);
				}

				continue;
			}

			if (!drawable.isLoaded()){
				getButtonPanel().getButtonAreaAt(note.getNoteIndex()).addChild(drawable);
			}
		}

		if (noteList.size() <= 0)
			return;

		List<INote> lastNoteList = getVisibleNoteList(getPlayManager().getCurrentTime() - NOTE_VISIBLE_TIME);
		for (INote note : lastNoteList){
			HitStatement hitStatement = getNoteDrawable(note).getHitStatement();
			if (!hitStatement.isCalculated())
				hitStatement.calculateState(time);

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
			float beatTiming = time - beatList.getBeatTime();
	    	if (Math.abs(beatTiming) <= NOTE_VISIBLE_TIME)
	    		break;

	    	for (INote note : beatList.getNoteList()){
				float timing = time - note.getExactTime();
				if (timing >= NOTE_VISIBLE_TIME) {
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
            markerDrawable = new MarkerDrawable((Note) note);
        }
        else if (note instanceof HoldNote){
	        markerDrawable = new HoldMarkerDrawable((HoldNote) note);
        }
        else{
	        return null;
        }

        drawableMap.put(note, markerDrawable);
	    return markerDrawable;
    }

}
