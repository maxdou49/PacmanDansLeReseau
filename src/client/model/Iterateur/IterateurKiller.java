package client.model.Iterateur;

import client.model.Agent;

/*
 * Ne garde que ce qui peut tuer l'agent spécifié
 */

public class IterateurKiller extends IterateurAgent {
    IterateurAgent iter;
    Agent agent;
    public IterateurKiller(IterateurAgent it, Agent a)
    {
        super();
        iter = it;
        this.agent = a;
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
            return (agent.canBeKilledBy(a) && a.canKillThis(agent));
        }
        return false;
    }
}
