package laboratorio;
import robocode.*;


public class LaboRobot extends JuniorRobot
{

	@Override	
	public void run() {

		setColors(orange, blue, white, yellow, black);
		ahead(100);
		turnGunRight(360);
		back(100);
		turnGunRight(360);
		
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	@Override
	public void onScannedRobot() {
		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	@Override
	public void onHitByBullet() {
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	@Override
	public void onHitWall() {
		back(20);
	}	
}