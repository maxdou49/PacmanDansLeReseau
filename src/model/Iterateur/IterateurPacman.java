package model.Iterateur;

import model.Agent;
import model.Pacman;

/*
 * Ne garde que les pacman
 */

public class IterateurPacman extends IterateurAgent {
    IterateurAgent iter;
    public IterateurPacman(IterateurAgent it)
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
            return (a instanceof Pacman);
        }
        return false;
    }
    
}
