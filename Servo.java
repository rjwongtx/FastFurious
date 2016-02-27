import rxtxrobot.*;

public class movement
{
  public static void main(String args[])
  {
    robot.setPort("COM3");
    robot.connet();
    
    robot.attachServo(RXTXRobot.SERVO1, 7); //x is a standin and will be replaced with the actual port number
    // robot.attachServo(RXTXRobot.SERVO2, y); //y is a standin and will be replaced with the actual port number 
    
    
    robot.moveServo(RXTXRobot.SERVO1, 180); //half power for 3 seconds to get an estimate on speeed
    robot.moveServo(RXTXRobot.SERVO2, 180); 
    
    robot.close();
  }
  
}
