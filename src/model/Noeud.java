package model;

/*
 * Un noeud pour calculer un chemin A*
 */

public class Noeud implements Comparable<Noeud> {
    public PositionAgent pos; //La position
    public Double distance; //La distance par rapport au départ
    public AgentAction action; //La dernière action effectuée
    public Noeud prev; //Le dernier noeud
    public Double heuristic;

    public Noeud(PositionAgent pos, double distance, AgentAction action, Noeud prev)
    {
        this.pos = pos;
        this.distance = Double.valueOf(distance);
        this.action = action;
        this.prev = prev;
        this.heuristic = 0.0;
    }

    public void setHeuristic(double h)
    {
        this.heuristic = h;
    }

    public Double getDist()
    {
        return distance + heuristic;
    }

    public int compareTo(Noeud b)
    {
        
        return getDist().compareTo(b.getDist());
    }

    public boolean equals(Noeud b)
    {
        return pos.equals(b.pos);
    }

}
