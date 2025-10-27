package robotitoKotlin2

import robocode.JuniorRobot
import robotitoKotlin.CombatStrategy
import kotlin.random.Random

class TwoPhaseCombatStrategy: Estratega{
    val terminatorThreshold: Int = 5
    val INSTANCE: TwoPhaseCombatStrategy= TwoPhaseCombatStrategy;
    private var nuevo = true


    private class EstrategiaEvasiva(robot: JuniorRobot): CombatStrategy(robot){
        
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
    private class EstrategiaOfensiva(robot: JuniorRobot): CombatStrategy(robot){
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
    override fun elegirEstrategia(robot: JuniorRobot): robotitoKotlin2.CombatStrategy {
        val estrategia: CombatStrategy
        if (robot.others > terminatorThreshold) {
            estrategia = TwoPhaseCombatStrategy.EstrategiaEvasiva.INSTANCE
            if (nuevo) {
                estrategia.init(robot)
                nuevo = false
            }
        } else {
            estrategia = TwoPhaseCombatStrategy.EstrategiaOfensiva.INSTANCE
            estrategia.init(robot)
        }
        return estrategia
    }
}
