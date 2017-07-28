package de.metro.robocode;

import robocode.*;



public class SoMa extends AdvancedRobot {
	double battlefield_x ;
	double battlefield_y;
	double myX;
	double myY;
	boolean isAtWall = false;
	boolean hasTurned = false;
	boolean isTurning = false;
    @Override
    public void run() {
    	battlefield_y = getBattleFieldHeight();
    	battlefield_x = getBattleFieldWidth();
    	myX = getX();
    	myY =getY();
        double radius = 100.0;
        double angle = 90.0;
        while (true) {
        	myX = getX();
    		while (getHeading() > 96 || getHeading() < 84)
    		{
    			setTurnLeft(5);
    			execute();
    		}
        	//setTurnRadarLeft(1000000);
        	if (myX < battlefield_x - 100)
        	{
        		setAhead(20);
        	}
        	else
        	{
        		if (!isAtWall)
        		{
        			if (getHeading() > 90)
        			{
        				setTurnLeft(getHeading() - 90);
        			}
        			else
        			{
        				setTurnRight(90 - getHeading());
        			}
        			isAtWall = true;
        		}
        	}
        	
        	if (!hasTurned && isAtWall)
        	{
        		turnLeft(90);
        		hasTurned = true;
        	}
        	if (hasTurned && isAtWall)
        	{
        		setAhead(100);
        		if ((getY() > battlefield_y - 50 || getY() < 50))
        		{
        			if (isTurning == false)
        			{
        			isTurning = true;
        			setTurnLeft(180);
        			}
        		}
        		/*else if ( getY() < battlefield_y - 50 && getY() > 50) 
        			isTurning = false;*/
        	}

        //setTurnLeft(getHeading() % 90);

   	

            //setAhead(radius);
            //setTurnLeft(angle);
            setTurnGunLeft(angle);
            //setfireBullet(getEnergy());
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }
    
    public void onHitWall() {
    	if (!isAtWall)
    	{
    		isAtWall = true;
    		//turnLeft(90);
    	}
    	else
    	{
    		//setTurnLeft(180);
    	}
	}

    public void onHitByBullet(HitByBulletEvent e) {
       //turnLeft(90 - e.getBearing());
    }

}
