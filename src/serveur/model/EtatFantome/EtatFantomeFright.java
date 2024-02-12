/*
 * Stratégie d'un fantome quand il est vulnérable
 */
package serveur.model.EtatFantome;

import model.AgentAction;
import serveur.model.Strategie.StrategieAgent;
import serveur.model.Strategie.StrategieFractory;
import serveur.model.Fantome;

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
