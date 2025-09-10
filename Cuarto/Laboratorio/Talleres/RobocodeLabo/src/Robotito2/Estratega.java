
package Robotito2;
import robocode.JuniorRobot;

public abstract class Estratega {
    protected JuniorRobot robot;

    public void init(JuniorRobot robot) {
        this.robot = robot;
        strategyForOnInit();
    }

    protected abstract CombatStrategy strategyForOnInit();

    protected abstract CombatStrategy strategyForOnTick();

    public abstract CombatStrategy strategyForOnScannedRobot();

    public abstract  CombatStrategy strategyForOnHitWall();

    public abstract CombatStrategy strategyForOnHitRobot();

    public abstract CombatStrategy strategyForOnHitByBullet();

}
