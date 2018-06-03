package cf.kuiprux.spbeat;

public class Main {
	public static void main(String[] args) {
		SpBeAt game = new SpBeAt();
		
		try (WindowHost window = new WindowHost(game)){
			window.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
