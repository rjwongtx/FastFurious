
import rxtxrobot.*;
//2.942 seconds for degrees, below at 80
public class Servo
{
  static RXTXRobot robot = new ArduinoUno();
  public static void main(String args[])
  {
    robot.setPort("COM3");
    robot.connect();

    robot.attachServo(RXTXRobot.SERVO1, 9);
    // robot.attachServo(RXTXRobot.SERVO2, y);


    robot.moveServo(RXTXRobot.SERVO1, 0); //90 is zero speed. above 90 goes clockwise while below goes counter
    robot.sleep(5000);
  //  robot.moveServo(RXTXRobot.SERVO1, 100); //90 is zero speed. above 90 goes clockwise while below goes counter
    //robot.sleep(206);

    robot.close();
  }

}
