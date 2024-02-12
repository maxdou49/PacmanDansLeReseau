/*
 * Base pour les stratégies
 */

package serveur.model.Strategie;
import serveur.model.Agent;
import model.AgentAction;
import serveur.model.Pacman;

public abstract class StrategieAgent {
    protected Agent agent;
    static protected int paramPacman = 0;
    static protected int paramFantome = 0;

    public int getParam()
    {
        if(agent instanceof Pacman)
        {
            return paramPacman;
        }
        return paramFantome;
    }

    static public void setParamPacman(int val)
    {
        paramPacman = val;
    }

    static public void setParamFantome(int val)
    {
        paramFantome = val;
    }

    public StrategieAgent(Agent a)
    {
        agent = a;
    }

    public abstract AgentAction getAction();
}
