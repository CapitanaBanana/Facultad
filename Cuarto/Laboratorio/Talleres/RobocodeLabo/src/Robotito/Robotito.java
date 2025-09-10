package Robotito;

import robocode.JuniorRobot;

public class Robotito extends JuniorRobot {

    private Estratega strategist = TwoPhaseCombatStrategy.getInstance();

    private CombatStrategy strategy;

    @Override
    public void run() {
        while (true) {
            CombatStrategy nextStrategy = strategist.elegirEstrategia(this);

            if (nextStrategy != strategy) {
                strategy = nextStrategy;
                strategy.init(this);
            }
            strategy.onTick();
        }
    }

    public void setStrategy(CombatStrategy newStrat) {
        strategy = newStrat;
    }

    public void onHitWall(){ strategy.onHitWall(); }

    public void onHitRobot() { strategy.onHitRobot(); }

    public void onHitByBullet() {
        strategy.onHitByBullet();
    }

    public void onScannedRobot(){ strategy.onScannedRobot(); }
}