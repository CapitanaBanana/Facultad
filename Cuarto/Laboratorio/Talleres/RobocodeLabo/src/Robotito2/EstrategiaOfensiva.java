package Robotito2;

import robocode.JuniorRobot;

public class EstrategiaOfensiva implements CombatStrategy {

    private int steps;

    @Override
    public void init(JuniorRobot robot) {
        robot.setColors(JuniorRobot.red, JuniorRobot.black, JuniorRobot.black,
                JuniorRobot.red, JuniorRobot.red);

        while (true){
            steps = (int)(Math.random() * 200 + 1);
            robot.ahead(steps);

            steps = (int)(Math.random() * 200 + 1);
            robot.back(steps);

            int inward = (robot.heading + 90) % 360;
            robot.turnGunTo((inward) % 360);
        }
    }

    private void discreteFire(JuniorRobot robot,double power){
        if (robot.gunReady) {
            robot.fire(power);
        }

    }

    @Override
    public void onTick(JuniorRobot robot) {

    }

    @Override
    public void onScannedRobot(JuniorRobot robot){
        robot.turnGunTo(robot.scannedAngle);
        // Ajustar potencia en función de la distancia
        double power = (robot.scannedDistance < 200) ? bulletMaxPower : bulletHalfPower;
        this.discreteFire(robot,power);


    }

    @Override
    public void onHitWall(JuniorRobot robot) {

    }

    @Override
    public void onHitRobot(JuniorRobot robot) {
        robot.turnGunTo(robot.hitRobotAngle);
        robot.fire(bulletMaxPower);

    }

    @Override
    public void onHitByBullet(JuniorRobot robot) {
        robot.turnGunTo(robot.hitByBulletAngle);
        this.discreteFire(robot,bulletHalfPower);

    }

}