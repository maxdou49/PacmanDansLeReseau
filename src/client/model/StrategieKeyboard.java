/*
 * Stratégie où on peux controller avec le clavier
 */

package client.model;

import model.AgentAction;
import model.KeyboadManager;

public class StrategieKeyboard {
    AgentAction action;
    KeyboadManager keyboard;

    public StrategieKeyboard(KeyboadManager key)
    {
        this.keyboard = key;
        action = new AgentAction(AgentAction.STOP);
    }

    public AgentAction getAction()
    {
        //System.out.println(agent.getGame().getKeyboard().getLastKey());
        switch(keyboard.getLastKey(0, false))
        {
            case KeyboadManager.KEY_UP:
                action = new AgentAction(AgentAction.NORTH);
                break;
            case KeyboadManager.KEY_DOWN:
                action = new AgentAction(AgentAction.SOUTH);
                break;
            case KeyboadManager.KEY_LEFT:
                action = new AgentAction(AgentAction.WEST);
                break;
            case KeyboadManager.KEY_RIGHT:
                action = new AgentAction(AgentAction.EAST);
                break;
            default:
                break;
        }
        return action;
    }
}
