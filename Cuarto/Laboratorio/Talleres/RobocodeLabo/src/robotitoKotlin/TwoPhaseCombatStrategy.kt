package robotitoKotlin

import robocode.JuniorRobot
import kotlin.random.Random

class TwoPhaseCombatStrategy private constructor(): Estratega {
  private val terminatorThreshold = 5

  companion object{
    val INSTANCE = TwoPhaseCombatStrategy()
    lateinit var robot: JuniorRobot
  }

  private object EstrategiaEvasiva: CombatStrategy() {
    private const val DISTANCE = 100

    private fun moveToLeftWall(){
      robot.turnTo(270)
      robot.turnGunTo(0)
      robot.ahead(robot.fieldWidth)
    }
    override fun onTick() {
      robot.fire(bulletHalfPower)
      robot.ahead(DISTANCE)
    }

    init{
      robot.setColors(
        JuniorRobot.yellow, JuniorRobot.purple, JuniorRobot.red,
        JuniorRobot.yellow, JuniorRobot.red
      )

      moveToLeftWall()
    }

    override fun onHitRobot() {
      robot.turnGunTo(robot.hitRobotAngle)
      robot.fire(bulletMaxPower)
    }

    override fun onHitWall() {
      robot.turnRight(90)
      robot.ahead(DISTANCE)
      val inward = (robot.heading + 90) % 360
      robot.turnGunTo((inward) % 360)
    }

    override fun onHitByBullet() {

    }

    override fun onScannedRobot() {

    }

  }

  private object EstrategiaOfensiva: CombatStrategy() {
    private var  steps = 0
    private fun discreteFire( power: Double){
      if(robot.gunReady) robot.fire(power)

    }

   init{
      steps = Random.nextInt(1,200)
      robot.setColors(
        JuniorRobot.red, JuniorRobot.black, JuniorRobot.black,
        JuniorRobot.red, JuniorRobot.red
      )
    }

    override fun onTick() {
      robot.ahead(steps)

      steps = Random.nextInt(1,200)
      robot.back(steps)

      val inward = (robot.heading + 90) % 360

      robot.turnGunTo((inward) % 360)
    }

    override fun onScannedRobot() {
      robot.turnGunTo(robot.scannedAngle)
      val power = if(robot.scannedDistance < 200) bulletMaxPower else bulletHalfPower
      discreteFire(power)
    }

    override fun onHitWall() {
    }

    override fun onHitByBullet() {
      robot.turnGunTo(robot.hitByBulletAngle)
      discreteFire(bulletHalfPower)
    }

    override fun onHitRobot() {
      robot.turnGunTo(robot.hitRobotAngle)
      robot.fire(bulletMaxPower)
    }

  }

  override fun elegirEstrategia(): CombatStrategy = if (robot.others < terminatorThreshold) EstrategiaOfensiva else  EstrategiaEvasiva

}