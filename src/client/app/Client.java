package client.app;

import client.controller.MainControlleur;

public class Client {
    public static void main(String[] argu) throws InterruptedException
    {
        try {
            MainControlleur menu = new MainControlleur(argu);
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
