package Robotito;

import robocode.JuniorRobot;

/**
 * A sample robot.
 * Is not inherited from classic base robots, uses new experimental access to
 * RobotPeer.
 * Use -DEXPERIMENTAL=true to start robocode for this robot.
 *
 * @author Pavel Savara (original)
 */
public class Robotito extends JuniorRobot {

  private CombatStrategy strategy;

  @Override
  public void run() {
    strategy = new EstrategiaEvasiva();
    strategy.init(this);

    while (true) {

      // Cambio a ofensiva cuando la evasiva lo pida
        if (((EstrategiaEvasiva) strategy).readyToAttack()) {
          this.setStrategy(new EstrategiaOfensiva());
          strategy.init(this);
        }
      strategy.onTick(this);
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
