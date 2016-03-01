import rxtxrobot.*;

public class BumpSensor
{
	static RXTXRobot robot = new ArduinoUno();
	static int bump = 8;

	// the setup routine runs once when you press reset:
	public static void main(String[] args)
	{
		robot.setPort("COM3"); // Set the port to COM3
		robot.connect();
		robot.attachMotor(RXTXRobot.MOTOR1, 5);
	    robot.attachMotor(RXTXRobot.MOTOR2, 4);
	    robot.refreshAnalogPins();
	    int reading = robot.getAnalogPin(2).getValue();
	    while (reading>800)
	    {
	     robot.runMotor(RXTXRobot.MOTOR1, -240, RXTXRobot.MOTOR2, 150, 0);
		 reading = robot.getAnalogPin(2).getValue();
		 System.out.println(reading);
		 robot.refreshAnalogPins();
	    }
		robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
		//robot.runMotor(RXTXRobot.MOTOR1, -340, RXTXRobot.MOTOR2, 250, 3000); //half power for 3 seconds to get an estimate on speed
		robot.close();

	}
} 
