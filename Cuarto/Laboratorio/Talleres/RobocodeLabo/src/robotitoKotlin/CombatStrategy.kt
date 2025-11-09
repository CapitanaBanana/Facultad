package robotitoKotlin

abstract class CombatStrategy  {
  protected val bulletHalfPower: Double = 1.5
  protected val bulletMaxPower: Double = 3.0

  abstract fun onTick()

  abstract fun onScannedRobot()

  abstract fun onHitWall()

  abstract fun onHitByBullet()

  abstract fun onHitRobot()

}