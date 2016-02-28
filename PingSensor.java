import rxtxrobot.*;

public class PingSensor
{
	static RXTXRobot robot = new ArduinoUno();
	static int ping = 7;

	public static void main(String[] args)
	{
		robot.setPort("COM3"); // Set the port to COM3
		robot.connect();
		for (int x=0; x < 100; ++x)
		{
			//Read the ping sensor value, which is connected to pin 12
			System.out.println("Response: " + robot.getPing(ping) + " cm");
			robot.sleep(300);
		}
		robot.close();
	}
} 
