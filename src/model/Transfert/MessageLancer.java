package model.Transfert;

// Lancer une partie
public class MessageLancer {
    protected String carte;

    public MessageLancer()
    {
        
    }

    public MessageLancer(String carte)
    {
        this.carte = carte;
    }

    public void setCarte(String carte)
    {
        this.carte = carte;
    }

    public String getCarte()
    {
        return carte;
    }
}
