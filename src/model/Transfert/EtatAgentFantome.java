package model.Transfert;

import model.PositionAgent;

public class EtatAgentFantome extends EtatAgent {
    protected boolean frightened;

    public EtatAgentFantome(PositionAgent pos, boolean alive, boolean frightened)
    {
        super(pos, alive);
        this.frightened = frightened;
    }

    public boolean getFrightened()
    {
        return frightened;
    }
}
