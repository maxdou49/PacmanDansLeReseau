package client.view.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.controller.MenuControlleur;

public abstract class MenuPanel extends JPanel {
    Image background;
    MenuControlleur controlleur;

    public MenuPanel(MenuControlleur controlleur)
    {
        this.controlleur = controlleur;
    }

    public boolean onKeyPress(KeyEvent e)
    {
        return false;
    }

    void custumizeButton(JButton button)
    {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 26));
        new Timer().schedule(new TimerTask() {
            boolean isOrange = true;
            @Override
            public void run() {
                button.setBackground(Color.BLACK);
                button.setForeground((isOrange)  ? Color.WHITE : Color.ORANGE);
                isOrange = !isOrange;
            }
        }, 0, 500);
    }

    void custumizeLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.PLAIN, 26));
        label.setForeground(Color.BLUE);
        label.setOpaque(false);
    }

    void custumizeTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 26));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setForeground(Color.BLUE);
        textField.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
