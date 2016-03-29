import rxtxrobot.ArduinoUno;
import rxtxrobot.RXTXRobot;
//Left is two, negative, motor 1
//Right is three, positive, motor 2
//pin five is right
public class Position1BL
{
  static RXTXRobot robot = new ArduinoUno();
  static int ping = 7;
  static double ticks_per_inch = 10.3;

  public static void main(String args[])
  {
    robot.setPort("COM3");
    robot.connect();
    robot.attachMotor(RXTXRobot.MOTOR1, 5);
    robot.attachMotor(RXTXRobot.MOTOR2, 4);
    robot.refreshAnalogPins();
    robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
  //  robot.runMotor(RXTXRobot.MOTOR1, -290, RXTXRobot.MOTOR2, 250, 0);
    forward(250, 218);//speed, distance
    rightTurn90();
    forward(250, 100);
    System.out.println(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1));
	robot.close();

  }
  static void rightTurn90()
  {
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
	  robot.runMotor(RXTXRobot.MOTOR1, 250,0);
	  robot.runMotor(RXTXRobot.MOTOR2, 250, 0);
	  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)<215)
	  {}
	  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors

  }
  static void leftTurn90()
  {
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
	  robot.runMotor(RXTXRobot.MOTOR1, -250,0);
	  robot.runMotor(RXTXRobot.MOTOR2, -250, 0);
	  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)>-178)
	  {}
      robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors

  }
  static void forward(int speed, double distance)
  {
	  int change = 7;
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR2);
	  robot.runMotor(RXTXRobot.MOTOR1, -speed-change,0);
	  robot.runMotor(RXTXRobot.MOTOR2, speed,0);
	  double ticks_per_inch = 10.3;
	  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)>-(distance*ticks_per_inch) && robot.getEncodedMotorPosition(RXTXRobot.MOTOR2)<(distance*ticks_per_inch))
	  {
		System.out.println(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1));
	  }
	  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
  }

  static void forward2(int speed, double distance)
  {
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR2);
	  //double ticks_per_inch = 10.3;


  }
  static void avoidObject()
  {
	  rightTurn90();
	  forward(150, 24);
	  leftTurn90();
	  forward(150,24);
	  leftTurn90();
	  forward(150,24);
	  rightTurn90();
	  forward(150, 48);
  }

  static void backward(int speed, double distance)
  {
	  int change = 7;
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR2);
	  robot.runMotor(RXTXRobot.MOTOR1, speed+change,0);
	  robot.runMotor(RXTXRobot.MOTOR2, -speed,0);
	  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)<(distance*ticks_per_inch) && robot.getEncodedMotorPosition(RXTXRobot.MOTOR2)>-(distance*ticks_per_inch))
	  {
		System.out.println(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1));
	  }
	  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
  }
}
