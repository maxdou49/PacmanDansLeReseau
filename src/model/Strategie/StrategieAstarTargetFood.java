package model.Strategie;

import model.Iterateur.IterateurAgent;
import model.Iterateur.IterateurAgentPosition;
import model.Iterateur.IterateurKiller;
import model.Agent;
import model.PositionAgent;

/*
 * Algorithme a* cherchant la nourriture.
 */

public class StrategieAstarTargetFood extends StrategieAStar {
    public StrategieAstarTargetFood(Agent a)
    {
        super(a);
    }

    public boolean isValid(PositionAgent pos)
    {
        if(super.isValid(pos))
        {
            //On cherche a éviter ce qui peux nous tuer
            boolean ghosthere = false;
            IterateurAgent iter = new IterateurKiller(new IterateurAgentPosition(agent.getGame().getAgentIter(), pos), agent);
            if(iter.hasNext())
            {
                ghosthere = true;
            }
            return !ghosthere;
        }
        return false;
    }

    public boolean isTarget(PositionAgent pos)
    {
        return (agent.getGame().getMaze().isFood(pos.getX(), pos.getY()) || agent.getGame().getMaze().isCapsule(pos.getX(), pos.getY()));
    }
}
