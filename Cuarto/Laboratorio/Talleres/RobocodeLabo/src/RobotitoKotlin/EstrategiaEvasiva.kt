package RobotitoKotlin

import robocode.JuniorRobot


class EstrategiaEvasiva(robot: JuniorRobot) : CombatStrategy(robot) {
    val terminatorThreshold= 5
    val leftWallAngle= 270
    var ready= false
    val distance = 100
    init{
        ready = robot.others < this.terminatorThreshold;

        robot.setColors(
            JuniorRobot.yellow, JuniorRobot.purple, JuniorRobot.red,
            JuniorRobot.purple, JuniorRobot.red)

        robot.turnTo(leftWallAngle)
        robot.turnGunTo(0)
        robot.ahead(robot.fieldWidth)
    }

    override fun onTick() {
        robot.fire(bulletHalfPower)
        if (robot.others < this.terminatorThreshold) {
            ready = true
        }
        robot.ahead(distance)
        robot.doNothing()
    }

    override fun onScannedRobot() {

    }

    override fun onHitWall() {
        robot.turnRight(90)
        robot.ahead(distance)

        // hacia el INTERIOR del mapa
        val inward = (robot.heading + 90) % 360
        robot.turnGunTo((inward) % 360)
    }

    override fun onHitRobot() {
        robot.turnGunTo(robot.hitRobotAngle)
        robot.fire(bulletMaxPower)
    }

    override fun onHitByBullet() {
    }

    override fun readyToAttack(): Boolean {
        return ready
    }

}