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
        if(action != null)
        {
            return action;
        }
        return new AgentAction(AgentAction.STOP);
    }
}
