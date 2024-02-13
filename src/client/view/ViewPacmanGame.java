package client.view;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import client.model.Agent;
import client.model.PacmanGame;
import client.controller.ControllerPacmanGameClient;
import model.KeyboadManager;
import model.Observable;
import model.Observer;
import model.PositionAgent;
import client.model.Fantome;
import client.model.Pacman;

public class ViewPacmanGame extends JFrame implements Observer {
    protected KeyboadManager keyboard;
    private PanelPacmanGame mazePanel;


    public ViewPacmanGame(ControllerPacmanGameClient controlleur)
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
        PacmanGame p = (PacmanGame)controlleur.getGame();
        mazePanel = new PanelPacmanGame(p.getMaze());
        controlleur.getGame().addObserver(this);
        if(controlleur.getGame() instanceof PacmanGame) //Il faut que ce soit correctement initialiser...
        {
            add(mazePanel);
        }
        
        keyboard = new KeyboadManager();
        addKeyListener(keyboard);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update(Observable o)
    {
        if(o instanceof PacmanGame)
        {
            PacmanGame p = (PacmanGame)o;
            PanelPacmanGame pa = new PanelPacmanGame(p.getMaze());
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
            mazePanel.repaint();
        }
    }

    public KeyboadManager getKeyboard()
    {
        return keyboard;
    }
}