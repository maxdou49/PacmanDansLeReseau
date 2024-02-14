/*
 * Un fantome
 */
package client.model;
import client.model.Strategie.ListeStrategie;
import model.AgentAction;
import model.PositionAgent;
import model.Transfert.EtatAgent;
import model.Transfert.EtatAgentFantome;

public class Fantome extends Agent {
    protected boolean canDie;
    protected int timer;

    public Fantome(PacmanGame g, PositionAgent p)
    {
        super(g, p);
        canDie = false;
        timer = -1;
    }

    public void revive()
    {
        super.revive();
        canDie = false;
        timer = -1;
    }

    public AgentAction getAction()
    {
        return new AgentAction(AgentAction.STOP);
    }

    public void takeTurn()
    {
        if(timer >= 0)
        {
            timer--;
            if(timer == 0)
            {
                canDie = false;
            }
        }
        if(!alive)
        {
            revive();
        }
    }

    public void makeVulnerable(int timer)
    {
        canDie = true;
        this.timer = timer;
    }

    public boolean canKillThis(Agent a)
    {
        if(a instanceof Fantome) //Un fantome ne peux pas tuer un fantome
        {
            return false;
        }
        return !canDie; //On ne peux pas tuer si on est vulnérable
    }

    public boolean canBeKilledBy(Agent a)
    {
        if(a instanceof Fantome) //Un fantome ne peux pas tuer un fantome
        {
            return false;
        }
        return canDie; //On est vulnérable donc on peux mourir
    }

    public void kill(Agent a)
    {
        super.kill(a);
    }

    public boolean isFrightened()
    {
        return canDie;
    }

    public void setStrategie(ListeStrategie strategie)
    {
        /*try
        {
            this.strategie = StrategieFractory.createStrategie(this, strategie);
        } catch (Exception e)
        {
            e.printStackTrace();
        }*/
    } 
}

