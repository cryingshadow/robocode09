package de.metro.robocode;

import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;



public class SoMa extends AdvancedRobot {
	int direction = 1;
	boolean locked = false;
	String target = "";
	int countRadarIteration = 0;
	boolean close = false;
	
    @Override
    public void run() {
    	while (true)
    	{
    		setTurnRadarLeft(10000);
    		if (!(close && getEnergy() < 50))
    			setAhead(100);
    		else
    			setAhead(0);
    		//setTurnLeft(90);
    		//setTurnGunLeft(90);
    		execute();
    		if(getRadarHeading() > 200 )
    		{
    			countRadarIteration++;
    		}
    		if (countRadarIteration > 5)
    		{
    			locked = false;
    		}
    	}
    }

    public void onScannedRobot(ScannedRobotEvent e) {
    	if (!locked)
    	{
    		locked = true;
    		target = e.getName();
    	}
    		
    	if (e.getName() == target)
    	{
    		double absbear = getHeading() + e.getBearing();
    		double bearingGun = normalRelativeAngleDegrees(absbear - getGunHeading());
    		setTurnGunRight(bearingGun);
    		if (e.getDistance() < 60)
    		{
    			close = true;
    		}
    		else
    			close = false;
    		countRadarIteration = 0;
    		setTurnRight(e.getBearing());
    		if (e.getDistance() < 50)
    		{
    			if(getGunHeat() <= 0.5)
    				fire(3);
    		}
    		else if (e.getDistance() < 75)
    		{
    			if(getGunHeat() <= 0.5)
    				fire(2);    			
    		}
    		else if (e.getDistance() < 150)
    		{
    			if(getGunHeat() <= 0.5)
    				fire(1); 
    		}
    	}
        //fire(2);
    }
    
    public void onHitWall() {
	}

    public void onHitByBullet(HitByBulletEvent e) {
       //turnLeft(90 - e.getBearing());
    }

}
