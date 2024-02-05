/*
 * Un agent sur la carte
 */

package model;

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

    protected abstract boolean isMoveLegal(AgentAction action);
    protected abstract void moveAgent(AgentAction action);

    public abstract void takeTurn();

    public boolean canKillPacman()
    {
        return false;
    }

    public boolean canKillGhost()
    {
        return false;
    }

    public boolean canBeKilled()
    {
        return true;
    }

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
