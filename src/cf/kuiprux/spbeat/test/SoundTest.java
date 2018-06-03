package cf.kuiprux.spbeat.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;

class SoundTest {

	@Test
	void test() {
		try {
			Music music = new Music("res/sample/sample.ogg");
			if(!music.playing())
				music.play(1.0f, 1.0f);
			
			music.stop();
		} catch (SlickException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
