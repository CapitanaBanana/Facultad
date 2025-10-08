package RobotitoKotlin
import robocode.JuniorRobot
    class EstrategiaOfensiva(robot: JuniorRobot) : CombatStrategy(robot) {
        init {
            robot.setColors(
                JuniorRobot.red, JuniorRobot.black, JuniorRobot.black,
                JuniorRobot.red, JuniorRobot.red
            )

            while (true) {
                var steps = (Math.random() * 200 + 1).toInt()
                robot.ahead(steps)

                steps = (Math.random() * 200 + 1).toInt()
                robot.back(steps)

                val inward = (robot.heading + 90) % 360
                robot.turnGunTo((inward) % 360)
            }
        }
        private fun discreteFire(power:Double){
            if (robot.gunReady) {
                robot.fire(power)
            }
        }

        override fun onTick() {

        }

        override fun onScannedRobot() {
            robot.turnGunTo(robot.scannedAngle)

            // Ajustar potencia en funciÃ³n de la distancia
            val power = if (robot.scannedDistance < 200) bulletMaxPower else bulletHalfPower
            this.discreteFire(power)
        }

        override fun onHitWall() {

        }

        override fun onHitRobot() {
            robot.turnGunTo(robot.hitRobotAngle);
            robot.fire(bulletMaxPower)
        }

        override fun onHitByBullet() {
            robot.turnGunTo(robot.hitByBulletAngle);
            this.discreteFire(bulletHalfPower)
        }

}