package cf.kuiprux.spbeat.game.controller;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class SpbeatController extends GameController {

	private static GpioPinDigitalInput[] digitalInputs;
	
	//index 범위 0 ~ 15
	static {
		digitalInputs = new GpioPinDigitalInput[16];
		GpioController gpio = GpioFactory.getInstance();
		
		for(int i = 0; i < digitalInputs.length; i++) {
			try {
				Pin pin = (Pin) RaspiPin.class.getField("GPIO_" + ((i+4 < 10) ? "0" : "") + (i+4)).get(null);
				digitalInputs[i] = gpio.provisionDigitalInputPin(pin);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void updateLoop() {
		for(int i = 0; i < digitalInputs.length; i++) {
			// Pull-up circuit
			boolean isPressed = digitalInputs[i].isLow();
			if(isPressed)
				callPressEvent(i);
			else
				callReleaseEvent(i);
		}
	}
}
