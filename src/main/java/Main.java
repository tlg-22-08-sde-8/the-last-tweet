import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //create new game
        //for now the only commands are "venture out" -> "go [north, west, east, south]
        final Player player = new Player(25, 25, 25);;
        Game game = new Game(player);
        game.gameIntro();
        game.commandInput();
    }
}
