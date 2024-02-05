/*
 * Stratégie d'un fantome quand il est vulnérable
 */
package model.EtatFantome;

import model.AgentAction;
import model.Fantome;
import model.Strategie.StrategieAgent;
import model.Strategie.StrategieFractory;

public class EtatFantomeFright extends EtatFantome {
    StrategieAgent strategie;

    public EtatFantomeFright(Fantome a)
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
            strategie = StrategieFractory.createStrategie(agent, agent.getFrightStrategie());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
