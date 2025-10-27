package robotitoKotlin2

import robocode.JuniorRobot

abstract class CombatStrategy(protected val robot: JuniorRobot)  {

    var bulletHalfPower: Double = 1.5
    var bulletMaxPower: Double = 3.0

    abstract fun onTick()

    abstract fun onScannedRobot()

    abstract fun onHitWall()

    abstract fun onHitRobot()

    abstract fun onHitByBullet()

}