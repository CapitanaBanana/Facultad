package robotitoKotlin

import robocode.JuniorRobot

class Robotito: JuniorRobot() {
  private var strategist = TwoPhaseCombatStrategy.INSTANCE

  init{
    TwoPhaseCombatStrategy.robot = this
  }
  override fun run() {
    while (true){
        onTick()
    }
  }

  private fun onTick() =  strategist.elegirEstrategia().onTick()

  override fun onHitWall() = strategist.elegirEstrategia().onHitWall()

  override fun onHitRobot() = strategist.elegirEstrategia().onHitRobot()

  override fun onHitByBullet() = strategist.elegirEstrategia().onHitByBullet()

  override fun onScannedRobot() = strategist.elegirEstrategia().onScannedRobot()




}