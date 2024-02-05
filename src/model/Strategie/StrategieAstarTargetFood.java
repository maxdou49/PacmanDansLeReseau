package model.Strategie;

import model.Iterateur.IterateurAgent;
import model.Agent;
import model.PositionAgent;

public class StrategieAstarTargetFood extends StrategieAStar {
    public StrategieAstarTargetFood(Agent a)
    {
        super(a);
    }

    public boolean isValid(PositionAgent pos)
    {
        if(super.isValid(pos))
        {
            //On cherche a éviter les fantomes qui peuvent nous tuer
            boolean ghosthere = false;
            Agent a = null;
            IterateurAgent iter = agent.getGame().ghostIsHereIter(pos);
            while(iter.hasNext())
            {
                a = iter.next();
                if(a.canKillPacman())
                {
                    ghosthere = true;
                }
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
