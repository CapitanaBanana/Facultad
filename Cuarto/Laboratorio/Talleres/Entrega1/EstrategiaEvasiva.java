package Robotito;

import robocode.JuniorRobot;

public class EstrategiaEvasiva implements CombatStrategy {
    private int terminatorThreshold = 5;
    private int leftWallAngle = 270;
    private boolean ready = false;
    private final int distance = 100;

    @Override
    public void init(JuniorRobot robot) {
        ready = robot.others < this.terminatorThreshold;

        robot.setColors(JuniorRobot.yellow, JuniorRobot.purple, JuniorRobot.red,
                JuniorRobot.purple, JuniorRobot.red);

        robot.turnTo(leftWallAngle);
        robot.turnGunTo(0);
        robot.ahead(robot.fieldWidth);
    }

    @Override
    public void onTick(JuniorRobot robot) {
        robot.fire(CombatStrategy.bulletHalfPower);
        if (robot.others < this.terminatorThreshold) {
            ready = true;
        }
        robot.ahead(distance);
        robot.doNothing();
    }

    @Override
    public void onHitWall(JuniorRobot robot) {
        robot.turnRight(90);
        robot.ahead(distance);
        // hacia el INTERIOR del mapa
        int inward = (robot.heading + 90) % 360;
        robot.turnGunTo((inward) % 360);
    }

    @Override
    public void onScannedRobot(JuniorRobot robot) {

    }

    @Override
    public void onHitRobot(JuniorRobot robot) {

        robot.turnGunTo(robot.hitRobotAngle);
        robot.fire(CombatStrategy.bulletMaxPower);
    }

    @Override
    public void onHitByBullet(JuniorRobot robot) {

    }

    @Override
    public boolean readyToAttack() {
        return ready;
    }
}