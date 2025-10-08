package RobotitoKotlin
import robocode.*;

abstract class CombatStrategy(protected val robot: JuniorRobot) {


    protected val bulletHalfPower = 1.5
    protected val bulletMaxPower = 3.0

    abstract fun onTick()
    abstract fun onScannedRobot()
    abstract fun onHitWall()
    abstract fun onHitRobot()
    abstract fun onHitByBullet()

    open fun readyToAttack(): Boolean {
        return true
    }
}
