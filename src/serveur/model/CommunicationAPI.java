package serveur.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Joueur;

public class CommunicationAPI {
    private static final String api = "http://localhost:8080/AppWebPacMan1/AppWebApi/"; //A changer si c'est autre part

    private static ObjectMapper mapper = new ObjectMapper();

    private static HttpURLConnection getConnexion(String endpoint) throws MalformedURLException, IOException
    {
        return (HttpURLConnection)new URL(api + endpoint).openConnection();
    }

    public static Joueur connexion(String utilisateur, String motdepasse) throws Exception
    {
        try
        {
            //On lance la connexion
            HttpURLConnection connexion = getConnexion("Connexion");
            connexion.setRequestMethod("GET");
            //On définit les parametres
            connexion.setRequestProperty("utilisateur", utilisateur);
            connexion.setRequestProperty("motdepasse", motdepasse);
            //On lit
            System.out.println(connexion.getURL().toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            int status = connexion.getResponseCode();
            if(status != 200) //La requete a échoué.
            {
                throw new Exception("Echec de la connexion");
            }
            //Celle-ci a fonctionner donc on renvoie le joueur
            return mapper.readValue(reader.readLine(), Joueur.class);
        } catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
