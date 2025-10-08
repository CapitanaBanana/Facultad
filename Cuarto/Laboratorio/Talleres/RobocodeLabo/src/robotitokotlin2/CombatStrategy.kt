package robotitokotlin2

import robocode.JuniorRobot

abstract class CombatStrategy(protected val robot: JuniorRobot)  {
    val bulletHalfPower: Double = 1.5
    val bulletMaxPower: Double = 3.0

    abstract fun onTick()

    abstract fun onScannedRobot()

    abstract fun onHitWall()

    abstract fun onHitByBullet()

    fun onHitRobot(){
        robot.turnGunTo(robot.hitRobotAngle)
        robot.fire(this.bulletMaxPower)
    }

    open fun readyToAttack() : Boolean{
        return false
    }
}