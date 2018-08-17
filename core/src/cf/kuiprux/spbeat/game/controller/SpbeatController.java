package cf.kuiprux.spbeat.game.controller;


public class SpbeatController extends GameController {

	@Override
	public void listen() throws Exception {
		super.listen();

		throw new Exception("구현 되지 않음");
	}


	@Override
	protected void updateLoop() {

	}
}

/*
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class SpbeatController extends GameController {

	private static boolean isUsable = false;
	private static GpioPinDigitalInput[] digitalInputs;

	// index 범위 0 ~ 15

	// update 시작 전 호출됨
	protected void updateInit() {
		try {
			digitalInputs = new GpioPinDigitalInput[16];
			GpioController gpio = GpioFactory.getInstance();
			for (int i = 0; i < digitalInputs.length; i++) {
				try {
					Pin pin = (Pin) RaspiPin.class.getField("GPIO_" + ((i + 4 < 10) ? "0" : "") + (i + 4)).get(null);
					digitalInputs[i] = gpio.provisionDigitalInputPin(pin);
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
						| SecurityException e) {
					System.err.println("An error occurred initializing pin " + (i + 4) + ":" + e.getMessage());
				}
			}
			isUsable = true;
		} catch (Exception e) {
			System.err.println("An error occurred initializing pin input: " + e.getMessage());
		}
	}

	@Override
	protected void updateLoop() {
		if (isUsable) {
			for (int i = 0; i < digitalInputs.length; i++) {
				// Pull-up circuit
				boolean isPressed = digitalInputs[i].isLow();
				if (isPressed)
					callPressEvent(i);
				else
					callReleaseEvent(i);
			}
		}
	}
}
*/