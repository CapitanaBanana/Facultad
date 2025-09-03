package robotito3;

import robocode.JuniorRobot;

public class EstrategiaOfensiva implements CombatStrategy {

    private int tick = 0;
    private int targetAngle = -1;
    private int targetDistance = Integer.MAX_VALUE;

    @Override
    public void init(JuniorRobot r) {
        r.out.println("🔴 MODO TERMINATOR ACTIVADO");
        r.setColors(JuniorRobot.red, JuniorRobot.black, JuniorRobot.black,
                JuniorRobot.red, JuniorRobot.red);
        tick = 0;
        targetAngle = -1;
        targetDistance = Integer.MAX_VALUE;
    }

    @Override
    public void onTick(JuniorRobot r) {
        tick++;

        // Si tenemos un objetivo válido, ir hacia él
        if (targetAngle != -1) {
            r.turnTo(targetAngle);

            if (targetDistance > 80) {
                r.ahead(50); // avanzar hasta acercarse
            } else {
                r.back(20); // si está encima, dar espacio
            }
        } else {
            // Si no hay target, barrer el radar
            r.turnGunRight(30);
        }

        r.doNothing();
    }

    @Override
    public void onScannedRobot(JuniorRobot r) {
        // Siempre quedarse con el enemigo más cercano
        if (r.scannedDistance < targetDistance) {
            targetAngle = r.scannedAngle;
            targetDistance = r.scannedDistance;
        }

        // Apuntar y disparar
        r.turnGunTo(r.scannedAngle);

        double p = (r.scannedDistance < 120) ? 3.0
                : (r.scannedDistance < 250) ? 2.0
                : 1.2;

        if (r.gunReady) {
            r.fire(p);
        }

        // Actualizar estado del target
        targetAngle = r.scannedAngle;
        targetDistance = r.scannedDistance;
    }

    @Override
    public void onHitWall(JuniorRobot r) {
        r.back(40);
        r.turnRight(45);
    }

    @Override
    public void onHitRobot(JuniorRobot r) {
        // retroceder y seguir disparando
        r.back(30);
        if (r.gunReady) r.fire(2.0);
    }

    @Override
    public void onHitByBullet(JuniorRobot r) {
        // micro evasión para no quedar fijo
        r.turnAheadRight(40, 20);
    }
}
