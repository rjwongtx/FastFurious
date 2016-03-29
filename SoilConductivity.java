import rxtxrobot.ArduinoUno;
import rxtxrobot.RXTXRobot;
//Motor 1 needs to be negative, right
//Motor 2 needs to be positive, left
//pin five is right
public class SoilConductivity
{
  static RXTXRobot robot = new ArduinoUno();
  public static void main(String args[])
  {
    robot.setPort("COM3");
    robot.connect();
    System.out.println("ADC Code: "+robot.getConductivity());
    int reading = robot.getConductivity();
    double resistance = (2000*reading)/(1-reading);
    double conductivity = 0.00555625/(resistance*0.0762);
    System.out.println("Conductivity is: " + conductivity);
    robot.close();
  }

}
