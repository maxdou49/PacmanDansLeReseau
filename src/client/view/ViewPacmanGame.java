package client.view;
import java.awt.*;
import javax.swing.*;

import client.model.PacmanGame;
import client.controller.ControllerPacmanGameClient;
import model.KeyboadManager;
import model.Maze;
import model.Observable;
import model.Observer;

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
            Maze maze = ((PacmanGame)o).getMaze();
            mazePanel.setMaze((maze));
            mazePanel.setPacmans_pos(maze.getPacman_start());
            mazePanel.setGhosts_pos(maze.getGhosts_start());
            mazePanel.repaint();
        }
    }

    public KeyboadManager getKeyboard()
    {
        return keyboard;
    }
}