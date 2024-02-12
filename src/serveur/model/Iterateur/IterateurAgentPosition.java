package serveur.model.Iterateur;

import serveur.model.Agent;
import model.PositionAgent;

/*
 * Ne garde que les agents ayant une certaine position
 */

public class IterateurAgentPosition extends IterateurAgent {
    IterateurAgent iter;
    PositionAgent pos;
    public IterateurAgentPosition(IterateurAgent it, PositionAgent pos)
    {
        super();
        iter = it;
        this.pos = pos;
    }

    public Agent get(int pos)
    {
        return iter.get(pos);
    }

    public int size()
    {
        return iter.size();
    }

    @Override
    protected boolean valid(Agent a) {
        if(iter.valid(a))
        {
            return a.getPos().equals(pos);
        }
        return false;
    }
}
