package robotitoKotlin2

import robotitoKotlin.CombatStrategy
import robotitoKotlin.EstrategiaEvasiva
import robotitoKotlin.EstrategiaOfensiva

import robocode.JuniorRobot

class Robotito: JuniorRobot() {

    lateinit var strategy: TwoPhaseCombatStrategy

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