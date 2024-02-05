package model.Iterateur;

import model.Agent;
import model.PositionAgent;

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
