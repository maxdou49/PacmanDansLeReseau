package serveur.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.AgentAction;
import model.ReaderWriter;
import model.Transfert.EtatGame;

/*
 * Gère la communication entre le client et le serveur(coté serveur)
 */

public class ClientCommunication implements Runnable {
    ReaderWriter client;
    boolean running;
    AgentAction action;
    ObjectMapper objectMapper;

    public ClientCommunication(ReaderWriter client)
    {
        this.client = client;
        objectMapper = new ObjectMapper();
    }

    public void run()
    {
        this.running = true;
        try
        {
            //Récuperer la dernière action du client
            while(running)
            {
                
                    String str = client.getReader().readLine();
                    if(str != null)
                    {
                        action = objectMapper.readValue(str, AgentAction.class);
                    }
                    else
                    {
                        running = false;
                    }
                
            }
        } catch(Exception e)
        {
            running = false;
        }
    }

    public boolean isRunning()
    {
        return running;
    }

    public AgentAction getAction()
    {
        return action;
    }

    public void sendState(EtatGame state)
    {
        try
        {
            System.out.println(client.getAddress());
            System.out.println(objectMapper.writeValueAsString(state));
            client.getWriter().println(objectMapper.writeValueAsString(state));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void launch()
    {
        Thread t = new Thread(this);
        t.start();
    }
}
