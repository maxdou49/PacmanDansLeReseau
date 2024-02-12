package model.Transfert;

import model.PositionAgent;

public class EtatAgentFantome extends EtatAgent {
    protected boolean frightened;

    public EtatAgentFantome(PositionAgent pos, boolean alive, boolean frightened)
    {
        super(pos, alive);
        this.frightened = frightened;
        this.type = 'f';
    }

    public boolean getFrightened()
    {
        return frightened;
    }
}
