import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
  Player player;

  @Test
  public void normalAttack() {
  }
  @Before
  public void setUp() throws Exception {
    player  = new Player(1, 1, 1);

  }


  @Test
  public void testNormalAttack() {

    assertEquals(4, player.normalAttack());
  }

  
  @Test
  public void testSuperAttack() {

    assertEquals(7, (new Player(1, 1, 1)).superAttack());
  }

  @Test
  public void superAttack() {
  }

  @Test
  public void getCodeLines() {
    assertEquals(0, player.getCodeLines());
  }

  @Test
  public void setCodeLines() {
    player.setCodeLines(10);
    assertEquals(10, player.getCodeLines());
  }

  @Test
  public void getScore() {

  }

  @Test
  public void setScore() {
  }

  @Test
  public void getHunger() {
  }

  @Test
  public void setHunger() {
  }

  @Test
  public void getEmployability() {
  }

  @Test
  public void setEmployability() {
  }

  @Test
  public void getSanity() {
  }

  @Test
  public void setSanity() {
  }

  @Test
  public void getRoom() {
  }

  @Test
  public void setRoom() {
  }
}