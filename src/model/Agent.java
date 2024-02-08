/*
 * Un agent sur la carte
 */

package model;

import model.Iterateur.IterateurAgent;
import model.Iterateur.IterateurAgentPosition;

public abstract class Agent {
    protected PositionAgent pos;
    protected PacmanGame game;
    protected boolean alive;
    protected PositionAgent spawn;
    protected int player;

    public Agent(PacmanGame g, PositionAgent p)
    {
        spawn = new PositionAgent(p.getX(), p.getY(), p.getDir());
        pos = new PositionAgent(p.getX(), p.getY(), p.getDir());
        game = g;
        alive = true;
        player = 0;
    }

    //Faire réaparaître un agent
    public void revive()
    {
        alive = true;
        pos = new PositionAgent(spawn.getX(), spawn.getY(), spawn.getDir());;
    }

    public PositionAgent getPos()
    {
        return pos;
    }

    public PacmanGame getGame()
    {
        return game;
    }

    //Vérifie si un déplacement est valide
    protected boolean isMoveLegal(AgentAction action)
    {
        PositionAgent newpos = new PositionAgent(pos.getX() + action.get_vx(), pos.getY() + action.get_vy(), pos.getX() + action.get_direction());
        return !game.getMaze().isWall(newpos.getX(), newpos.getY());
    }

    //Gérer le déplacement
    protected void moveAgent(AgentAction action)
    {
        if(isMoveLegal(action))
        {
            pos.setX(pos.getX() + action.get_vx());
            pos.setY(pos.getY() + action.get_vy());
            pos.setDir(action.get_direction());
        }
    }
    
    //Gère les morts
    public void manageKill()
    {
        //Gerer les morts
        Agent a;
        IterateurAgent iter = new IterateurAgentPosition(game.getAgentIter(), pos);
        while(iter.hasNext())
        {
            a = iter.next();
            if(a != null)
            {
                //Si on peux tuer un agent qui peut mourir de nous, on le tue
                if(canKillThis(a) && a.canBeKilledBy(this))
                {
                    kill(a);
                }
                //S'il peut nous tuer et qu'on peut en mourir, il nous tue
                if(a.canKillThis(this) && canBeKilledBy(a))
                {
                    a.kill(this);
                }
            }
        }
    }

    //Retourner l'action décidé par la stratégie
    public abstract AgentAction getAction();

    //Gère la logique dépendant de l'agent
    public abstract void takeTurn();

    //Teste si on peut tuer un agent
    public abstract boolean canKillThis(Agent a);

    //Teste si on peut mourir de cet agent
    public abstract boolean canBeKilledBy(Agent a);
    
    //Tuer un agent
    public void kill(Agent a)
    {
        a.killMe();
    }

    //Mourir
    public void killMe()
    {
        pos.setX(-1);
        pos.setY(-1);
        alive = false;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public int getPlayer()
    {
        return player;
    }

    public void setPlayer(int player)
    {
        this.player = player;
    }

}
