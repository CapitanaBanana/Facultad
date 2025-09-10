package Robotito2;

import robocode.JuniorRobot;

public class Robotito extends JuniorRobot {

    private Estratega strategist = TwoPhaseCombatStrategy.getInstance();

    private CombatStrategy strategy;

    @Override
    public void run() {
        strategist.init(this);
        while (true) {
            strategist.strategyForOnTick().onTick();
        }
    }
    @Override
    public void onScannedRobot() {
        strategist.strategyForOnScannedRobot().onScannedRobot();
    }

    @Override
    public void onHitWall() {
        strategist.strategyForOnHitWall().onHitWall();
    }

    @Override
    public void onHitRobot() {
        strategist.strategyForOnHitRobot().onHitRobot();
    }

    @Override
    public void onHitByBullet() {
        strategist.strategyForOnHitByBullet().onHitByBullet();
    }


}