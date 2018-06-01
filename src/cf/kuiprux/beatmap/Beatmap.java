package cf.kuiprux.beatmap;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import cf.kuiprux.game.Event;
import cf.kuiprux.game.Note;

public class Beatmap {

	Image logo;
	String name;
	String description;
	String author;
	String songPath;
	int difficulty;
	int defaultBpm;
	
	double offset;
	//bpm 전환, 배경 등의 정보를 갖고 있음
	List<Event> eventList = new ArrayList<>();
	//노트 리스트
	List<Note> beats = new ArrayList<>();
	
}