import rxtxrobot.ArduinoUno;
import rxtxrobot.RXTXRobot;
//Left is two, negative, motor 1
//Right is three, positive, motor 2
//pin five is right
public class Autonomy
{
  static RXTXRobot robot = new ArduinoUno();
  static int ping = 7;
  static double ticks_per_inch = 10.3;
  static int startArea = 2;
  double rampX=0;
  double rampY=0;
  double soilX=2;
  double soilY=1;


  double x = 177.3;
  double y = 187.5;

  static double currentX;
  static double currentY;
  static double solarX;
  static double solarY;
  static int solarArea;
  double desiredX;
  double desiredY;
  double currentArea;

  static int compass = 1; //1-North, 2-East, 3-South, 4-West

  int objective1Complete=0;


public static void main(String args[])
  {
	robot.setPort("COM3");
	robot.connect();
	robot.attachMotor(RXTXRobot.MOTOR1, 5);
	robot.attachMotor(RXTXRobot.MOTOR2, 4);
	robot.refreshAnalogPins();
	robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
	  if(startArea==1)
	  {
	  	System.out.println("I am in Area 1.");
	  	currentX=2;
	  	currentY=30;
	  	solarX=25;
	  	solarY=29;
	  	solarArea=2;
	  	compass = 3;
	  	System.out.println("I will now go do objective 1: go up the ramp.");
	  	objective1();
	  }
	  else if(startArea==2)
	  {
	  	System.out.println("I am in Area 2.");
	  	currentX=26;
	  	currentY=30;
	  	solarX=25;
	  	solarY=3;
	  	solarArea=4;
	  	compass = 3;
	  	System.out.println("I will now switch Areas.");
	  	switchArea();
	  }
	  else if(startArea==3)
	  {
	  	System.out.println("I am in Area 3.");
	  	currentX=2;
	  	currentY=2;
	  	solarX=3;
	  	solarY=3;
	  	solarArea=1;
	  	compass = 1;
	  	System.out.println("I will now switch Areas.");
	  	switchArea;
	  }
	  else if(startArea==4)
	  {
	  	System.out.println("I am in Area 4.");
	  	currentX=26;
	  	currentY=2;
	  	solarX=3;
	  	solarY=29;
	  	solarArea=3;
	  	compass = 1;
	  	System.out.println("I will now switch Areas.");
	  	switchArea;
	  }
	  else
	  {
	  	System.out.println("No specified start area!");
	  	System.exit(0);
	  }
  }



void turnRight()
  {
  	System.out.println("I am going to turn right.");
	  rightTurn90();

  		if(compass == 4)
  		{
  			compass=1;
  			System.out.println("Compass now equals" + compass);
  		}

  		else
  		{
  			compass++;
  			System.out.println("Compass now equals" + compass);
  		}
  }

void turnLeft()
  {
	System.out.println("I am going to turn left.");
	leftTurn90();

  		if(compass==1)
  		{
  			compass=4;
  			System.out.println("Compass now equals" + compass);
  		}

  		else
  		{
  			compass--;
  			System.out.println("Compass now equals" + compass);
  		}
  }




void move()
  {
  	System.out.println("I am about to move now.");
  	if(compass==2 || compass==4)
  	{
  		System.out.println("I should xPing");
  		xPing();
  		//move function using x
		//if(currentX==13 || currentX==15)
		//{ use command to realign itself with the wall }

  		if(compass==2)
  		{
  			currentX+=1;
  			System.out.println("My current position is " + currentX + ", " + currentY);
  		}
  		else if(compass==4)
  		{
  			currentX-=1;
  			System.out.println("My current position is " + currentX + ", " + currentY);
  		}
  		else
  		{
  			System.out.println("How did we even get here? - X-axis editon error.");
  			System.exit(0);
  		}

  	}

  	else if(compass==1 || compass==3)
  	{
  		System.out.println("I should yPing");
  		yPing();
  		//move function using y
		//if(currentY==15 || currentY==17)
		//{ use command to realign itself with the wall }

  		if(compass==1)
  		{
  			currentY+=1;
  			System.out.println("My current position is " + currentX + ", " + currentY);
  		}
  		else if(compass==3)
  		{
  			currentY-=1;
  			System.out.println("My current position is " + currentX + ", " + currentY);
  		}
  		else
  		{
  			System.out.println("How did we even get here? - Y-axis edition error.");
  			System.exit(0);
  		}
  	}
  	else
  	{
  		System.out.println("Something went wrong with the compass.");
  		System.out.println("It currently reads: " + compass);
  		System.exit(0);
  	}

  }

void movement()
  {
  System.out.println("My current position is " + currentX + ", " + currentY);
  System.out.println("I want to go to " + desiredX + ", " + desiredY);
  //compare currentLocation y-axis with desiredLocation y-axis> // Y-axis check

  if(currentY > desiredY)
  {
  	System.out.println("I am higher than where I want to be.");
  	if(compass !=3)
  	{
  		  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
  		  robot.runMotor(RXTXRobot.MOTOR1, 250,0);
  		  robot.runMotor(RXTXRobot.MOTOR2, 250, 0);
  		  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)<215)
  		  {}
  		  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0);
  		  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0);
  	}
  	else if(compass==3)
  	{
  		move();
  	}
  	else
  	{
  		System.out.println("Error regarding currentLocation’s y-axis being higher than the desiredLocation’s y-axis.");
  		System.exit(0);
  	}
  }
  else if(currentY < desiredY)
  {
  	System.out.println("I am lower than where I want to be.");
  	if(compass!=1)
  	{
  	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
	  robot.runMotor(RXTXRobot.MOTOR1, 250,0);
	  robot.runMotor(RXTXRobot.MOTOR2, 250, 0);
	  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)<215)
	  {}
	  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0);
	  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0);
  	}
  	else if(compass==1)
  	{
  		move();
  	}
  else
  	{
  		System.out.println("Error regarding currentLocation’s y-axis being lower than the desiredLocation’s y-axis.");
  		System.exit(0);
  	}

  }

  else if(currentY == desiredY)
  {
  System.out.println("I am at the right height.");
  if(currentX > desiredX)
  	{
  		System.out.println("I am too far right.");
  		if(compass !=4)
  		{
  		  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
  		  robot.runMotor(RXTXRobot.MOTOR1, 250,0);
  		  robot.runMotor(RXTXRobot.MOTOR2, 250, 0);
  		  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)<215)
  		  {}
  		  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
  		}
  		else if(compass==4)
  		{
  			move();
  		}
  		else
  		{
  			System.out.println("Error regarding currentLocation’s x-axis being higher than the desiredLocation’s x-axis.");
  			System.exit(0);
  		}
  	}
  	else if(currentX < desiredX)
  	{
  		System.out.println("I am too far left.");
  		if(compass!=2)
  		{
  		  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
  		  robot.runMotor(RXTXRobot.MOTOR1, 250,0);
  		  robot.runMotor(RXTXRobot.MOTOR2, 250, 0);
  		  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)<215)
  		  {}
  		  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
  		}
  		else if(compass==2)
  		{
  			move();
  		}
  else
  		{
  			System.out.println("Error regarding currentLocation’s x-axis being lower than the desiredLocation’s x-axis.");
  			System.exit(0);
  		}
  	}
  	else
  	{
  		System.out.println("I am right where I want to be.");
  		//currentLocation = desiredLocation
  		return;
  	}
  }
 }




