package RobotitoKotlin

import robocode.JuniorRobot

class Robotito: JuniorRobot() {
    private lateinit var strategy: CombatStrategy;
    override fun run(){
        strategy = EstrategiaEvasiva(this)

        if (this.strategy.readyToAttack()) {
            this.setStrategy(EstrategiaOfensiva(this))
        }
        strategy.onTick()
    }
    fun setStrategy(newStrat: CombatStrategy) {
        strategy = newStrat
    }

    override fun onScannedRobot() = strategy.onScannedRobot()
    override fun onHitWall() = strategy.onHitWall()
    override fun onHitRobot() = strategy.onHitRobot()
    override fun onHitByBullet() = strategy.onHitByBullet()
}