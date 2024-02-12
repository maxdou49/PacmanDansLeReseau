/*
 * Pour créer des agents.
 */

package serveur.model;

import model.PositionAgent;

public class AgentFactory {
    public Pacman createPacman(PacmanGame pacmanGame, PositionAgent p)
    {
        return new Pacman(pacmanGame, p);
    }

    public Fantome createFantome(PacmanGame g, PositionAgent p)
    {
        return new Fantome(g, p);
    }

}
