package client.view.menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import client.controller.MenuViewControlleur;

public class MenuMain extends MenuPanel {
    JButton multiButton;
    JButton soloButton;

    public MenuMain(MenuViewControlleur controlleur)
    {
        super(controlleur);
        
        try {
            background = new ImageIcon("src/icons/imgAcc.jpg").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton multiButton = new JButton("Multiplayer");
        JButton soloButton = new JButton("Solo");
        JButton btnVide1 = new JButton(); btnVide1.setVisible(false);
        JButton btnVide2 = new JButton(); btnVide2.setVisible(false);
        
        custumizeButton(soloButton);
        custumizeButton(multiButton);
        
        this.setLayout(new GridLayout(3, 2));
        this.add(multiButton);
        this.add(soloButton);
        this.add(btnVide1);
        this.add(btnVide2);

        soloButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlleur.startGame();
                controlleur.hideCurrent();
            }

        });

        multiButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlleur.multiplayer();
                controlleur.hideCurrent();
            }

        });
    }

    public boolean onKeyPress(KeyEvent event)
    {
        return false;
    }
}