void xPing()
  {
  	System.out.println("I am pinging the x axis.");
	robot.getPing(7);
  	if(ping < 18)
  	{
  		System.out.println("I have found something in my sensors.");
  		robot.sleep(5000);
  		robot.getPing(7);

  		if(ping < 18)
  		{
  			System.out.println("It is still there. I will move.");
  			avoidObject();
  		}
  		else
  		{}
  	}
  	else
  	{}
  }

void yPing()
  {
  	System.out.println("I am pinging the y axis.");
  	robot.getPing(7);

  	if(ping < 19)
  	{
  		System.out.println("I have found something in my sensors.");
  		robot.sleep(5000);

  		if(ping < 19)
  		{
  			System.out.println("It is still there. I will move.");
  			avoidObject();
  		}
  		else
  		{}
  	}
  	else
  	{}
  }


void evasiveFunction()
  {
  System.out.println("Initiating evasive maneuvers.");
  if(currentArea ==1 || currentArea ==3)
  {
     rightTurn90();

  	if(compass==1 || compass==3)
  	{ yPing(); }
  	else if(compass==2 || compass==4)
  	{ xPing(); }
  	else
  	{
  		System.out.println("error on compass for evasive function.");
  		System.exit(0);
  	}

    move();
    leftTurn90();

  	if(compass==1 || compass==3)
  	{ yPing(); }
  	else if(compass==2 || compass==4)
  	{ xPing(); }
  	else
  	{
  		System.out.println("error on compass for evasive function.");
  		System.exit(0);
  	}
  }

  else if(currentArea ==2 || currentArea ==4)
  {
  leftTurn90();

  	if(compass==1 || compass==3)
  	{ yPing(); }
  	else if(compass==2 || compass==4)
  	{ xPing(); }
  	else
  	{
  		System.out.println("error on compass for evasive function.");
  		System.exit(0);
  	}
  move();
  rightTurn90();

  	if(compass==1 || compass==3)
  	{ yPing(); }
  	else if(compass==2 || compass==4)
  	{ xPing(); }
  	else
  	{
  		System.out.println("error on compass for evasive function.");
  		System.exit(0);
  	}
  }

  else
  {
  	System.out.println("error on evasive function for currentArea.");
  	System.exit(0);
  }
  }


