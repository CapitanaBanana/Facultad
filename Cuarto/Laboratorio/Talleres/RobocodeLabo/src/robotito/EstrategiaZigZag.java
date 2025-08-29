package robotito;

import robocode.JuniorRobot;
import java.util.Random;

/**
 * Movimiento evasivo "medio aleatorio" usando turnAheadRight/Left.
 * - Siempre avanza con giro simultáneo (sin ahead "largo").
 * - Segmentos cortos con parámetros aleatorios (paso y grados).
 * - Suaviza paredes: cuando está cerca, sesga el giro hacia adentro.
 * - Reacciona a choques cambiando el sentido del zig.
 * - No dispara.
 */
public class EstrategiaZigZag implements CombatStrategy {

    private static final int STEP   = 80; // pasos cortos para no “clavarse”
    private static final int MARGIN = 22; // umbral para detectar pared

    private int heading; // rumbo continuo en grados (0..359)

    @Override
    public void init(JuniorRobot r) {
        r.setColors(JuniorRobot.black, JuniorRobot.blue, JuniorRobot.white,
                JuniorRobot.yellow, JuniorRobot.green);

        // Ángulo inicial aleatorio (evitamos múltiplos exactos de 90°)
        heading = (int)(Math.random() * 360);
        if (heading % 90 == 0) heading += 17;
        heading = norm(heading);

        r.turnTo(heading);
        r.ahead(STEP);
    }

    @Override
    public void onTick(JuniorRobot r) {
        // avanzar siempre en pasos cortos siguiendo el heading actual
        r.turnTo(heading);
        r.ahead(STEP);
        r.doNothing();
    }

    @Override
    public void onHitWall(JuniorRobot r) {
        // despegarse un poquito
        r.back(25);

        int W = r.fieldWidth, H = r.fieldHeight, x = r.robotX, y = r.robotY;
        boolean hitVertical   = (x <= MARGIN) || (x >= W - MARGIN);
        boolean hitHorizontal = (y <= MARGIN) || (y >= H - MARGIN);

        int h = heading;
        if (hitVertical)   h = 180 - h; // reflejo en pared vertical
        if (hitHorizontal) h = 360 - h; // reflejo en pared horizontal
        heading = norm(h);

        r.turnTo(heading);
        r.ahead(STEP);
    }

    @Override
    public void onHitRobot(JuniorRobot r) {
        // rebote simple contra robots: invertí rumbo
        heading = norm(heading + 180);
        r.back(20);
        r.turnTo(heading);
        r.ahead(STEP);
    }

    @Override
    public void onScannedRobot(JuniorRobot r) {
        // disparar sólo si va casi en nuestro rumbo (cono frontal)
        if (Math.abs(r.scannedBearing) <= 12) {
            r.turnGunTo(r.scannedAngle);
            double p = (r.scannedDistance < 140) ? 3.0 : (r.scannedDistance < 260 ? 2.0 : 1.2);
            if (r.gunReady) r.fire(p);
        }
    }

    @Override public void onHitByBullet(JuniorRobot r) {
        // micro-ajuste para no quedar alineado contra pared
        heading = norm(heading + 20);
    }

    // ---- helper ----
    private static int norm(int deg) {
        int d = deg % 360;
        return (d < 0) ? d + 360 : d;
    }
}
