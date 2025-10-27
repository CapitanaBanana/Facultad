package viejo2;

import robocode.JuniorRobot;

public class Robotito extends JuniorRobot {

    private Estratega strategist = TwoPhaseCombatStrategy.INSTANCE;

    private CombatStrategy strategy;

    @Override
    public void run() {
        setStrategy(strategist.elegirEstrategia(this));
        while(true){
            onTick();
        }
    }

    public void setStrategy(CombatStrategy newStrat) {
        strategy = newStrat;
    }
    private void onTick(){
        strategist.elegirEstrategia(this).onTick();
    }

    public void onHitWall(){
      strategist.elegirEstrategia(this).onHitWall(); }

    public void onHitRobot() { strategist.elegirEstrategia(this).onHitRobot(); }

    public void onHitByBullet() {
      strategist.elegirEstrategia(this).onHitByBullet();
    }

    public void onScannedRobot(){ strategist.elegirEstrategia(this).onScannedRobot(); }
}