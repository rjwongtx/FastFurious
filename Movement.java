import rxtxrobot.*;

public class movement
{
  public static void main(String args[])
  {
    robot.setPort("COM3");
    robot.connet();
    
    robot.attachMotor(RXTXRobot.MOTOR1, 10); //x is a standin and will be replaced with the actual port number
    robot.attachMotor(RXTXRobot.MOTOR2, 11); //y is a standin and will be replaced with the actual port number 
    
    robot.runMotor(RXTXRobot.MOTOR1, 250, 3000); //half power for 3 seconds to get an estimate on speeed
    robot.runMotor(RXTXRobot.MOTOR2, 250, 3000); 
    
    robot.close();
  }
  
}
