package robotito;

import robocode.*;

public interface CombatStrategy {
  void init(JuniorRobot r);

  void onTick(JuniorRobot r);

  void onScannedRobot(JuniorRobot r);

  void onHitWall(JuniorRobot r);

  void onHitRobot(JuniorRobot r);

  void onHitByBullet(JuniorRobot r);


}
