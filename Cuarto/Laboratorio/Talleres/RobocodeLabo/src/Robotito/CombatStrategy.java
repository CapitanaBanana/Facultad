package Robotito;

import robocode.*;

public interface CombatStrategy {
    double bulletHalfPower = 1.5;
    double bulletMaxPower = 3;

    void init(JuniorRobot robot);

    void onTick(JuniorRobot robot);

    void onScannedRobot(JuniorRobot robot);

    void onHitWall(JuniorRobot robot);

    void onHitRobot(JuniorRobot robot);

    void onHitByBullet(JuniorRobot robot);

    default boolean readyToAttack(){
        return true;
    }

}