package model.Strategie;

import model.Agent;
import model.PositionAgent;

public class StrategieAstarTargetPacman extends StrategieAStar {
    public StrategieAstarTargetPacman(Agent a)
    {
        super(a);
    }

    public boolean isTarget(PositionAgent pos)
    {
        return (agent.getGame().pacmanIsHere(pos));
    }
}
