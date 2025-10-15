package robotitoKotlin

import robocode.JuniorRobot
import kotlin.random.Random

class EstrategiaOfensiva(robot: JuniorRobot): CombatStrategy(robot) {
    private var steps = Random.nextInt(1,200)


    //Bloque de inicializacion
    init {
        robot.setColors(JuniorRobot.red, JuniorRobot.black, JuniorRobot.black,
            JuniorRobot.red, JuniorRobot.red)
    }

    private fun discreteFire(power: Double){
        if(robot.gunReady) robot.fire(power)
    }

    override fun onTick(){
        robot.ahead(steps)

        steps = Random.nextInt(1,200)
        robot.back(steps)

        val inward = (robot.heading + 90) % 360
        robot.turnGunTo((inward) % 360)

    }

    override fun onScannedRobot() {
        robot.turnGunTo(robot.scannedAngle)

        val power = if (robot.scannedDistance > 200) this.bulletMaxPower else this.bulletHalfPower

        this.discreteFire(power)

    }

    override fun onHitWall() {

    }

    override fun onHitByBullet() {
        robot.turnGunTo(robot.hitByBulletAngle)
        this.discreteFire(this.bulletHalfPower)
    }


}