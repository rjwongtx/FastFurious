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


    robot.attachMotor(RXTXRobot.MOTOR1, 5);
    robot.attachMotor(RXTXRobot.MOTOR2, 4);
    robot.runMotor(RXTXRobot.MOTOR1, -340, RXTXRobot.MOTOR2, 250, 9650);
    //robot.runMotor(RXTXRobot.MOTOR2, 250, 3000);
    robot.close();
  }

}
