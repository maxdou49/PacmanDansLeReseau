package client.view.menu;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.controller.MenuControlleur;

public class MenuView extends JFrame {
    MenuPanel panel;
    MenuControlleur controlleur;

    public MenuView(MenuControlleur controlleur)
    {
        this.controlleur = controlleur;
        
        setTitle("PacmanGame");
        setLocationRelativeTo(null);

        JMenu menu = new JMenu("Configuration");
        JMenuBar mb = new JMenuBar();
        JMenuItem chgServ = new JMenuItem("Changer de domaine");
        chgServ.addActionListener((ActionEvent e) -> {
            String newServName = JOptionPane.showInputDialog(null, "Entrez une le nouveau domaine :");
            controlleur.setServerName(newServName);
        });

        JMenuItem exitApp = new JMenuItem("Quitter");
        exitApp.setMnemonic(KeyEvent.VK_Q);
        exitApp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        exitApp.setToolTipText("Exit application");
        
        exitApp.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        menu.add(chgServ);
        menu.add(exitApp);
        mb.add(menu);
        
        this.setJMenuBar(mb);
        this.setView(new MenuMain(controlleur));
        this.setSize(new Dimension(450, 280));
        this.setLocation(780, 325);
        this.addKeyListener(new KeyListener() { //C'est un peu bugué la gestion des touches dans un sous-panel
            public void keyPressed(KeyEvent e) {
                panel.onKeyPress(e);
            }
        
            public void keyReleased(KeyEvent e) {
                return;
            }
        
            public void keyTyped(KeyEvent e) {
                return;
            }
        });
    }

    public void setView(MenuPanel panel)
    {
        System.out.println("Ouverture de " + panel.toString());
        if(panel != null)
        {
            remove(panel);
        }
        
        setContentPane(panel);
        this.panel = panel;

        repaint();
    }

    @Override
    public void setVisible(boolean b)
    {
        super.setVisible(b);
        if(panel != null)
        {
            panel.setVisible(b);
        }

        repaint();
    }
}
