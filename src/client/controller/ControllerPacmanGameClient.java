package client.controller;

import controller.GameControlleur;
import model.AgentAction;
import model.Maze;
import model.Transfert.EtatGame;
import model.Transfert.Message;
import model.Transfert.MessageBuilder;
import client.view.ViewCommand;
import client.view.ViewPacmanGame;

import java.io.IOException;

import javax.naming.directory.InvalidAttributesException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import client.model.PacmanGame;

public class ControllerPacmanGameClient extends GameControlleur {
    ViewPacmanGame viewGame;
    ViewCommand viewCom;
    EtatGame etatGame;
    MainControlleur controlleur;

    public ControllerPacmanGameClient(MainControlleur controlleur, EtatGame etat) throws Exception
    {
        super();

        this.controlleur = controlleur;
        etatGame = etat;
        
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
            controlleur.envoyerMessage((MessageBuilder.build(Message.ACTION, mapper.writeValueAsString(action))));
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

    public void setEtatGame(EtatGame etat)
    {
        etatGame = etat;
        viewGame.rafrachier(etatGame);
    }

    /***   
        *   fonction pour recuperer la maze
    ***/

    public Maze getMaze()
    {
        return (etatGame != null) ? etatGame.getMaze() : null;
    }

    public void play()
    {
        //Le thread est dans MainControlleur qui envoie vers ControlleurPacmanGameClient en récéption de message
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String rd;
                    do {
                        rd = rw.getReader().readLine();
                        if(rd != null)
                        {
                            String type = readMessage(rd);
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
        }).start();*/
      
        game.launch();
    }
}