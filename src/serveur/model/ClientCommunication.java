package serveur.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.AgentAction;
import model.ReaderWriter;
import model.Transfert.Message;
/*
 * Gère la communication entre le client et le serveur(coté serveur)
 */

public class ClientCommunication {
    ReaderWriter client;
    boolean running;
    AgentAction action;
    ObjectMapper objectMapper;

    public ClientCommunication(ReaderWriter client)
    {
        this.client = client;
        objectMapper = new ObjectMapper();
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
}
