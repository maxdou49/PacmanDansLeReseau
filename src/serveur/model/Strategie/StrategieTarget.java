/*
 * Stratégie où on cherche a rejoindre une cible en prenant la direction la plus proche(stratégie du jeu de base)
 */

package serveur.model.Strategie;

import serveur.model.Agent;
import model.AgentAction;
import model.PositionAgent;

public abstract class StrategieTarget extends StrategieBase {
    public StrategieTarget(Agent a)
    {
        super(a);
    }

    public abstract PositionAgent getTarget();

    public int selectDir()
    {
        PositionAgent target = getTarget();
        int dir = 4;
        int dirlen = Integer.MAX_VALUE;
        AgentAction a = new AgentAction(dir);
        PositionAgent p = agent.getPos();
        for(int i = 0; i < 4; ++i)
        {
            //On ne fait pas demi-tour
            if(flip(i) == p.getDir())
            {
                continue;
            }
            a = new AgentAction(i);
            PositionAgent nextPos = new PositionAgent(p.getX()+a.get_vx(), p.getY()+a.get_vy(), i);
            //Si la case est libre et est plus proche de la cible que celle retenue
            if(!agent.getGame().getMaze().isWall(nextPos.getX(), nextPos.getY()) && nextPos.squaredDistance(target) < dirlen)
            {
                dirlen = nextPos.squaredDistance(target);
                dir = i;
            }
        }
        //Des fois qu'on tombe dans une impasse
        if(dir == 4)
        {
            dir = flip(p.getDir());
        }
        return dir;
    }
}
