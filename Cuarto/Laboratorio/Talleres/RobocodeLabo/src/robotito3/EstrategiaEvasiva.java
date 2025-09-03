package robotito3;

import robocode.JuniorRobot;

public class EstrategiaEvasiva implements CombatStrategy {

  private boolean ready = false;
  private int tick = 0;
  private int distance = 100;

  @Override
  public void init(JuniorRobot r) {
    if (r.others < 5) {
      ready = true;
    }
    r.setColors(JuniorRobot.yellow, JuniorRobot.purple, JuniorRobot.purple,
        JuniorRobot.purple, JuniorRobot.red);

    r.turnTo(270);
    r.turnGunTo(0);
    r.ahead(r.fieldWidth);
    ready = false;
  }

  @Override
  public void onTick(JuniorRobot r) {
    tick++;
    r.fire(2);
    if (r.others < 5) {
      ready = true;
    }
    r.ahead(distance);
    r.doNothing();
  }

  @Override
  public void onHitWall(JuniorRobot r) {
    r.turnRight(90);
    r.ahead(distance);
    // hacia el INTERIOR del mapa
    int inward = (r.heading + 90) % 360;
    r.turnGunTo((inward) % 360);
  }

  @Override
  public void onScannedRobot(JuniorRobot r) {

  }

  @Override
  public void onHitRobot(JuniorRobot r) {

    r.turnGunTo(r.hitRobotAngle);
    r.fire(3);
  }

  @Override
  public void onHitByBullet(JuniorRobot r) {

  }

  public boolean readyToAttack() {
    return ready;
  }
}