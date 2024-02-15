package client.view;

import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import client.controller.ControllerPacmanGameClient;

public class ViewCommandPacman extends ViewCommand {
    JLabel mazeFile;

    public ViewCommandPacman(ControllerPacmanGameClient controlleur)
    {
        super(controlleur);
        setSize(getWidth(), (getHeight()*4)/2);

        JPanel panelStrat = new JPanel();
        panelStrat.setLayout(new GridLayout(4,2));
        add(panelStrat);
        //Choix layout
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
        }
    }
}
