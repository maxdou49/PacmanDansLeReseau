/*
 * Stratégie où un agent ne fait rien
 */

package client.model.Strategie;
import client.model.Agent;
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
