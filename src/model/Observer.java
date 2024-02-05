/*
 * Une classe qui peut écouter une autre en attente de mise à jour
 */

package model;
public interface Observer {
    public void update(Observable o);
}
