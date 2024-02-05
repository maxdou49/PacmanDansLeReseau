/*
 * Pour créer des agents.
 */

package model;

public class AgentFactory {
    public Pacman createPacman(PacmanGame g, PositionAgent p)
    {
        return new Pacman(g, p);
    }

    public Fantome createFantome(PacmanGame g, PositionAgent p)
    {
        return new Fantome(g, p);
    }
}
