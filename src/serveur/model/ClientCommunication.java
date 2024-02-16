package serveur.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.AgentAction;
import model.ReaderWriter;
import model.Transfert.EtatGame;
import model.Transfert.Message;
/*
 * Gère la communication entre le client et le serveur(coté serveur)
 */
import model.Transfert.MessageBuilder;

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
                    Message msg = MessageBuilder.buildFromString(str);
                    switch(msg.getType())
                    {
                        case Message.ACTION:
                            action = objectMapper.readValue(msg.getData(), AgentAction.class);
                            break;
                        default:
                            break;
                    }
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

    public void sendMessage(Message message)
    {
        try
        {
            client.getWriter().println(message.toString());
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
