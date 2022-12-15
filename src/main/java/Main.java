import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //create new game
        while (true) {
            final Player player = new Player(25, 25, 25);
            Game game = new Game(player);
            game.gameIntro();
            game.commandInput();
            System.out.println("want to play again? (enter 'yes' for new game)");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String newGame = br.readLine().toLowerCase();
            if (!newGame.equals("yes")){
                break;
            }
        }
    }
}
