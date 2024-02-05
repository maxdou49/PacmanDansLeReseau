package View;
import java.awt.*;

import javax.swing.*;

import controller.AbstractController;
import model.Game;
import model.Observable;
import model.Observer;

public class ViewSimpleGame extends JFrame implements Observer {
    private JLabel label;

    public ViewSimpleGame(AbstractController controlleur)
    {
        setTitle("Game");
        setSize(new Dimension(700,700));
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width/2;
        int dy = centerPoint.y - windowSize.height/2 - 350;
        setLocation(dx, dy);
        setVisible(true);
        label = new JLabel("Text", JLabel.CENTER);
        add(label);
        label.setLocation(dx, dy);
        controlleur.getGame().addObserver(this);
    }

    public void update(Observable o)
    {
        if(o instanceof Game)
        {
            Game g = (Game)o;
            setText("Turn : " + g.getTurn());
        }
    }

    public void setText(String text)
    {
        label.setText(text);
    }

}
