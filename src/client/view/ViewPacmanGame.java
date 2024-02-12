package client.view;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import client.model.Agent;
import client.model.PacmanGame;
import controller.AbstractController;
import model.KeyboadManager;
import model.Observable;
import model.Observer;
import model.PositionAgent;
import client.model.Fantome;
import client.model.Pacman;

public class ViewPacmanGame extends JFrame implements Observer {
    protected KeyboadManager keyboard;

    public ViewPacmanGame(AbstractController controlleur)
    {
        setTitle("Pacman");
        setSize(new Dimension(700,700));
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width/2;
        int dy = centerPoint.y - windowSize.height/2 - 350;
        setLocation(dx, dy);
        setVisible(true);
        controlleur.getGame().addObserver(this);
        if(controlleur.getGame() instanceof PacmanGame) //Il faut que ce soit correctement initialiser...
        {
            PacmanGame p = (PacmanGame)controlleur.getGame();
            add(p.getMazePanel());
        }
        
        keyboard = new KeyboadManager();
        addKeyListener(keyboard);

    }

    public void update(Observable o)
    {
        if(o instanceof PacmanGame)
        {
            PacmanGame p = (PacmanGame)o;
            PanelPacmanGame pa = p.getMazePanel();
            //On met a jour les positions
            pa.getPacmans_pos();
            ArrayList<PositionAgent> pacman = new ArrayList<PositionAgent>();
            ArrayList<PositionAgent> fantomes = new ArrayList<PositionAgent>();
            ArrayList<Boolean> fright = new ArrayList<Boolean>();
            for(Agent a: p.getAgents())
            {
                if(a instanceof Pacman)
                {
                    pacman.add(a.getPos());
                }
                else
                {
                    Fantome f = (Fantome)a;
                    fantomes.add(a.getPos());
                    fright.add(f.isFrightened());
                }
            }
            pa.setPacmans_pos(pacman);
            pa.setGhosts_pos(fantomes);
            pa.setGhostsScarred(fright);
            p.getMazePanel().repaint();
        }
    }

    public KeyboadManager getKeyboard()
    {
        return keyboard;
    }
}