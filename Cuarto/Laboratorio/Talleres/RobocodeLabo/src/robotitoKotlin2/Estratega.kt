package robotitoKotlin2

import robocode.JuniorRobot

interface Estratega {
    fun elegirEstrategia(robot: JuniorRobot): CombatStrategy
}