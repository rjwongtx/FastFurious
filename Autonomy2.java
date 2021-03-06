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

  //edit these before starting
  static int startArea = 3;
  static double rampX=4;
  static double rampY=29;
  static double soilX=4;
  static double soilY=6;


  static double currentX;
  static double currentY;
  static double solarX;
  static double solarY;
  static int solarArea;
  static double desiredX;
  static double desiredY;
  static double currentArea;

  static int compass = 1; //1-North, 2-East, 3-South, 4-West

  static int objective1Complete=0;


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
	  	currentArea=1;
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
	  	currentArea=2;
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
	  	currentArea=3;
	  	solarX=3;
	  	solarY=29;
	  	solarArea=1;
	  	compass = 1;
	  	System.out.println("I will now switch Areas.");
	  	switchArea();
	  }
	  else if(startArea==4)
	  {
	  	System.out.println("I am in Area 4.");
	  	currentX=26;
	  	currentY=2;
	  	currentArea=4;
	  	solarX=3;
	  	solarY=3;
	  	solarArea=3;
	  	compass = 1;
	  	System.out.println("I will now switch Areas.");
	  	switchArea();
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




static void move()
  {
  	System.out.println("I am about to move now.");
  	if(compass==2 || compass==4)
  	{
  		System.out.println("I should xPing");
  		xPing();
  		forward(250, 17.25);
  		//if(currentX==13 || currentX==15)
  			//adjust();
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
  		forward(250, 18.25);
		//if(currentY==15 || currentY==17)
			//adjust();
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

static void movement()
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
  	rightTurn90();
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
  		  rightTurn90(); // Stop both motors
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




static void xPing()
  {
  	System.out.println("I am pinging the x axis.");
	ping=robot.getPing(7);
  	if(ping < 18 && ping > 0)
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

static void yPing()
  {
  	System.out.println("I am pinging the y axis.");
  	ping=robot.getPing(7);

  	if(ping < 19 && ping >0)
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


static void evasiveFunction()
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


static void objective1()
  {
   System.out.println("I have begun objective1.");
  if(objective1Complete==0)
  {
   desiredX=rampX;
   desiredY=rampY;
   System.out.println("I am about to move to objective1.");
   movement();
   forward(250, 36.5);
   getTemp();
   forward(250, 54.75);
   currentY-=5;

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

static void objective2()
  {
  System.out.println("I have begun objective2.");
    if(solarArea==currentArea)
     {
       desiredX=solarX;
       desiredY=solarY;
       System.out.println("I am about to move to objective2.");
       movement();
       robot.sleep(5000);
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

static void objective3()
  {
  System.out.println("I have begun objective3.");
  desiredX=soilX;
  desiredY=soilY;
  System.out.println("I am about to move to objective3.");
  movement();
  goUntilBump();
  getSoil();
  System.out.println("We did it!");

  System.exit(0);
  }


static void switchArea()
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
  System.out.println("current area reads " + currentArea);
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
static void forward(int speed, double distance)
{
	  int change = 7;
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
	  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR2);
	  robot.runMotor(RXTXRobot.MOTOR1, -speed,0);
	  robot.runMotor(RXTXRobot.MOTOR2, speed,0);
	 // robot.runMotor(RXTXRobot.MOTOR2, (int)(speed*.982),0);
	  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)>-(distance*ticks_per_inch) && robot.getEncodedMotorPosition(RXTXRobot.MOTOR2)<(distance*ticks_per_inch))
	  {
		System.out.println(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1));
	  }
	  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
}
static void adjust()
{
	  int rPing = 11;
	  int lPing = 7;
while(robot.getPing(rPing)!=robot.getPing(lPing))
	{
	  if (robot.getPing(rPing)>robot.getPing(lPing))
	  	{

		  double x =robot.getPing(rPing)-robot.getPing(lPing);//10.3
		 // robot.runMotor(RXTXRobot.MOTOR1, -250, 0);
		  robot.runMotor(RXTXRobot.MOTOR1, -250, 0);
		  System.out.println("Right: I have to go this many cm: " + x);
		  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR2)<(5.1*x))
		  {
			  if(robot.getEncodedMotorPosition(RXTXRobot.MOTOR2)<0)
				  break;
			  System.out.println(robot.getEncodedMotorPosition(RXTXRobot.MOTOR2));
			//  System.out.println("Right ping is: "+robot.getPing(rPing));
			  //System.out.println("Left ping is: "+robot.getPing(lPing));
		  }
		  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
		  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
		  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR2);
		  System.out.println("I am going to stop the motors for the right");
		  System.out.println("The motors have stopped moving for the right");

	  	}
	  else if(robot.getPing(lPing)>robot.getPing(rPing))
	  	{
		  double y = robot.getPing(lPing)-robot.getPing(rPing);
		  robot.runMotor(RXTXRobot.MOTOR2, 250, 0);
		//  robot.runMotor(RXTXRobot.MOTOR2, 250, 0);
		  System.out.println("Left: I have to go this many cm: " + y);
		  while(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1)>(y*5.13))
		  {
			  System.out.println(robot.getEncodedMotorPosition(RXTXRobot.MOTOR1));
			//  System.out.println("Right ping is: "+robot.getPing(rPing));
			  //System.out.println("Left ping is: "+robot.getPing(lPing));
		  }
		  robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors
		  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR1);
		  robot.resetEncodedMotorPosition(RXTXRobot.MOTOR2);
	  	}
	  else
		  break;
	}
System.out.println("I am going to stop the motors");
System.out.println("The motors have stopped moving");
robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors

}
static void getTemp()
	{
  robot.runMotor(RXTXRobot.MOTOR3, 500, 18000);
  int thermistorReading = getThermistorReading();
  //Print the results
  System.out.println("The probe read the value: " + thermistorReading);
  System.out.println("In volts: " + (thermistorReading * (5.0/1023.0)));
  //double temperature = (thermistorReading-809.05)/-8.5458;
  double temperature = thermistorReading*(-0.0725)+63.624;
  //double windTemp = -0.1176*thermistorReading + 93.588;
  System.out.println("The temperature is: " + temperature);
  getWind();
  robot.sleep(10000);
	}
