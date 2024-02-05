/*
 * La gestion des états d'un fantome
 */

package model.EtatFantome;

import model.AgentAction;
import model.Fantome;

public abstract class EtatFantome {
    protected Fantome agent;

    public EtatFantome(Fantome a)
    {
        agent = a;
    }

    public abstract AgentAction takeTurn();
    public abstract void setStrategie();
}
