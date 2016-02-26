import rxtxrobot.*;

public class BumpSensor
{
	static RXTXRobot robot = new ArduinoUno();
	static int bump = 11;

	public static void main(String[] args)
	{
		robot.setPort("COM3"); // Set the port to COM3
		robot.connect();

		robot.close();

	}
} 
