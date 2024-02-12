package model.Transfert;

import model.PositionAgent;

public class EtatAgentPacman extends EtatAgent {

    public EtatAgentPacman(PositionAgent pos, boolean alive) {
        super(pos, alive);
        this.type = 'p';
    }
    
}
