import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //create new game
        //for now the only commands are "venture out" -> "go [north, west, east, south]
        Game game = new Game();
        game.commandInput();
    }
}
