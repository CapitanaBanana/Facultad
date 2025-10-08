package Robotito

import robocode.*;

abstract class CombatStrategy {

    protected lateinit var robot: JuniorRobot
    protected val bulletHalfPower = 1.5
    protected val bulletMaxPower = 3.0

    fun init(robot: JuniorRobot) {
        this.robot = robot
        onInit()
    }

    abstract fun onInit()
    abstract fun onTick()
    abstract fun onScannedRobot()
    abstract fun onHitWall()
    abstract fun onHitRobot()
    abstract fun onHitByBullet()

    open fun readyToAttack(): Boolean {
        return true
    }
}
