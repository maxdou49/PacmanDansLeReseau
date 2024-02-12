/*
 * Sélecteur de stratégie
 */
package client.model.Strategie;
import client.model.Agent;

public class StrategieFractory {

    public static StrategieAgent createStrategie(Agent a, ListeStrategie s) throws Exception
    {
        switch(s)
        {
            case NONE:
                return new IdleStrategie(a);
            case KEYBOARD:
                return new StrategieKeyboard(a);
            default:
                throw new Exception("Strategie inconnue");
        }
    }
}
