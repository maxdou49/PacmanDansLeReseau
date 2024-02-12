/*
 * Stratégie où un agent ne fait rien
 */

package serveur.model.Strategie;
import serveur.model.Agent;
import model.AgentAction;

public class IdleStrategie extends StrategieAgent {
    public IdleStrategie(Agent a)
    {
        super(a);
    }

    public AgentAction getAction()
    {
        return new AgentAction(AgentAction.STOP);
    }
}
