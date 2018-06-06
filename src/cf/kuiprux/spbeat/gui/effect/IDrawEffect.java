package cf.kuiprux.spbeat.gui.effect;

import org.newdawn.slick.Graphics;

public interface IDrawEffect {
	
	float getStartTime();
	
	float getDuration();
	
	float getEndTime();
	
	boolean isEnded(float currentTime);
	
	void applyAt(float currentTime, Graphics graphics);
}
