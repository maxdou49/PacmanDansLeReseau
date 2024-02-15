package client.view;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import client.model.PacmanGame;
import client.controller.ControllerPacmanGameClient;
import model.KeyboadManager;
import model.Maze;
import model.Observable;
import model.Observer;
import model.PositionAgent;
import model.Transfert.EtatGame;

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
        
    }

    public KeyboadManager getKeyboard()
    {
        return keyboard;
    }

    public void rafrachier(EtatGame etat) {
        ArrayList<PositionAgent> pacmans = new ArrayList<PositionAgent>();
        for(int i = 0; i < etat.getPacmans().size(); i++)
        {
            pacmans.add(etat.getPacmans().get(i).getPos());
        }
        mazePanel.setPacmans_pos(pacmans);
        ArrayList<PositionAgent> fantomes = new ArrayList<PositionAgent>();
        ArrayList<Boolean> fright = new ArrayList<Boolean>();
        for(int i = 0; i < etat.getFantomes().size(); i++)
        {
            fantomes.add(etat.getFantomes().get(i).getPos());
            fright.add(etat.getFantomes().get(i).getFrightened());
        }
        mazePanel.setGhosts_pos(fantomes);
        mazePanel.setGhostsScarred(fright);

        Maze maze = etat.getMaze();
        mazePanel.setMaze(maze);
        //On rafraichit ici histoire que ce soit plus propre
        mazePanel.repaint();
    }
}