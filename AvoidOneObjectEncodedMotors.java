import rxtxrobot.ArduinoUno;
import rxtxrobot.RXTXRobot;
//Left is two, negative, motor 1
//Right is three, positive, motor 2
//pin five is right
public class EncodedMotors
{
  static RXTXRobot robot = new ArduinoUno();
  static int ping = 7;

  public static void main(String args[])
  {
    robot.setPort("COM3");
    robot.connect();
    robot.attachMotor(RXTXRobot.MOTOR1, 5);
    robot.attachMotor(RXTXRobot.MOTOR2, 4);
    robot.refreshAnalogPins();
    robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
    System.out.println(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1));
    robot.runMotor(RXTXRobot.MOTOR1, -290, RXTXRobot.MOTOR2, 250, 0);
    while (robot.getPing(ping) > 40)
	{
	}
    rightTurn90();
    forward(150, 24);
    leftTurn90();
    forward(150,24);
    leftTurn90();
    forward(150,24);
    rightTurn90();
    forward(150, 48);

    System.out.println(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1));
    leftTurn90();
    //forward(150, 48);//one inch is 6.22 ticks
	//robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
    // Program stops until the command above is completed
	//robot.runEncodedMotor(RXTXRobot.MOTOR1, -255, 100); // Run motor 1 backward (speed of 255) for 100,000 ticks
	robot.close();

  }
  static void rightTurn90()
  {
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
	  robot.runMotor(RXTXRobot.MOTOR1, 250,0);
	  robot.runMotor(RXTXRobot.MOTOR2, 250, 0);
	  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)<183.5)
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
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR2);
	  robot.runMotor(RXTXRobot.MOTOR1, -speed,0);
	  robot.runMotor(RXTXRobot.MOTOR2, speed,0);
	  double ticks_per_inch = 13.55;
	  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)>-(distance*ticks_per_inch) && robot.getEncodedMotorPosition(RXTXRobot.MOTOR2)<(distance*ticks_per_inch))
	  {
		System.out.println(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1));
	  }
	  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
  }

}
