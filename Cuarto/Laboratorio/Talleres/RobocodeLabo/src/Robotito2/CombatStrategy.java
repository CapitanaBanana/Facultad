package Robotito2;

import robocode.*;

public interface CombatStrategy {
     double bulletHalfPower = 1.5;
     double bulletMaxPower = 3;
     int terminatorThreshold = 5;
     int leftWallAngle = 270;

    public abstract void init(JuniorRobot robot);

    public abstract void onTick(JuniorRobot robot);

    public abstract void onScannedRobot(JuniorRobot robot);

    public abstract void onHitWall(JuniorRobot robot);

    public abstract void onHitRobot(JuniorRobot robot);

    public abstract void onHitByBullet(JuniorRobot robot);
}
