/*
 * Pacman
 */

package model;

import model.Iterateur.IterateurAgent;
import model.Strategie.ListeStrategie;
import model.Strategie.StrategieAgent;
import model.Strategie.StrategieFractory;

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

    protected void moveAgent(AgentAction action)
    {
        pos.setX(pos.getX() + action.get_vx());
        pos.setY(pos.getY() + action.get_vy());
        pos.setDir(action.get_direction());

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
        //Kill(or be killed by) ghosts
        IterateurAgent iter = game.ghostIsHereIter(pos);
        Agent ghost = null;
        while(iter.hasNext())
        {
            ghost = iter.next();
            if(ghost.canBeKilled())
            {
                killGhost(ghost);
            }
            else
            {
                killMe();
            }
        }
    }

    public void takeTurn()
    {
        if(alive)
        {
            AgentAction action = strategie.getAction();

            if(!game.areGhostVulnerable())
            {
                breakChain();
            }
        
            //On veux pas de mur
            if(isMoveLegal(action))
            {
                moveAgent(action);
            }
        }
    }

    public void breakChain()
    {
        chain = 0;
    }

    public void killGhost(Agent a)
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

}