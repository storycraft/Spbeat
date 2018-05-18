package cf.kuiprux;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class BeatMap {

	Image logo;
	String name;
	String description;
	String author;
	int difficulty;
	
	double offset;
	List<Content<Double>> bpm = new ArrayList<Content<Double>>();
	List<Content<Button>> beats = new ArrayList<Content<Button>>();
	
}

class Content<T> {
	int startTime;
	int lastTime;
	int endTime;
	T content;
	
	Content(int startTime, int lastTime, int endTime, T content) {
		this.startTime = startTime;
		this.lastTime = lastTime;
		this.endTime = endTime;
		this.content = content;
	}
}