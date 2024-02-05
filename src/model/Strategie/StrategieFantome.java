/*
 * Stratégie où un fantome ne fait rien
 */
package model.Strategie;
import model.Agent;
import model.AgentAction;

public class StrategieFantome extends StrategieAgent {
    public StrategieFantome(Agent a)
    {
        super(a);
    }

    public AgentAction getAction()
    {
        return new AgentAction(AgentAction.STOP);
    }
}
