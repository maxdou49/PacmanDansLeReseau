/*
 * Stratégie où un agent fait une action aléatoire
 */
package model.Strategie;
import model.Agent;
import model.AgentAction;

public class RandomStrategie extends StrategieAgent {

    public RandomStrategie(Agent a)
    {
        super(a);
    }

    public AgentAction getAction()
    {
        return new AgentAction(agent.getGame().getRandom().next(5));
    }
}
