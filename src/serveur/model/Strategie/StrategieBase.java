/*
 * Base pour une stratégie où on choisi une direction et qu'on tourne si on peux pas y aller
 */

package serveur.model.Strategie;
import serveur.model.Agent;
import model.AgentAction;
import model.PositionAgent;

public abstract class StrategieBase extends StrategieAgent {
    public StrategieBase(Agent a)
    {
        super(a);
    }

    //Il semblerait que les directions ne sont pas correctement organisé
    public int nextDir(int dir)
    {
        final int d[] = {AgentAction.EAST, AgentAction.WEST, AgentAction.SOUTH, AgentAction.NORTH, AgentAction.STOP};
        return d[dir];
    }

    public int flip(int dir)
    {
        final int d[] = {AgentAction.SOUTH, AgentAction.NORTH, AgentAction.WEST, AgentAction.EAST, AgentAction.STOP};
        return d[dir];
    }

    public abstract int selectDir();

    public AgentAction rotate(int d)
    {
        //Tourner tant qu'on a un mur(ou que c'est la direction d'où on vient)
        AgentAction a = new AgentAction(d);
        PositionAgent p = agent.getPos();
        int nbTurn = 0;
        //System.out.println(String.format("%d C: %d", p.getDir(), d));
        //On ne recule pas et on ne va pas dans un mur
        //Sauf que le jeu plante si on n'autorise pas le demi-tour dans certaines situation donc on l'autorise s'il n'y a pas d'autre possibilité
        while((nbTurn < 4 && flip(p.getDir()) == a.get_direction()) || agent.getGame().getMaze().isWall(p.getX()+a.get_vx(), p.getY()+a.get_vy()))
        {
            int dir = nextDir(a.get_direction());
            //System.out.println("D:"+Integer.toString(dir));
            a = new AgentAction(dir);
            nbTurn++;
        }
        return a;
    }

    public AgentAction getAction()
    {
        return rotate(selectDir());
    }
}
