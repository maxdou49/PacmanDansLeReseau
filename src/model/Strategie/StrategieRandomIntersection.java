/*
 * Stratégie où on essaye d'aller dans une direction aléatoire(stratégie fright dans le jeu original)
 */

package model.Strategie;
import model.Agent;

public class StrategieRandomIntersection extends StrategieBase {
    public StrategieRandomIntersection(Agent a)
    {
        super(a);
    }

    public int selectDir()
    {
        return agent.getGame().getRandom().next(4);
    }
}
