package model.Strategie;

import model.Iterateur.IterateurAgent;
import model.Iterateur.IterateurKiller;
import model.Agent;
import model.PositionAgent;

/*
 * Stratégie a* n'allant pas a proximité des fantômes
 */

public class StrategieAstarTargetFoodV3 extends StrategieAstarTargetFood {
    public StrategieAstarTargetFoodV3(Agent a)
    {
        super(a);
    }

    public boolean isValid(PositionAgent pos)
    {
        if(super.isValid(pos))
        {
            //On évite les fantomes qui peuvent nous tuer
            Agent a = null;
            int d = Integer.MAX_VALUE;
            IterateurAgent iter = new IterateurKiller(agent.getGame().getAgentIter(), agent);
            while(iter.hasNext())
            {
                a = iter.next();
                d = Math.min(d, a.getPos().squaredDistance(pos));
            }
            int maxdist = getParam();
            return d > (maxdist * maxdist); //Distance au carré. Au moins 5 cases d'un fantome
        }
        return false;
    }
}
