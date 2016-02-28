
import rxtxrobot.*;

public class Servo
{
  static RXTXRobot robot = new ArduinoUno();
  public static void main(String args[])
  {
    robot.setPort("COM3");
    robot.connect();

    robot.attachServo(RXTXRobot.SERVO1, 9); //x is a standin and will be replaced with the actual port number
    // robot.attachServo(RXTXRobot.SERVO2, y); //y is a standin and will be replaced with the actual port number


    robot.moveServo(RXTXRobot.SERVO1, 89); //half power for 3 seconds to get an estimate on speeed
    //robot.moveServo(RXTXRobot.SERVO2, 180);
    robot.sleep(6000);

    robot.close();
  }

}
