package serveur.model.Strategie;

import serveur.model.Agent;
import model.PositionAgent;

/*
 * Stratégie A* cherchant Pacman
 */

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
