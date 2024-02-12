package model.Transfert;

import model.PositionAgent;

/*
 * Classe contenant l'état d'un agent
 */

public interface EtatAgent {
    public PositionAgent getPos();

    public boolean getAlive();
}
