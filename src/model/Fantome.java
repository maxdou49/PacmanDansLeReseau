/*
 * Un fantome
 */
package model;
import model.EtatFantome.EtatFantome;
import model.EtatFantome.EtatFantomeChase;
import model.EtatFantome.EtatFantomeFright;
import model.Iterateur.IterateurAgent;
import model.Strategie.ListeStrategie;

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

    protected boolean isMoveLegal(AgentAction action)
    {
        PositionAgent newpos = new PositionAgent(pos.getX() + action.get_vx(), pos.getY() + action.get_vy(), pos.getX() + action.get_direction());
        return !game.getMaze().isWall(newpos.getX(), newpos.getY());
    }

    protected void moveAgent(AgentAction action)
    {
        pos.setX(pos.getX() + action.get_vx());
        pos.setY(pos.getY() + action.get_vy());
        pos.setDir(action.get_direction());
        Pacman pacman = null;
        IterateurAgent iter = game.pacmanIsHereIter(pos);
        if(iter.hasNext())
        {
            pacman = (Pacman)iter.next();
            if(pacman != null)
            {
                if(canBeKilled())
                {
                    pacman.killGhost(this);
                }
                else
                {
                    pacman.killMe();
                }
            }
        }
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
        if(alive)
        {
            AgentAction action = etat.takeTurn();
        
            //On veux pas de mur
            if(isMoveLegal(action))
            {
                moveAgent(action);
            }
        }
    }

    public void makeVulnerable(int timer)
    {
        canDie = true;
        this.timer = timer;
        etat = new EtatFantomeFright(this);
    }

    public boolean canKillPacman()
    {
        return !canDie;
    }

    public boolean canKillGhost()
    {
        return false;
    }

    public boolean canBeKilled()
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
}
