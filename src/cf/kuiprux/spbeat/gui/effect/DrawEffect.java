package cf.kuiprux.spbeat.gui.effect;

// 애니메이션 효과
public abstract class DrawEffect implements IDrawEffect {
	
	private long startTime;
	private int duration;
	
	public DrawEffect(long startTime, int duration) {
		this.startTime = startTime;
		this.duration = duration;
	}
	
	public DrawEffect(int duration) {
		this(0, duration);
	}
	
	@Override
	public long getStartTime() {
		return startTime;
	}
	
	@Override
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	@Override
	public int getDuration() {
		return duration;
	}
	
	@Override
	public long getEndTime() {
		return getStartTime() + getDuration();
	}
	
	@Override
	public boolean isEnded(long currentTime) {
		return getEndTime() < currentTime;
	}
}
