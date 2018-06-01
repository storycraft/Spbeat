package cf.kuiprux.game;

public class Event {

	private EventType eventType;
	
	private int triggerTime;
	
	public Event(EventType eventType, int triggerTime) {
		this.eventType = eventType;
		this.triggerTime = triggerTime;
	}
	
	public EventType getEventType() {
		return eventType;
	}

	public int getTriggerTime() {
		return triggerTime;
	}
}
