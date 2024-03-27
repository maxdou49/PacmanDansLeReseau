/*
 * Le jeu Pacman
 */

package serveur.model;
import java.util.ArrayList;

import model.AgentAction;
import model.Game;
import model.Maze;
import model.PositionAgent;
import serveur.controller.ControllerPacmanGameServeur;
import serveur.model.Iterateur.IterateurAgent;
import serveur.model.Iterateur.IterateurAgentBase;
import model.Random.BasicRandom;
import model.Random.RandomGenerator;
import model.Transfert.EtatAgentFantome;
import model.Transfert.EtatAgentPacman;
import model.Transfert.EtatGame;
import serveur.model.Strategie.ListeStrategie;
import serveur.model.Strategie.StrategieAgent;

public class PacmanGame extends Game {

    private Maze maze;
    private String mazeFile;
    protected ArrayList<Agent> agents;
    protected RandomGenerator rand;
    private int level;
    private int lives;
    private int score;
    private ListeStrategie pacmanStrategie;
    private ListeStrategie fantomeStrategie;
    private boolean endless;
    private boolean ended;

    private int NB_LIVES = 5;
    private int MAX_LEVEL = 255;

    private ControllerPacmanGameServeur controlleur;

    public PacmanGame(String mazeFile, ControllerPacmanGameServeur controller)
    {
        super();
        this.endless = false;
        this.ended = false;
        this.mazeFile = mazeFile;
        agents = new ArrayList<Agent>();
        loadMaze();
        level = 1;
        lives = NB_LIVES;
        pacmanStrategie = ListeStrategie.NONE;
        fantomeStrategie = ListeStrategie.NONE;
        this.controlleur = controller;
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

    //Initialiser une partie
    protected void initializeGame()
    {
        loadMaze();
        level = 1;
        score = 0;
        lives = NB_LIVES;
        ended = false;

        //Charger agents
        agents.clear();
        AgentFactory f = new AgentFactory();
        int player = 0;
        for(PositionAgent p: maze.getPacman_start())
        {
            Pacman a = f.createPacman(this, p);
            a.setStrategie(pacmanStrategie);
            a.setPlayer(player);
            agents.add(a);
            player++;
        }
        for(PositionAgent p: maze.getGhosts_start())
        {
            Fantome a = f.createFantome(this, p);
            a.setChaseStrategie(fantomeStrategie);
            a.setPlayer(-1);
            agents.add(a);
        }

        //Diverses initialisation
        rand = new BasicRandom(System.nanoTime());
    }

    protected void nextLevel()
    {
        if(level < MAX_LEVEL)
        {
            loadMaze();
            level += 1;
            System.out.println("Niveau "+Integer.toString(level));
            for(Agent a: agents)
            {
                a.revive();
            }
        }
        else
        {
            ended = true;
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
            if(a.isAlive()) //On ne bouge pas si on est mort(sinon ça plante)
            {
                AgentAction action = a.getAction();
                //System.out.println(a + " " + action);
                a.moveAgent(action);
            }
            a.takeTurn();
            a.manageKill();
        }
        
        //updateObservers();

        if(!isAnyFoodLeft())
        {
            if(endless)
                nextLevel();
            else
            {
                ended = true;
                level = 2; //Histoire de simplifier la condition de victoire
            }
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

        controlleur.envoyerEtat(getEtat());
    }

    public boolean hasWon()
    {
        return ended;
    }

    protected boolean gameEnd()
    {
        return (lives <= 0) || ended;
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
        controlleur.terminerPartie();
    }

    public Maze getMaze()
    {
        return maze;
    }

    public RandomGenerator getRandom()
    {
        return rand;
    }

    public ArrayList<Agent> getAgents()
    {
        return agents;
    }

    public IterateurAgent getAgentIter()
    {
        return new IterateurAgentBase(agents);
    }

    //Regarde si il y a un pacman a une position donné
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

    //Rend les fantomes vulnérable
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

    //Vérifie si au moins un fantôme est vulnérable
    public boolean areGhostVulnerable()
    {
        for(Agent a: agents)
        {
            if(a instanceof Fantome)
            {
                Fantome f = (Fantome)a;
                if(f.isFrightened())
                {
                    return true;
                }
            }
        }
        return false;
    }

    //Vérifie si au moins un pacman est en vie
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

    //Changer la stratégie de Pacman
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

    //Changer la stratégie des fantomes
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

    //Etat
    public EtatGame getEtat()
    {
        EtatGame etat = new EtatGame(score, lives, level, turn, stepTime, maze);
        for(Agent a:agents)
        {
            if(a instanceof Pacman)
                etat.addPacman((EtatAgentPacman)a.getEtatAgent());
            else if(a instanceof Fantome)
                etat.addFantome((EtatAgentFantome)a.getEtatAgent());
        }
        return etat;
    }

    public void setFromEtat(EtatGame etat)
    {
        this.score = etat.getScore();
        this.lives = etat.getLives();
        this.level = etat.getLevel();
        this.turn = etat.getTurn();

        ArrayList<EtatAgentFantome> fantomes = etat.getFantomes();
        ArrayList<EtatAgentPacman> pacmans = etat.getPacmans();
        int fantomesIdx = 0;
        int pacmansIdx = 0;
        for(Agent a: agents)
        {
            if(a instanceof Pacman)
            {
                Pacman p = (Pacman)a;
                if(pacmansIdx < pacmans.size())
                {
                    p.setFromEtatAgent(pacmans.get(pacmansIdx++));
                }
            } else if(a instanceof Fantome)
            {
                Fantome f = (Fantome)a;
                if(fantomesIdx < fantomes.size())
                {
                    f.setFromEtatAgent(fantomes.get(fantomesIdx++));
                }
            }
        }
    }

    public ControllerPacmanGameServeur getController()
    {
        return controlleur;
    }

    public int getNbPlayers()
    {
        return maze.getPacman_start().size();
    }

    public boolean getEndless() {
        return endless;
    }
}
