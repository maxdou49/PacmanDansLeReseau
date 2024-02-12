/*
 * Stratégie qui part vers le prochain point d'un parcours calculé par A*.
 * Vraiment mal opti
 */

package serveur.model.Strategie;

import java.util.ArrayList;
import java.util.PriorityQueue;

import serveur.model.Agent;
import model.AgentAction;
import model.Noeud;
import model.PositionAgent;

public abstract class StrategieAStar extends StrategieAgent {
    public StrategieAStar(Agent a)
    {
        super(a);
    }

    //La case est une cible?
    public abstract boolean isTarget(PositionAgent pos);
    public void getHeuristic(Noeud a)
    {
        a.setHeuristic(0);
    }

    //On peut aller là?
    public boolean isValid(PositionAgent pos)
    {
        return !agent.getGame().getMaze().isWall(pos.getX(), pos.getY());
    }

    //Récupérer la prochaine action d'un chemin
    public AgentAction getNodeAction(Noeud n)
    {
        if(n == null)
        {
            return new AgentAction(AgentAction.STOP);
        }
        Noeud current = n;
        Noeud prev = n.prev;
        while(prev != null && prev.prev != null) //On cherche le deuxième noeud(car le premier est le point de départ).
        {
            current = prev;
            prev = current.prev;
        }
        return current.action;
    }

    //Chercher un noeud menant au même endroit dans la liste
    public Noeud getNode(PriorityQueue<Noeud> list, Noeud n)
    {
        for(Noeud test: list)
        {
            if(test.equals(n))
            {
                return test;
            }
        }
        return null;
    }

    public boolean alreadyVisited(ArrayList<Noeud> list, Noeud n)
    {
        for(Noeud test: list)
        {
            if(test.equals(n))
            {
                return true;
            }
        }
        return false;
    }

    public AgentAction aStar(PositionAgent pos)
    {
        PriorityQueue<Noeud> list = new PriorityQueue<Noeud>();
        ArrayList<Noeud> visited = new ArrayList<Noeud>();
        list.add(new Noeud(pos, 0, new AgentAction(AgentAction.STOP), null));
        while(!list.isEmpty())
        {
            Noeud n = list.poll();
            if(isTarget(n.pos)) //On a trouvé une cible
            {
                return getNodeAction(n);
            }
            for(int i = 0; i < 4; i++)
            {
                AgentAction action = new AgentAction(i);
                PositionAgent nextPos = new PositionAgent(n.pos.getX() + action.get_vx(), n.pos.getY() + action.get_vy(), i);
                if(isValid(nextPos)) //On peux aller là
                {
                    Noeud a = new Noeud(nextPos, n.distance + 1, action, n);
                    if(!alreadyVisited(visited, a)) //Le noeud n'a pas été visité
                    {
                        getHeuristic(a);
                        Noeud b = getNode(list, a); //On regarde si on a déjà un chemin menant ici
                        boolean add = true;
                        if(b != null)
                        {
                            if(b.compareTo(a) > 0) //On a déjà plus proche. On ignore
                            {
                                add = false;
                            }
                            else //On est plus proche. Autant virer l'ancien
                            {
                                list.remove(b);
                            }
                        }
                        if(add)
                        {
                            list.add(a);
                        }
                    }
                }
                visited.add(n);
            }
        }
        return new AgentAction(AgentAction.STOP);
    }

    public AgentAction getAction()
    {
        return aStar(agent.getPos());
    } 
}