static void getWind()
{
int windSensorReading = getWindSensorReading();
int temperatureReading = getThermistorReading();
//System.out.println("The windSensor read the value: " + windSensorReading);
//System.out.println("The Temperature read the value: " + temperatureReading);
double windTemp = -0.1176*windSensorReading + 93.588;
double temperature = temperatureReading*(-0.0725)+63.624;
//System.out.println("The windSensor read the value: " + windTemp);
//System.out.println("The Temperature read the value: " + temperature);
double difference = temperature-windTemp;
double windspeed = 0.7844*difference-0.4656;
if (windspeed<0)
	System.out.println("The wind speed: 0");
else
	System.out.println("The wind speed: " + windspeed);
}
public static int getThermistorReading()
	{
	 int sum = 0;
	 int readingCount = 20;

	 //Read the analog pin values ten times, adding to sum each time
	 for (int i = 0; i < readingCount; i++) {

	 //Refresh the analog pins so we get new readings
	 robot.refreshAnalogPins();
	 int reading = robot.getAnalogPin(0).getValue();
	 sum += reading;
	 }

	 //Return the average reading
	 return sum / readingCount;
	 }
 public static int getWindSensorReading()
	{
	 int sum = 0;
	 int readingCount = 10;

	 //Read the analog pin values ten times, adding to sum each time
	 for (int i = 0; i < readingCount; i++) {

	 //Refresh the analog pins so we get new readings
	 robot.refreshAnalogPins();
	 int reading = robot.getAnalogPin(1).getValue();
	 sum += reading;
	 }

	 //Return the average reading
	 return sum / readingCount;
	}
 static void getSoil()
 {
	  robot.attachServo(RXTXRobot.SERVO2, 8);//conductivity
	  robot.moveServo(RXTXRobot.SERVO2, 180);
	  System.out.println("ADC Code: "+robot.getConductivity());
	  int reading = robot.getConductivity();
	  double content = (reading-1000.2)/(-1.6488);
	  //int moistureContent = (int)(content*1000);
	  //double result = moistureContent/1000;
	  if(reading>=1000)
		  System.out.println("Moisture content is: 0%");
	  else
		  System.out.println("Moisture content is: " + content+ "%");
	  robot.sleep(2000);
	  robot.moveServo(RXTXRobot.SERVO2, 85);
	  robot.sleep(3500);
 }
 static void goUntilBump()
 {
	robot.refreshAnalogPins();
	int bump = robot.getAnalogPin(2).getValue();
	while (bump>800)
	  {
	   robot.runMotor(RXTXRobot.MOTOR1, -300, RXTXRobot.MOTOR2, 300, 0);
	   bump = robot.getAnalogPin(2).getValue();
	   System.out.println(bump);
	   robot.refreshAnalogPins();
	  }
	robot.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0);
	robot.sleep(1000);
 }
}
