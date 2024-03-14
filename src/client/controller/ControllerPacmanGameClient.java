package client.controller;

import controller.AbstractController;
import model.AgentAction;
import model.Maze;
import model.MethodeFactory;
import model.ReaderWriter;
import model.Transfert.EtatGame;
import model.Transfert.Message;
import model.Transfert.MessageBuilder;
import client.view.ViewCommand;
import client.view.ViewPacmanGame;

import java.io.IOException;
import java.net.Socket;

import javax.naming.directory.InvalidAttributesException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import client.model.PacmanGame;

public class ControllerPacmanGameClient extends AbstractController {
    ViewPacmanGame viewGame;
    ViewCommand viewCom;
    Socket socket;
    ReaderWriter rw;
    EtatGame etatGame;

    public ControllerPacmanGameClient(Socket so) throws Exception
    {
        super();

        this.socket = so;
        this.rw = new ReaderWriter(so);
        ObjectMapper mapper = new ObjectMapper();
        etatGame = null;
        do
        {
            String rd = rw.getReader().readLine();
            readMessage(rd);
        } while(etatGame == null);
        
        PacmanGame g = new PacmanGame(this);
        this.game = g;
        this.game.setMaxTurn(Integer.MAX_VALUE);
        this.viewGame = new ViewPacmanGame(this);
        this.viewCom = new ViewCommand(this);
        g.setKeyboard(viewGame.getKeyboard());
        this.game.init();
    }

    /***   
        *   fonction qui retourne la vue du jeu pacman utiliser par le controleur pacman
    ***/

    public ViewPacmanGame getViewGame() {
        return viewGame;
    }

    /***   
        *   fonction qui retourne le jeu pacman utiliser par le controleur pacman
    ***/

    public PacmanGame getGame() {
        return (PacmanGame) game;
    }

    /***   
        *   fonction pour evoyer sur le serveur unr action du pacman
     * @throws InvalidAttributesException 
     * @throws JsonProcessingException 
    ***/

    public void sendAction(AgentAction action)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            rw.getWriter().println((MessageBuilder.build(Message.ACTION, mapper.writeValueAsString(action))).toString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /***   
        *   fonction pour recuperer l'etat du jeu depuis le serveur
     * @throws InvalidAttributesException 
     * @throws IOException 
    ***/

    public EtatGame getEtatGame()
    {   
        return etatGame;
    }

    /***   
        *   fonction pour recuperer la maze
    ***/

    public Maze getMaze()
    {
        return (etatGame != null) ? etatGame.getMaze() : null;
    }

    public String readMessage(String message) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        Message msg = MessageBuilder.buildFromString(message);
        switch(msg.getType())
        {
            case Message.ETAT:
                etatGame = mapper.readValue(msg.getData(), EtatGame.class);
                break;
            default:
                break;
        }
            return msg.getType();
    }

    public void play()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String rd;
                    do {
                        rd = rw.getReader().readLine();
                        if(rd != null)
                        {
                            String type = readMessage(rd);
                            System.out.println(type);
                            if(type.equals(Message.ETAT))
                            {
                                viewGame.rafrachier(etatGame);
                            }
                        }
                    } while((rd != null));
                } catch (Exception e) {
                    System.out.println(new MethodeFactory().constructMessage("ControllerClient\t"+e));
                    e.printStackTrace();
                }
            }
        }).start();
      
        game.launch();
    }
}