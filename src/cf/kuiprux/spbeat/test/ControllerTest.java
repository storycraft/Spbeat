package cf.kuiprux.spbeat.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import cf.kuiprux.spbeat.game.controller.FallbackController;
import cf.kuiprux.spbeat.game.controller.IControllerListener;
import cf.kuiprux.spbeat.game.controller.SpbeatController;

class ControllerTest implements IControllerListener {
	
	private boolean passed;

	@Test
	void test() {
		passed = false;
		
		try {
			Display.setDisplayMode(new DisplayMode(100, 100));
			Display.create();
			
			System.out.println("아무 키나 누르세요");
			
			Keyboard.create();
		} catch (LWJGLException e) {
			fail(e);
		}
		
		try (FallbackController controller = new FallbackController()){
			controller.addListener(this);
			try {
				controller.listen();
			} catch (Exception e) {
				fail(e);
			}
			
			while(!passed) {
				Display.update();
			}
		}
		
		System.out.println("passed fallback test");
		
		try (SpbeatController controller = new SpbeatController()){
			controller.addListener(this);
			try {
				controller.listen();
			} catch (Exception e) {
				fail(e);
			}
			
			while(!passed) {
				Display.update();
			}
		}
		
		System.out.println("passed controller test");
	}

	@Override
	public void onPress(int keyIndex) {
		System.out.println("press: " + keyIndex);
		passed = true;
	}

	@Override
	public void onRelease(int keyIndex) {
		System.out.println("release: " + keyIndex);
		passed = true;
	}

}
