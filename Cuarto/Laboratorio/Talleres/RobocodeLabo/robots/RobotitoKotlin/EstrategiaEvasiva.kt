package robotitoKotlin

import robocode.JuniorRobot

class EstrategiaEvasiva(robot: JuniorRobot): CombatStrategy(robot) {
    private val terminatorThreshold =  5
    private val leftWallAngle = 270
    private val distance = 100
    private var ready = false


    //Bloque de inicializacion
    init{
        robot.setColors(JuniorRobot.yellow, JuniorRobot.purple, JuniorRobot.red,
            JuniorRobot.purple, JuniorRobot.red)

        ready =  robot.others < this.terminatorThreshold

        robot.turnTo(this.leftWallAngle)
        robot.turnGunTo(0)
        robot.ahead(robot.fieldWidth)
    }

    override fun onTick() {
        robot.fire(this.bulletHalfPower)
        if(robot.others < this.terminatorThreshold) ready = true
        robot.ahead(distance)
    }

    override fun onHitWall() {
        robot.turnRight(90)
        robot.ahead(distance)
        val inward = (robot.heading + 90) % 360
        robot.turnGunTo((inward) % 360)
    }

    override fun onScannedRobot() {

    }


    override fun onHitByBullet() {

    }

    override fun readyToAttack(): Boolean = ready
}