/*
 * La stratégie que doit suivre le fantome quand il chasse Pacman
 */

package serveur.model.EtatFantome;

import model.AgentAction;
import serveur.model.Strategie.StrategieAgent;
import serveur.model.Strategie.StrategieFractory;
import serveur.model.Fantome;

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
