
import rxtxrobot.*;
public class temperature {
 static RXTXRobot robot = new ArduinoUno();
	public static void main(String args[]) {

		 //Connect to the arduino
		// robot = new ArduinoUno();
		 robot.setPort("COM3");
		 robot.connect();

		 //Get the average thermistor reading
		 int thermistorReading = getThermistorReading();

		 //Print the results
		 System.out.println("The probe read the value: " + thermistorReading);
		 System.out.println("In volts: " + (thermistorReading * (5.0/1023.0)));
		 double temperature = (thermistorReading-809.05)/-8.5458;
		 System.out.println("The temperature is: " + temperature);
		 robot.close();
		 }
	public static int getThermistorReading() {
	 int sum = 0;
	 int readingCount = 10;

	 //Read the analog pin values ten times, adding to sum each time
	 for (int i = 0; i < readingCount; i++) {

	 //Refresh the analog pins so we get new readings
	 robot.refreshAnalogPins();
	 int reading = robot.getAnalogPin(0).getValue();
	 sum += reading;
	 }

	 //Return the average reading
	 return sum / readingCount;
	 }


}
