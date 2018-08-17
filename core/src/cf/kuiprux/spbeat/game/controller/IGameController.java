package cf.kuiprux.spbeat.game.controller;

public interface IGameController extends AutoCloseable {
	
	boolean isPressed(int index);
	
	void addListener(IControllerListener listener);
	void removeListener(IControllerListener listener);
	
	boolean containsListener(IControllerListener listener);
	
	boolean isListening();
	void listen() throws Exception;
}
