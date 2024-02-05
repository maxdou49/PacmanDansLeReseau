package View;

import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.ControleurPacmanGame;
import model.PacmanGame;
import model.Strategie.ListeStrategie;

public class ViewCommandPacman extends ViewCommand {
    JLabel mazeFile;

    public ViewCommandPacman(ControleurPacmanGame controlleur)
    {
        super(controlleur);
        setSize(getWidth(), (getHeight()*4)/2);

        JPanel panelStrat = new JPanel();
        panelStrat.setLayout(new GridLayout(4,2));
        add(panelStrat);
        //Choix layout
        mazeFile = new JLabel(((PacmanGame)controlleur.getGame()).getMazeFile());
        panelStrat.add(mazeFile);
        //On fait les boutons
        JButton changeLayout = new JButton("Changer layout");
        changeLayout.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                changeLayout();
            }
        });
        panelStrat.add(changeLayout);

        //Stratégies
        JLabel stratPacmanLabel = new JLabel("Strategie Pacman");
        panelStrat.add(stratPacmanLabel);
        JLabel stratFantomeLabel = new JLabel("Strategie Fantome");
        panelStrat.add(stratFantomeLabel);

        //Pacman
        JComboBox<ListeStrategie> stratPacman = new JComboBox<ListeStrategie>(ListeStrategie.values());
        panelStrat.add(stratPacman);
        stratPacman.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if(event.getSource() == stratPacman)
                {
                    controlleur.setStrategiePacman((ListeStrategie)stratPacman.getSelectedItem());
                }
            };
        });
        
        //Fantomes
        JComboBox<ListeStrategie> stratFantome = new JComboBox<ListeStrategie>(ListeStrategie.values());
        panelStrat.add(stratFantome);
        stratFantome.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if(event.getSource() == stratFantome)
                {
                    controlleur.setStrategieFantome((ListeStrategie)stratFantome.getSelectedItem());
                }
            };
        });

        //Paramètrage stratégie
        JSpinner paramPacman = new JSpinner();
        paramPacman.setValue(0);
        paramPacman.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controlleur.setStrategiePacmanParam(Integer.valueOf(paramPacman.getValue().toString()));
            }
        });
        panelStrat.add(paramPacman);

        JSpinner paramFantome = new JSpinner();
        paramFantome.setValue(0);
        paramFantome.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controlleur.setStrategieFantomeParam(Integer.valueOf(paramFantome.getValue().toString()));
            }
        });
        panelStrat.add(paramFantome);

    }

    private void changeLayout()
    {
        FileDialog fd = new FileDialog(this, "Choisir fichier layout", FileDialog.LOAD);
        fd.setDirectory("./");
        //fd.setFile("*.lay");
        fd.setVisible(true);
        String file = fd.getFile();
        if(file != null)
        {
            String path = fd.getDirectory() + fd.getFile();
            System.out.println("Selected:" + path);
            mazeFile.setText(file);
            ControleurPacmanGame cp = (ControleurPacmanGame)controller;
            cp.changeMaze(path);
        }
    }
}
