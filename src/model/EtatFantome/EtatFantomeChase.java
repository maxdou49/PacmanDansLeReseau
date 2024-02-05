/*
 * La stratégie que doit suivre le fantome quand il chasse Pacman
 */

package model.EtatFantome;

import model.AgentAction;
import model.Fantome;
import model.Strategie.StrategieAgent;
import model.Strategie.StrategieFractory;

public class EtatFantomeChase extends EtatFantome {
    StrategieAgent strategie;

    public EtatFantomeChase(Fantome a)
    {
        super(a);
        setStrategie();
    }

    public AgentAction takeTurn()
    {
        return strategie.getAction();
    }

    public void setStrategie()
    {
        try
        {
            strategie = StrategieFractory.createStrategie(agent, agent.getChaseStrategie());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
