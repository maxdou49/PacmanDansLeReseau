package model;

import controller.AbstractController;

public class MethodeFactory {
    private AbstractController controlleur;

    public MethodeFactory() {
    }

    public MethodeFactory(AbstractController ctr) {
        this.controlleur = ctr;
    }

    public String constructMessage(String message) {
        return "###\n" + message + "\n###\n";
    }
}
