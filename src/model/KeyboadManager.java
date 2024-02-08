package model;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyboadManager implements KeyListener {
    public final static int NB_PLAYERS = 2;
    public final static int KEY_NONE = -1;
    public final static int KEY_UP = 0;
    public final static int KEY_DOWN = 1;
    public final static int KEY_LEFT = 2;
    public final static int KEY_RIGHT = 3;
    public final static int KEY_MAX = 4;

    private int lastKey[];
    private boolean keyPress[][];

    public KeyboadManager()
    {
        lastKey = new int[NB_PLAYERS];
        for(int i = 0; i < NB_PLAYERS; ++i)
        {
            lastKey[i] = KEY_NONE;
        }
        keyPress = new boolean[NB_PLAYERS][KEY_MAX];
        for(int i = 0; i < NB_PLAYERS; ++i)
        {
            for(int j = 0; j < KEY_MAX; ++j)
            {
                keyPress[i][j] = false;
            }
        }
    }

    protected void updateKey(int keyCode, boolean val)
    {
        int key = -1;
        int player = -1;
        switch(keyCode)
        {
            case KeyEvent.VK_UP:
                key = KEY_UP;
                player = 0;
                break;
            case KeyEvent.VK_DOWN:
                key = KEY_DOWN;
                player = 0;
                break;
            case KeyEvent.VK_LEFT:
                key = KEY_LEFT;
                player = 0;
                break;
            case KeyEvent.VK_RIGHT:
                key = KEY_RIGHT;
                player = 0;
                break;
            case KeyEvent.VK_Z:
                key = KEY_UP;
                player = 1;
                break;
            case KeyEvent.VK_S:
                key = KEY_DOWN;
                player = 1;
                break;
            case KeyEvent.VK_Q:
                key = KEY_LEFT;
                player = 1;
                break;
            case KeyEvent.VK_D:
                key = KEY_RIGHT;
                player = 1;
                break;
            default:
                key = -1;
                break;
        }
        if(key >= 0 && key < KEY_MAX)
        {
            keyPress[player][key] = val;
            if(val)
            {
                lastKey[player] = key;
            }
        }
    }

    //Vérifie si une touche a été appuyé
    public boolean getKeyPress(int player, int key, boolean isLast)
    {
        if(key >= 0 && key < KEY_MAX)
        {
            return (!isLast || lastKey[player] == key) && keyPress[player][key];
        }
        return false;
    }

    //Regarde la dernière touche appuyé
    public int getLastKey(int player, boolean isPressed)
    {
        //System.out.println(Integer.toString(lastKey));
        if(lastKey[player] >= 0 && lastKey[player] < KEY_MAX)
        {
            if(!isPressed || keyPress[player][lastKey[player]])
            {
                return lastKey[player];
            }
        }
        return KEY_NONE;
    }

    public void keyPressed(KeyEvent e) {
        updateKey(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {
        updateKey(e.getKeyCode(), false);
    }

    public void keyTyped(KeyEvent e) {
        return;
    }
}
