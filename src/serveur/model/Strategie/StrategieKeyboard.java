/*
 * Stratégie où on peux controller avec le clavier
 */

package serveur.model.Strategie;
import serveur.model.Agent;
import model.AgentAction;

public class StrategieKeyboard extends StrategieAgent {
    AgentAction action;

    public StrategieKeyboard(Agent a)
    {
        super(a);
        action = new AgentAction(AgentAction.STOP);
    }

    public AgentAction getAction()
    {
        AgentAction action = agent.getGame().getController().lireActionClient(agent.getPlayer());
        
        return (action != null) ? action : new AgentAction(AgentAction.STOP);
    }
}
