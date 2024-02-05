package model.Iterateur;

import model.Agent;

public class IterateurGhostKiller extends IterateurAgent {
    IterateurAgent iter;
    public IterateurGhostKiller(IterateurAgent it)
    {
        super();
        iter = it;
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
            return a.canKillGhost();
        }
        return false;
    }
}
