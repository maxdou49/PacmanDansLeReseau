package model.Strategie;

import model.Iterateur.IterateurAgent;
import model.Iterateur.IterateurKiller;
import model.Agent;
import model.Noeud;

/*
 * Algorithme a* préviligiant les cases loin des fantomes.
 */

public class StrategieAstarTargetFoodV2 extends StrategieAstarTargetFood {
    public StrategieAstarTargetFoodV2(Agent a)
    {
        super(a);
    }

    public void getHeuristic(Noeud n)
    {
        //On évite ce qui peut nous tuer
        Agent a = null;
        int d = Integer.MAX_VALUE;
        IterateurAgent iter = new IterateurKiller(agent.getGame().getAgentIter(), agent);
        while(iter.hasNext())
        {
            a = iter.next();
            d = Math.min(d, a.getPos().squaredDistance(n.pos));
        }
        n.setHeuristic(0 - getParam()*(Math.sqrt(d)));
    } 
}
