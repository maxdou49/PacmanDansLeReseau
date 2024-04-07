package serveur.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Joueur;

public class CommunicationAPI {
    private static final String api = "http://localhost:8080/AppWebPacMan/AppWebApi/"; //A changer si c'est autre part

    private static ObjectMapper mapper = new ObjectMapper();

    private static HttpURLConnection getConnexion(String endpoint) throws MalformedURLException, IOException
    {
        return (HttpURLConnection)new URL(api + endpoint).openConnection();
    }

    public static Joueur connexion(String utilisateur, String motdepasse) throws Exception
    {
        try
        {
            utilisateur = utilisateur.trim();
            motdepasse = motdepasse.trim();
            //On lance la connexion
            HttpURLConnection connexion = getConnexion(String.format("Connexion?utilisateur=%s&motdepasse=%s", utilisateur, motdepasse));
            connexion.setRequestMethod("GET");
            //On lit
            BufferedReader reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            StringBuilder str = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null)
            {
                str.append(line);
            }
            System.out.println("Response: " + str);
            //On a pas recu un json
            if(str.charAt(0) != '{')
            {
                throw new Exception("Echec de la connexion");
            }
            //On renvoit le joueur
            return mapper.readValue(str.toString(), Joueur.class);
        } catch(IOException e)
        {
            throw new Exception("Echec de la connexion");
        }
    }

    public static void envoiPartie(ArrayList<Joueur> joueurs, String maze, int score, boolean endless, boolean victoire) throws Exception
    {
        try
        {
            //On lance la connexion
            StringBuilder listeId = new StringBuilder();
            for(int i = 0; i < joueurs.size(); i++)
            {
                if(i > 0)
                {
                    listeId.append(',');
                }
                listeId.append(joueurs.get(i).getId());
            }
            String listeIdStr = listeId.toString();
            System.out.println(joueurs.size() + " "+listeIdStr);
            String get = String.format("AddPartie?score=%d&maze=%s&endless=%d&victoire=%d&joueurs=%s", score, maze, endless ? 1 : 0, victoire ? 1 : 0, listeIdStr);
            System.out.println(get);
            HttpURLConnection connexion = getConnexion(get);
            connexion.setRequestMethod("GET");
            //On lit pour envoyer la requête car cette bibliothèque est bizarre
            BufferedReader reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            reader.readLine();
        } catch(IOException e)
        {
            throw new Exception("Echec de la connexion");
        }
    }
}
