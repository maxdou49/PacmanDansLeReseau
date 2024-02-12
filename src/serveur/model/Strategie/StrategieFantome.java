/*
 * Stratégie où un fantome ne fait rien
 */
package serveur.model.Strategie;
import serveur.model.Agent;
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
