package cf.kuiprux.spbeat.test;

import static org.junit.jupiter.api.Assertions.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

class SoundTest {

	@Test
	void test() {
		try {
			Player player = new Player(new FileInputStream("res/sample/realice.mp3"));
			player.play();
		} catch (JavaLayerException |FileNotFoundException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
