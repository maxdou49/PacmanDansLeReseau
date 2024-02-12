package model.Transfert;

import model.PositionAgent;

public class EtatAgentPacman implements EtatAgent {

    protected boolean alive;
    protected PositionAgent pos;

    public EtatAgentPacman(PositionAgent pos, boolean alive) {
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
