import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MoveController;
import lejos.robotics.navigation.MovePilot;

public class Mover {

private MovePilot pilot = null;
    
    public void go()
    {
    	System.out.println("We are at the start of go");
    	EV3 brick = LocalEV3.get();
    	System.out.println(brick.getName());
    	try (NXTTouchSensor rightSensor = new NXTTouchSensor(brick.getPort("S4")))
    	{
    		System.out.println("Sensor 1 online");
    		try(NXTTouchSensor leftSensor = new NXTTouchSensor(brick.getPort("S1")))
    		{
    			System.out.println("Sensor 2 online");
    			setupPilot(brick);

        		pilot.forward();
        		while (!Button.ESCAPE.isDown())
        		{
        			float[] sampleRight = new float[rightSensor.sampleSize()];
    				float[] sampleLeft = new float[rightSensor.sampleSize()];
    				rightSensor.fetchSample(sampleRight, 0);
    				leftSensor.fetchSample(sampleLeft, 0);
    				System.out.println("We should get here");
    				if(sampleRight[0] == 1 && sampleLeft[0] == 1)
    				{
    					pilot.stop();
    					pilot.travel(-5);
    					pilot.rotate(-40);
    					pilot.forward();
    				}
    				else if (sampleRight[0] == 1)
        			{
    					pilot.stop();
    					pilot.travel(-5);
    					pilot.rotate(-20);
    					pilot.forward();
        			}
    				else if (sampleLeft[0] == 1)
    				{
    					pilot.stop();
    					pilot.travel(-5);
    					pilot.rotate(-20);
    					pilot.forward();
    				}
        		}
        		rightSensor.close();
        		leftSensor.close();
    			
    		}
    	}
    }

    private void setupPilot(EV3 brick)
    {
    	System.out.println("we are at the setup pilot");
	Wheel wheel1 = WheeledChassis
		.modelWheel(new EV3LargeRegulatedMotor(brick.getPort("C")), 2.7).offset(-7.9);
	Wheel wheel2 = WheeledChassis
		.modelWheel(new EV3LargeRegulatedMotor(brick.getPort("B")), 2.7).offset(7.9);

	Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
	pilot = new MovePilot(chassis);
    }
}