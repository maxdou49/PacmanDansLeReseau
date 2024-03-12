package client.view;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.GameControlleur;
import model.Game;
import model.Observable;
import model.Observer;

public class ViewCommand extends JFrame implements Observer {
    protected JLabel text;
    protected JLabel etat;
    protected JButton restart;
    protected JButton play;
    protected JButton step;
    protected JButton pause;
    protected GameControlleur controller;

    public ViewCommand(GameControlleur controlleur)
    {
        super();
        this.controller = controlleur;
        setSize(600, 200);
        setLayout(new GridLayout(3, 1));
        JPanel pbutton = new JPanel();
        JPanel pmisc = new JPanel();
        add(pbutton);
        add(pmisc);

        pbutton.setLayout(new GridLayout(1, 4));
        pmisc.setLayout(new GridLayout(1,2));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JSlider speed = new JSlider(1, 20, 1);
        speed.setMajorTickSpacing(2);
        speed.setPaintTicks(true);
        speed.setPaintLabels(true);
        speed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e)
            {
                controlleur.setSpeed(((JSlider)e.getSource()).getValue());
            }
        });
        controlleur.setSpeed(2);
        text = new JLabel("Turn : ?");
        //etat = new JLabel("This text should not appear.");
        pmisc.add(speed);
        pmisc.add(text);
        //pmisc.add(etat);
        
        //On fait les boutons
        restart = new JButton(new ImageIcon("assets/icon_restart.png"));
        restart.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                controlleur.restart();
            }
        });
        pbutton.add(restart);
        play = new JButton(new ImageIcon("assets/icon_run.png"));
        play.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                controlleur.play();
            }
        });
        pbutton.add(play);
        step = new JButton(new ImageIcon("assets/icon_step.png"));
        step.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                controlleur.step();
            }
        });
        pbutton.add(step);
        pause = new JButton(new ImageIcon("assets/icon_pause.png"));
        pause.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                controlleur.pause();
            }
        });
        pbutton.add(pause);

        setVisible(true);
        controlleur.getGame().addObserver(this);
    }

    public void update(Observable o)
    {
        if(o != null && o instanceof Game)
        {
            Game g = (Game)o;
            text.setText("<html>Turn : " + g.getTurn() + " \t" + g.getTexte() + "</html>");
            //etat.setText(g.getTexte());
        }
    }
}
