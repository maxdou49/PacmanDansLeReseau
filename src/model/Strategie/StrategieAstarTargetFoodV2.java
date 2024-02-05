package model.Strategie;

import model.Iterateur.IterateurAgent;
import model.Iterateur.IterateurPacmanKiller;
import model.Agent;
import model.Noeud;

public class StrategieAstarTargetFoodV2 extends StrategieAstarTargetFood {
    public StrategieAstarTargetFoodV2(Agent a)
    {
        super(a);
    }

    public void getHeuristic(Noeud n)
    {
        //On évite les fantomes qui peuvent nous tuer
        Agent a = null;
        int d = Integer.MAX_VALUE;
        IterateurAgent iter = new IterateurPacmanKiller(agent.getGame().getGhostIter());
        while(iter.hasNext())
        {
            a = iter.next();
            d = Math.min(d, a.getPos().squaredDistance(n.pos));
        }
        n.setHeuristic(0 - getParam()*(Math.sqrt(d)));
    } 
}
