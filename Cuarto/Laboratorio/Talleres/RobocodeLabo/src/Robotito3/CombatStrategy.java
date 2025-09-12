package Robotito3;

import robocode.*;

public abstract class CombatStrategy {
    JuniorRobot robot;
    double bulletHalfPower = 1.5;
    double bulletMaxPower = 3;

    void init(JuniorRobot robot) {
        this.robot = robot;
        onInit();
    }

    abstract void onInit();

    abstract void onTick();

    abstract void onScannedRobot();

    abstract void onHitWall();

    abstract void onHitRobot();

    abstract void onHitByBullet();

}