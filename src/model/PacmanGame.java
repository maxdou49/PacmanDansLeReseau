/*
 * Le jeu Pacman
 */

package model;
import java.util.ArrayList;

import model.Iterateur.IterateurAgent;
import model.Iterateur.IterateurAgentBase;
import model.Iterateur.IterateurAgentPosition;
import model.Iterateur.IterateurFantome;
import model.Iterateur.IterateurPacman;
import model.Iterateur.IterateurPacmanKiller;
import model.Random.BasicRandom;
import model.Random.RandomGenerator;
import View.PanelPacmanGame;
import model.Strategie.ListeStrategie;
import model.Strategie.StrategieAgent;

public class PacmanGame extends Game {

    private Maze maze;
    private PanelPacmanGame mazePanel;
    private String mazeFile;
    protected ArrayList<Agent> agents;
    protected RandomGenerator rand;
    protected KeyboadManager keyboard;
    private int level;
    private int lives;
    private int score;
    private ListeStrategie pacmanStrategie;
    private ListeStrategie fantomeStrategie;

    private int NB_LIVES = 5;

    public PacmanGame(String mazeFile)
    {
        super();
        this.mazeFile = mazeFile;
        agents = new ArrayList<Agent>();
        loadMaze();
        mazePanel = new PanelPacmanGame(maze);
        level = 1;
        lives = NB_LIVES;
        pacmanStrategie = ListeStrategie.NONE;
        fantomeStrategie = ListeStrategie.NONE;
    }

    public String getMazeFile()
    {
        return mazeFile;
    }

    private void loadMaze()
    {
        try{
            maze = new Maze(mazeFile);
            System.out.println(mazeFile);
        }
        catch(Exception e)
        {
            System.out.println("Error");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void changeMaze(String mazeFile)
    {
        this.mazeFile = mazeFile;
        initializeGame();
    }

    protected void initializeGame()
    {
        loadMaze();
        mazePanel.setMaze(maze);
        level = 1;
        score = 0;
        lives = NB_LIVES;

        //Charger agents
        agents.clear();
        AgentFactory f = new AgentFactory();
        for(PositionAgent p: maze.getPacman_start())
        {
            Pacman a = f.createPacman(this, p);
            a.setStrategie(pacmanStrategie);
            a.setPlayer(0);
            agents.add(a);
        }
        for(PositionAgent p: maze.getGhosts_start())
        {
            Fantome a = f.createFantome(this, p);
            a.setChaseStrategie(fantomeStrategie);
            a.setPlayer(1);
            agents.add(a);
        }

        //Diverses initialisation
        rand = new BasicRandom(System.nanoTime());
    }

    protected void nextLevel()
    {
        loadMaze();
        mazePanel.setMaze(maze);
        level += 1;
        System.out.println("Niveau "+Integer.toString(level));
        for(Agent a: agents)
        {
            a.revive();
        }
    }

    protected boolean gameContinue()
    {
        return true;
    }

    protected void takeTurn()
    {
        //On gère la logique
        for(Agent a:agents)
        {
            a.takeTurn();
        }
        
        updateObservers();

        if(!isAnyFoodLeft())
        {
            nextLevel();
        }

        if(!pacmanAlive() && lives > 0)
        {
            lives--;
            System.out.println("Vous avez perdu une vie. Restant "+Integer.toString(lives));
            for(Agent a:agents)
            {
                a.revive();
            }
        }
    }

    protected boolean gameEnd()
    {
        return (lives <= 0) || level > 255;
    }

    protected void gameOver()
    {
        System.out.println("Jeu terminé");
        if(lives > 0)
        {
            System.out.println("Vous avez gagné");
        }
        else
        {
            System.out.println("Vous avez perdu");
        }

        System.out.println("Score: "+Integer.toString(getScore()));

        updateObservers();
    }

    public Maze getMaze()
    {
        return maze;
    }

    public PanelPacmanGame getMazePanel()
    {
        return mazePanel;
    }

    public RandomGenerator getRandom()
    {
        return rand;
    }

    public ArrayList<Agent> getAgents()
    {
        return agents;
    }

    public KeyboadManager getKeyboard()
    {
        return keyboard;
    }

    public void setKeyboard(KeyboadManager keyboard)
    {
        this.keyboard = keyboard;
    }

    public IterateurAgent pacmanKillerIter()
    {
        return new IterateurPacmanKiller(new IterateurAgentBase(agents));
    }

    public boolean pacmanIsHere(PositionAgent p)
    {
        for(Agent a: agents)
        {
            if(a.getPos().equals(p) && a instanceof Pacman)
            {
                return true;
            }
        }
        return false;
    }

    public IterateurAgent pacmanIsHereIter(PositionAgent p)
    {
        return new IterateurAgentPosition(getPacmanIter(), p);
    }

    public IterateurAgent ghostIsHereIter(PositionAgent p)
    {
        return new IterateurAgentPosition(getGhostIter(), p);
    }

    public IterateurAgent getPacmanIter()
    {
        return new IterateurPacman(new IterateurAgentBase(agents));
    }

    public IterateurAgent getGhostIter()
    {
        return new IterateurFantome(new IterateurAgentBase(agents));
    }

    public void makeGhostVulnerable()
    {
        for(Agent a: agents)
        {
            if(a instanceof Fantome)
            {
                Fantome f = (Fantome)a;
                f.makeVulnerable(20);
            }
        }
    }

    public boolean areGhostVulnerable()
    {
        for(Agent a: agents)
        {
            if(a instanceof Fantome)
            {
                Fantome f = (Fantome)a;
                if(f.canBeKilled())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pacmanAlive()
    {
        for(Agent a: agents)
        {
            if(a instanceof Pacman && a.isAlive())
            {
                return true;
            }
        }
        return false;
    }

    public void awardScore(int add)
    {
        if((score / 10000) < (score + add) / 10000) //On gagne une vie tous les 10 000 pts
        {
            lives++;
        }
        score += add;
    }

    public int getScore()
    {
        return score;
    }

    public int getLives()
    {
        return lives;
    }

    public Pacman getPacman()
    {
        for(Agent a: agents)
        {
            if(a instanceof Pacman)
            {
                return (Pacman)a;
            }
        }
        return null;
    }

    public boolean isAnyFoodLeft()
    {
        return maze.countFood() > 0;
    }

    public int getLevel()
    {
        return level;
    }

    public String getTexte()
    {
        return String.format("Niveau: %d <br/>Score: %d \tVies: %d", level, getScore(), getLives());
    }

    public void setPacmanStrategie(ListeStrategie strategie)
    {
        pacmanStrategie = strategie;
        for(Agent a:agents)
        {
            if(a instanceof Pacman)
            {
                Pacman p = (Pacman)a;
                p.setStrategie(strategie);
            }
        }
    }

    public void setFantomeStrategie(ListeStrategie strategie)
    {
        fantomeStrategie = strategie;
        for(Agent a:agents)
        {
            if(a instanceof Fantome)
            {
                Fantome p = (Fantome)a;
                p.setChaseStrategie(strategie);
            }
        }
    }

    public void setPacmanStrategieParam(int param) {
        StrategieAgent.setParamPacman(param);
    }

    public void setFantomeStrategieParam(int param) {
        StrategieAgent.setParamFantome(param);
    }
}
