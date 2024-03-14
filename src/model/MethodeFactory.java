package model;

import controller.GameControlleur;

public class MethodeFactory {
    private GameControlleur controlleur;

    public MethodeFactory() {
    }

    public MethodeFactory(GameControlleur ctr) {
        this.controlleur = ctr;
    }

    public String constructMessage(String message) {
        return "###\n" + message + "\n###\n";
    }
}
