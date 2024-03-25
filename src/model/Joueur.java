package model;

public class Joueur {
    private int id;
    private long score;
    private int nbGame;
    private String utilisateur;
    public Joueur() {
    	this.utilisateur = "";
        this.score = 0;
        this.nbGame = 0;
        this.id = 0;
    }
    public Joueur(String utilisateur) {
    	this.utilisateur = utilisateur;
        this.score = 0;
        this.nbGame = 0;
        this.id = 0;
    }
    public void setScore(long score) {
        this.score = score;
    }
    public void setNbGame(int nbGame) {
        this.nbGame = nbGame;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getScore() {
        return score;
    }
    public int getNbGame() {
        return nbGame;
    }
    public void addScore(long score) {
        this.score += score;
        this.nbGame++;
    }

	public String getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}
}
