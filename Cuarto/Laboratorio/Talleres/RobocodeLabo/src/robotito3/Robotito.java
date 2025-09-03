package robotito3;

import robocode.JuniorRobot;

public class Robotito extends JuniorRobot {

    private CombatStrategy strategy;

    @Override
    public void run() {
        strategy = new EstrategiaEvasiva();
        strategy.init(this);

        while (true) {
            strategy.onTick(this);

            // ⬇️ Cambio a ofensiva cuando la evasiva lo pida
            if (strategy instanceof EstrategiaEvasiva) {
                if (((EstrategiaEvasiva) strategy).readyToAttack()) {
                    this.setStrategy(new EstrategiaOfensiva());
                    strategy.init(this);
                }
            }
        }
    }

    public void setStrategy(CombatStrategy newStrat) {
        strategy = newStrat;
    }

    public void onScannedRobot() {
        strategy.onScannedRobot(this);
    }

    public void onHitWall() {
        strategy.onHitWall(this);
    }

    public void onHitRobot() {
        strategy.onHitRobot(this);
    }

    public void onHitByBullet() {
        strategy.onHitByBullet(this);
    }
}
