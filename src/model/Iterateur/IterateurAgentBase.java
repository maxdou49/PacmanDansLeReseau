package model.Iterateur;

import java.util.ArrayList;

import model.Agent;

public class IterateurAgentBase extends IterateurAgent {
    protected ArrayList<Agent> liste;
    public IterateurAgentBase(ArrayList<Agent> liste)
    {
        super();
        this.liste = liste;
    }
    @Override
    protected boolean valid(Agent a) {
        return true;
    }

    @Override
    protected Agent get(int pos) {
        return liste.get(pos);
    }

    @Override
    protected int size() {
        return liste.size();
    }
    
}
