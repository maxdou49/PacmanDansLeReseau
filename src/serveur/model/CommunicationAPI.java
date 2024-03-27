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
            String line = reader.readLine();
            System.out.println("Response: " + line);
            //On a pas recu un json
            if(line.charAt(0) != '{')
            {
                throw new Exception("Echec de la connexion");
            }
            //On renvoit le joueur
            return mapper.readValue(line, Joueur.class);
        } catch(IOException e)
        {
            throw new Exception("Echec de la connexion");
        }
    }
}
