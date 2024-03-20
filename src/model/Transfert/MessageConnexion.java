package model.Transfert;

public class MessageConnexion {
    protected String utilisateur;
    protected String motdepasse;

    public MessageConnexion()
    {
        
    }

    public MessageConnexion(String utilisateur, String motdepasse)
    {
        this.utilisateur = utilisateur;
        this.motdepasse = motdepasse;
    }

    public void setUtilisateur(String utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    public String getUtilisateur()
    {
        return utilisateur;
    }

    public void setMotdepasse(String motdepasse)
    {
        this.motdepasse = motdepasse;
    }

    public String getMotdepasse()
    {
        return motdepasse;
    }
}
