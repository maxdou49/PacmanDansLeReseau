/*
 * Une classe qui peut notifier de mise à jour
 */
package model;
public interface Observable {
    public void addObserver(Observer o);
    public void removerObserver(Observer o);
}
