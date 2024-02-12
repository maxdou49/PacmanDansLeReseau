package model.Transfert;

import model.PositionAgent;

public class EtatAgentFantome implements EtatAgent {
    protected boolean frightened;
    protected PositionAgent pos;
    protected boolean alive;

    public EtatAgentFantome(PositionAgent pos, boolean alive, boolean frightened)
    {
        this.pos = pos;
        this.alive = alive;
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

    public boolean getAlive()
    {
        return alive;
    }
}
