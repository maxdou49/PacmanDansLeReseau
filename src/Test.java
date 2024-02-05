import controller.ControleurPacmanGame;
public class Test {
    public static void main(String[] args)
    {
        ControleurPacmanGame game = new ControleurPacmanGame("layout/originalClassic.lay");
        game.play();
    }
}
