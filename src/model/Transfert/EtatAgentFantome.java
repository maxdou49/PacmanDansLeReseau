package model.Transfert;

import model.PositionAgent;

public class EtatAgentFantome implements EtatAgent {
    protected boolean frightened;
    protected PositionAgent pos;

    public EtatAgentFantome(PositionAgent pos, boolean frightened)
    {
        this.pos = pos;
        this.frightened = frightened;
    }

    public boolean getFrightened()
    {
        return frightened;
    }

    public PositionAgent getPos()
    {
        return pos;
    }
}
