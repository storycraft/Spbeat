package cf.kuiprux.spbeat.game.controller;

public interface IGameController {
	
	void simulatePress(int index);
	boolean isPressed(int index);
	
	void addListener(IControllerListener listener);
	void removeListener(IControllerListener listener);
	
	boolean containsListener(IControllerListener listener);
}
