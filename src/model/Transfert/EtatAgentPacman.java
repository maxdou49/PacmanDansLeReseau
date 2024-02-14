package model.Transfert;

import model.PositionAgent;

public class EtatAgentPacman implements EtatAgent {

    protected PositionAgent pos;

    public EtatAgentPacman(PositionAgent pos) {
        this.pos = pos;
    }
    
    public PositionAgent getPos()
    {
        return pos;
    }
}
