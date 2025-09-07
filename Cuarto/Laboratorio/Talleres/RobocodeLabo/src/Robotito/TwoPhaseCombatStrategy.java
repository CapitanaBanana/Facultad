package Robotito;

import robocode.JuniorRobot;

public class TwoPhaseCombatStrategy implements Estratega{
    private static final int terminatorThreshold = 5;

    private static class EstrategiaEvasiva extends CombatStrategy {
        private final int distance = 100;

        @Override
        public void onInit() {

            robot.setColors(JuniorRobot.yellow, JuniorRobot.purple, JuniorRobot.red,
                    JuniorRobot.purple, JuniorRobot.red);

            moveToLeftWall(robot);
        }

        private void moveToLeftWall(JuniorRobot robot){
            int leftWallAngle = 270;
            robot.turnTo(leftWallAngle);
            robot.turnGunTo(0);
            robot.ahead(robot.fieldWidth);
        }

        @Override
        public void onTick() {
            robot.fire(bulletHalfPower);
            robot.ahead(distance);
            robot.doNothing();
        }

        @Override
        public void onHitWall() {
            robot.turnRight(90);
            robot.ahead(distance);
            // hacia el INTERIOR del mapa
            int inward = (robot.heading + 90) % 360;
            robot.turnGunTo((inward) % 360);
        }

        @Override
        public void onScannedRobot() {

        }

        @Override
        public void onHitRobot() {

            robot.turnGunTo(robot.hitRobotAngle);
            robot.fire(bulletMaxPower);
        }

        @Override
        public void onHitByBullet() {

        }

    }



    private static class EstrategiaOfensiva extends CombatStrategy {

        private int steps;

        @Override
        public void onInit() {
            robot.setColors(JuniorRobot.red, JuniorRobot.black, JuniorRobot.black,
                    JuniorRobot.red, JuniorRobot.red);
        }

        private void discreteFire(double power){
            if (robot.gunReady) {
                robot.fire(power);
            }

        }

        @Override
        public void onTick() {
            steps = (int)(Math.random() * 200 + 1);
            robot.ahead(steps);

            steps = (int)(Math.random() * 200 + 1);
            robot.back(steps);

            int inward = (robot.heading + 90) % 360;
            robot.turnGunTo((inward) % 360);
        }

        @Override
        public void onScannedRobot(){
            robot.turnGunTo(robot.scannedAngle);
            // Ajustar potencia en funciÃ³n de la distancia
            double power = (robot.scannedDistance < 200) ? bulletMaxPower : bulletHalfPower;
            this.discreteFire(power);


        }

        @Override
        public void onHitWall() {

        }

        @Override
        public void onHitRobot() {
            robot.turnGunTo(robot.hitRobotAngle);
            robot.fire(bulletMaxPower);

        }

        @Override
        public void onHitByBullet() {
            robot.turnGunTo(robot.hitByBulletAngle + 45);
            this.discreteFire(bulletHalfPower);
            robot.turnGunTo(robot.hitByBulletAngle - 45);
            this.discreteFire(bulletHalfPower);

        }

    }

    public static final CombatStrategy evasivePhase = new EstrategiaEvasiva();
    public static final CombatStrategy aggressivePhase = new EstrategiaOfensiva();

    @Override
    public CombatStrategy elegirEstrategia(JuniorRobot robot) {
        if (robot.others > terminatorThreshold) {
            return TwoPhaseCombatStrategy.evasivePhase;
        } else {
            return TwoPhaseCombatStrategy.aggressivePhase;
        }
    }
}
