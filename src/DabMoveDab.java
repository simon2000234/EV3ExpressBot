import java.awt.event.KeyEvent;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class DabMoveDab {

	public static void main(String[] args) {
			System.out.println("Hello we are in dabmovedab");
			Mover mover = new Mover();
			mover.go();
	}
	
	private void setupPilot(EV3 brick)
	{
	}
}
