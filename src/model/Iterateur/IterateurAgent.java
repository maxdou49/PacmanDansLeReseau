package model.Iterateur;

import model.Agent;

public abstract class IterateurAgent {
    protected int position; //Prochain élément a itérer

    public IterateurAgent()
    {
        position = 0;
    }

    abstract protected boolean valid(Agent a);
    abstract protected Agent get(int pos);
    abstract protected int size();

    public boolean hasNext()
    {
        int pos = position;
        boolean val = false;
        while(pos < size() && !val)
        {
            val = valid(get(pos));
            pos++;
        }
        return val;
    }

    public Agent next()
    {
        Agent a = null;   
        while(position < size() && a == null)
        {
            Agent test = get(position);
            if(valid(test))
            {
                a = test;
            }
            position++;
        }
        return a;
    }
}
