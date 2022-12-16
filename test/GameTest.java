import static org.junit.Assert.*;

import com.twitter.game.controller.Game;
import com.twitter.game.model.Player;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
  Game game;

  @Before
  public void setUp() throws Exception {
    Player player = new Player(10, 10,10);
    game = new Game(player);
  }

  @Test
  public void travel() {
  }

  @Test
  public void parseCommand() throws IOException {
  }
}
