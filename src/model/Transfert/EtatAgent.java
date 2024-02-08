package model.Transfert;

import model.PositionAgent;

/*
 * Classe contenant l'état d'un agent
 */

public class EtatAgent {
    protected PositionAgent pos;
    protected boolean alive;

    public EtatAgent(PositionAgent pos, boolean alive)
    {
        this.pos = pos;
        this.alive = alive;
    }

    public PositionAgent getPos()
    {
        return pos;
    }

    public boolean getAlive()
    {
        return alive;
    }
}