void objective1()
  {
   System.out.println("I have begun objective1.");
  if(objective1Complete==0)
  {
   desiredX=rampX;
   desiredY=rampY;
   System.out.println("I am about to move to objective1.");
   movement();

   //actions required to go up ramp and do all the stuff>
   //move 2 times in a row to go up ramp>
   //move 3 times to go down>

   objective1Complete+=1;
   System.out.println("I did objective1. I will now switch areas.");
   switchArea();
  }

  else
  {
  System.out.println("I already did objective1. I will now switch areas.");
   switchArea();
  }
  }

void objective2()
  {
  System.out.println("I have begun objective2.");
    if(solarArea==currentArea)
     {
       desiredX=solarX;
       desiredY=solarY;
       System.out.println("I am about to move to objective2.");
       movement();
       //rest for 5 seconds

   	if(solarArea==3)
    	{
    		System.out.println("I shall now begin objective3.");
  		objective3();
  	}
  	else if(solarArea==1)
  	{
  		System.out.println("I shall now begin objective1.");
  		objective1();
  	}
  	else
  	{
  		System.out.println("Nothing else to do. I will now switch areas.");
  		switchArea();
  	}
     }
  }

void objective3()
  {
  System.out.println("I have begun objective3.");
  desiredX=soilX;
  desiredY=soilY;
  System.out.println("I am about to move to objective3.");
  movement();

  //soil function

  System.out.println("We did it!");

  System.exit(0);
  }


void switchArea()
  {
  System.out.println("I have begun switching areas.");
   if(currentArea==1)
   {
   	System.out.println("I am in Area " + currentArea + " and I will move to Area 2.");
   desiredY=23;
   desiredX=15;
   movement();

   currentArea=2;
   System.out.println("I am now in Area " + currentArea);
   }

   else if(currentArea==2)
   {
   	System.out.println("I am in Area " + currentArea + " and I will move to Area 4.");
   desiredY=15;
   desiredX=18;
   movement();

   currentArea=4;
   System.out.println("I am now in Area " + currentArea);
   }

  else if(currentArea==3)
  {
  	System.out.println("I am in Area " + currentArea + " and I will move to Area 1.");
  desiredY=17;
  desiredX=9;
  movement();

  currentArea=1;
  System.out.println("I am now in Area " + currentArea);
  }

  else if(currentArea==4)
  {
  	System.out.println("I am in Area " + currentArea + " and I will move to Area 3.");
  desiredY=10;
  desiredX=12;
  movement();

  currentArea=3;
  System.out.println("I am now in Area " + currentArea);
  }

  else
  {
  System.out.println("switchArea function isn’t working for some reason.");
  System.exit(0);
  }
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
	  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)>-(distance*ticks_per_inch) && robot.getEncodedMotorPosition(RXTXRobot.MOTOR2)<(distance*ticks_per_inch))
	  {
		System.out.println(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1));
	  }
	  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
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


}
