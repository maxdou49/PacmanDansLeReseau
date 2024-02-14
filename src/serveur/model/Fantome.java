/*
 * Un fantome
 */
package serveur.model;
import model.AgentAction;
import model.PositionAgent;
import serveur.model.Strategie.ListeStrategie;
import model.Transfert.EtatAgent;
import model.Transfert.EtatAgentFantome;
import serveur.model.EtatFantome.EtatFantome;
import serveur.model.EtatFantome.EtatFantomeChase;
import serveur.model.EtatFantome.EtatFantomeFright;

public class Fantome extends Agent {
    protected boolean canDie;
    protected int timer;
    protected EtatFantome etat;
    protected ListeStrategie chaseStrategie;
    protected ListeStrategie frightStrategie;

    public Fantome(PacmanGame g, PositionAgent p)
    {
        super(g, p);
        canDie = false;
        timer = -1;
        setChaseStrategie(ListeStrategie.RANDOM_INTERSECTION);
        setFrightStrategie(ListeStrategie.RANDOM_INTERSECTION);
        etat = new EtatFantomeChase(this);
    }

    public void revive()
    {
        super.revive();
        canDie = false;
        timer = -1;
        etat = new EtatFantomeChase(this);
    }

    public AgentAction getAction()
    {
        return etat.takeTurn();
    }

    public void takeTurn()
    {
        if(timer >= 0)
        {
            timer--;
            if(timer == 0)
            {
                canDie = false;
                etat = new EtatFantomeChase(this);
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
        etat = new EtatFantomeFright(this);
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

    public ListeStrategie getChaseStrategie()
    {
        return chaseStrategie;
    }

    public ListeStrategie getFrightStrategie()
    {
        return frightStrategie;
    }

    public void setChaseStrategie(ListeStrategie strategie)
    {
        chaseStrategie = strategie;
        if(etat != null)
        {
            etat.setStrategie();
        }
    }

    public void setFrightStrategie(ListeStrategie strategie)
    {
        frightStrategie = strategie;
        if(etat != null)
        {
            etat.setStrategie();
        }
    }

    public EtatAgent getEtatAgent()
    {
        return new EtatAgentFantome(pos, canDie);
    }

    public void setFromEtatAgent(EtatAgent e)
    {
        if(e instanceof EtatAgentFantome)
        {
            EtatAgentFantome f = (EtatAgentFantome)e;
            pos = f.getPos();
            canDie = f.getFrightened();
        }
    }

    @Override
    public String toString() {
        return "Fantome";
    }
}
