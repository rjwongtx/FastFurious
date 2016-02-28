import rxtxrobot.*;
//Motor 1 needs to be negative, right
//Motor 2 needs to be positive, left
//pin five is right
public class Movement
{
  static RXTXRobot robot = new ArduinoUno();
  public static void main(String args[])
  {
    robot.setPort("COM3");
    robot.connect();


    robot.attachMotor(RXTXRobot.MOTOR1, 5); //x is a standin and will be replaced with the actual port number
    robot.attachMotor(RXTXRobot.MOTOR2, 4); //y is a standin and will be replaced with the actual port number
    robot.runMotor(RXTXRobot.MOTOR1, -340, RXTXRobot.MOTOR2, 250, 10400); //half power for 3 seconds to get an estimate on speeed
    //robot.runMotor(RXTXRobot.MOTOR2, 250, 3000);
    robot.close();
  }

}
