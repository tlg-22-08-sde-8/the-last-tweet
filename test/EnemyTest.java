import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EnemyTest {
  Enemy enemy;

  @Before
  public void setUp() throws Exception {
    enemy = new Enemy(10, "test", "testNormalAttack", 10, "testSuperAttack", 20);
  }

  @Test
  public void getNormalAttackDmg() {
    assertEquals(10, enemy.getNormalAttackDmg());
  }

  @Test
  public void setNormalAttackDmg() {
    enemy.setNormalAttackDmg(30);
    assertEquals(30, enemy.getNormalAttackDmg());
  }

  @Test
  public void getSuperAttackDmg() {
    assertEquals(20, enemy.getSuperAttackDmg());
  }

  @Test
  public void setSuperAttackDmg() {
    enemy.setNormalAttackDmg(20);
    assertEquals(20, enemy.getSuperAttackDmg());
  }

  @Test
  public void getHealth() {
    assertEquals(10, enemy.getHealth());
  }

  @Test
  public void setHealth() {
    enemy.setNormalAttackDmg(10);
    assertEquals(10, enemy.getHealth());
  }

  @Test
  public void getTitle() {
    assertEquals("test", enemy.getTitle());

  }

  @Test
  public void setTitle() {
    enemy.setTitle("test2");
    assertEquals("test2", enemy.getTitle());
  }

  @Test
  public void getNormalAttack() {
    assertEquals("testNormalAttack", enemy.getNormalAttack());
  }

  @Test
  public void setNormalAttack() {
    enemy.setNormalAttack("testNormalAttack2");
    assertEquals("testNormalAttack2", enemy.getNormalAttack());
  }

  @Test
  public void getSuperAttack() {
    assertEquals("testSuperAttack", enemy.getSuperAttack());
  }

  @Test
  public void setSuperAttack() {
    enemy.setSuperAttack("testSuperAttack2");
    assertEquals("testSuperAttack2", enemy.getSuperAttack());
  }
}