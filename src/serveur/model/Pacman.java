/*
 * Pacman
 */

package serveur.model;

import model.AgentAction;
import model.PositionAgent;
import model.Transfert.EtatAgent;
import model.Transfert.EtatAgentPacman;
import serveur.model.Strategie.ListeStrategie;
import serveur.model.Strategie.StrategieAgent;
import serveur.model.Strategie.StrategieFractory;

public class Pacman extends Agent
{
    protected StrategieAgent strategie;
    public int chain;

    
    public Pacman(PacmanGame g, PositionAgent p)
    {
        super(g, p);
        chain = 0;
    }

    protected boolean isMoveLegal(AgentAction action)
    {
        PositionAgent newpos = new PositionAgent(pos.getX() + action.get_vx(), pos.getY() + action.get_vy(), pos.getX() + action.get_direction());
        return !game.getMaze().isWall(newpos.getX(), newpos.getY());
    }

    public void setStrategie(ListeStrategie strategie)
    {
        try
        {
            this.strategie = StrategieFractory.createStrategie(this, strategie);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //On va dire que par défaut Pacman peut tout tuer vu que cela dépend du fantome.
    public boolean canKillThis(Agent a)
    {
        return (a instanceof Fantome);
    }

    //Cela dépend du fantome si Pacman peut mourir
    public boolean canBeKilledBy(Agent a)
    {
        return (a instanceof Fantome);
    }

    public AgentAction getAction()
    {
        return strategie.getAction();
    }

    public void takeTurn()
    {
        if(isAlive())
        {
            //Casser la chaine si aucun fantome n'est vulnérable
            if(!game.areGhostVulnerable())
            {
                breakChain();
            }
            //On gère la collecte de nourriture
            if(game.getMaze().isFood(pos.getX(), pos.getY()))
            {
                game.getMaze().setFood(pos.getX(), pos.getY(), false);
                game.awardScore(10);
            }
            if(game.getMaze().isCapsule(pos.getX(), pos.getY()))
            {
                game.getMaze().setCapsule(pos.getX(), pos.getY(), false);
                game.makeGhostVulnerable();
                game.awardScore(50);
            }
        }
    }

    public void breakChain()
    {
        chain = 0;
    }

    //On gagne des points si pacman tue un fantome
    public void kill(Agent a)
    {
        if(!a.isAlive())
        {
            return;
        }
        game.awardScore(200 << chain); //Comment faire 200^n sans passer par pow
        chain += 1;
        a.killMe();
    }

    public StrategieAgent getStrategie()
    {
        return strategie;
    }

    public EtatAgent getEtatAgent()
    {
        return new EtatAgentPacman(pos);
    }

    public void setFromEtatAgent(EtatAgent e)
    {
        if(e instanceof EtatAgentPacman)
        {
            EtatAgentPacman p = (EtatAgentPacman)e;
            pos = p.getPos();
        }
    }

    @Override
    public String toString() {
        return "Pacman";
    }

}