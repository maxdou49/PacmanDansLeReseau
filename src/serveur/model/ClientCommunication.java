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
        System.out.println("running Client thread...");
        this.running = true;
        try
        {
            //Récuperer la dernière action du client
            while(running)
            {
                    System.out.println("###CLIENT### "+client);  
                    System.out.println("###BFR###### "+client.getReader());

                    String str = client.getReader().readLine();
                    System.out.println("###run###### " + str);
                    if(str != null)
                    {
                        action = objectMapper.readValue(str, AgentAction.class);
                    }
                    else
                    {
                        running = false;
                    }
                
            }
        System.out.println("terminating Client thread...");
        } catch(Exception e)
        {
            running = false;
            System.out.println("terminating Client thread...(EXCEPTION)");
        }
        System.out.println("terminating Client thread...");
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
            //System.out.println(client.getAddress());
            //System.out.println(objectMapper.writeValueAsString(state));
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
