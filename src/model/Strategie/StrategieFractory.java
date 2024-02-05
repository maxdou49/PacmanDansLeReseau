/*
 * Sélecteur de stratégie
 */
package model.Strategie;
import model.Agent;

public class StrategieFractory {

    public static StrategieAgent createStrategie(Agent a, ListeStrategie s) throws Exception
    {
        switch(s)
        {
            case NONE:
                return new IdleStrategie(a);
            case RANDOM:
                return new RandomStrategie(a);
            case KEYBOARD:
                return new StrategieKeyboard(a);
            case RANDOM_INTERSECTION:
                return new StrategieRandomIntersection(a);
            case TARGET_PACMAN:
                return new StrategieTargetPacman(a);
            case ASTAR_TARGET_PACMAN:
                return new StrategieAstarTargetPacman(a);
            case ASTAR_TARGET_FOOD:
                return new StrategieAstarTargetFood(a);
            case ASTAR_TARGET_FOOD_V2:
                return new StrategieAstarTargetFoodV2(a);
            case ASTAR_TARGET_FOOD_V3:
                return new StrategieAstarTargetFoodV3(a);
            default:
                throw new Exception("Strategie inconnue");
        }
    }
}
