package robotito;

import robocode.JuniorRobot;

public class EstrategiaEvasiva2 implements CombatStrategy {
  private int nuevoHeading;
  private static final int MARGEN = 10;
  private static final int STEP = 60; // más rápido que 30, menos brusco que 100
  private static final int CORNER_DEG = 10; // giro suave por tick
  private static final int TRIGGER = 160; // empezar curva con algo de margen

  // estado de curva
  private boolean enCurva = false;
  private int curvaAcum = 0; // grados acumulados de la curva actual

  @Override
  public void init(JuniorRobot r) {
    r.setColors(JuniorRobot.gray, JuniorRobot.blue, JuniorRobot.white,
        JuniorRobot.yellow, JuniorRobot.green);

    nuevoHeading = 0;
    r.turnTo(nuevoHeading);
    // desde ahora SIEMPRE movemos en onTick por pasos
  }

  @Override
  public void onTick(JuniorRobot r) {
    int dist = distToFront(r, nuevoHeading);

    if (enCurva || dist <= TRIGGER) {
      // curva redondeada: girar un poco y avanzar
      enCurva = true;
      r.turnAheadRight(STEP, CORNER_DEG);
      curvaAcum += CORNER_DEG;

      // ¿completamos ~90°? fijar el nuevo lado
      if (curvaAcum >= 90) {
        enCurva = false;
        curvaAcum = 0;
        nuevoHeading = (nuevoHeading + 90) % 360;
      }
    } else {
      // recta: avanzar corrigiendo rumbo para quedar paralelo a nuevoHeading
      int err = angleDiff(nuevoHeading, r.heading); // [-180..180]
      if (err > 0) {
        r.turnAheadLeft(STEP, Math.min(4, err));
      } else if (err < 0) {
        r.turnAheadRight(STEP, Math.min(4, -err));
      } else {
        r.turnAheadRight(STEP, 0);
      }
    }

    r.doNothing();
  }

  @Override
  public void onHitWall(JuniorRobot r) {
    // recuperación suave: despegarse y entrar en curva (no usar ahead(5000))
    r.back(15);
    enCurva = true;
    curvaAcum = 0;
    // al terminar los ~90°, onTick ajusta nuevoHeading
  }

  @Override
  public void onScannedRobot(JuniorRobot r) {
  }

  @Override
  public void onHitRobot(JuniorRobot r) {
    r.back(25); // despegarse y dejar que onTick siga la lógica
  }

  @Override
  public void onHitByBullet(JuniorRobot r) {
  }

  // ===== helpers =====
  private static int distToFront(JuniorRobot r, int heading) {
    int W = r.fieldWidth, H = r.fieldHeight, x = r.robotX, y = r.robotY;
    if (heading == 0)
      return (W - MARGEN) - x; // derecha
    if (heading == 90)
      return (H - MARGEN) - y; // arriba
    if (heading == 180)
      return x - MARGEN; // izquierda
    if (heading == 270)
      return y - MARGEN; // abajo
    return 9999;
  }

  // diferencia angular normalizada [-180..180]
  private static int angleDiff(int target, int h) {
    int d = target - h;
    d = ((d + 180) % 360 + 360) % 360 - 180;
    return d;
  }
}
