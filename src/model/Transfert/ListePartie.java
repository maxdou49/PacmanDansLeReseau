package model.Transfert;

import java.util.ArrayList;

public class ListePartie {
    ArrayList<Integer> liste;

    public ArrayList<Integer> getListe() {
        return liste;
    }

    public void setListe(ArrayList<Integer> liste) {
        this.liste = liste;
    }

    public ListePartie() {
    }

    public ListePartie(ArrayList<Integer> liste) {
        this.liste = liste;
    }
}
