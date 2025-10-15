package robotitoKotlin

import robocode.JuniorRobot

class Robotito: JuniorRobot() {

    lateinit var strategy: CombatStrategy

    override fun run(){
        strategy = EstrategiaEvasiva(this)
        var checked = false

        while(true){
            strategy.onTick()

            if(this.strategy.readyToAttack() && !checked){
                this.strategy = EstrategiaOfensiva(this)
                checked = true
            }

        }

    }

    override fun onScannedRobot(){
        strategy.onScannedRobot()
    }

    override fun onHitWall(){
        strategy.onHitWall()
    }

    override fun onHitRobot() {
        strategy.onHitRobot()
    }

    override fun onHitByBullet() {
        strategy.onHitByBullet()
    }

}