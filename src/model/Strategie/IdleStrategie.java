/*
 * Stratégie où un agent ne fait rien
 */

package model.Strategie;
import model.Agent;
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
