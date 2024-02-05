package model.Iterateur;

import model.Agent;
import model.Fantome;

public class IterateurFantome extends IterateurAgent {
    IterateurAgent iter;
    public IterateurFantome(IterateurAgent it)
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
            return (a instanceof Fantome);
        }
        return false;
    }
    
}
