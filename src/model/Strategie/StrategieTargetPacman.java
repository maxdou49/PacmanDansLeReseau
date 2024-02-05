/*
 * Stratégie où on suit Pacman(stratégie du fantome rouge)
 */
package model.Strategie;

import model.Agent;
import model.PositionAgent;

public class StrategieTargetPacman extends StrategieTarget {
    public StrategieTargetPacman(Agent a)
    {
        super(a);
    }

    public PositionAgent getTarget()
    {
        Agent pacman = agent.getGame().getPacman();
        if(pacman == null)
        {
            return new PositionAgent(0, 0, 0);
        }
        return new PositionAgent(pacman.getPos().getX(), pacman.getPos().getY(), pacman.getPos().getDir());
    }
}
