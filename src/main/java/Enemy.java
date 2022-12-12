public class Enemy {
    private int health;
    private String title;
    private String normalAttack;
    private int normalAttackDmg;
    private String superAttack;
    private int superAttackDmg;

    public Enemy(int health, String title, String normalAttack, int normalAttackDmg, String superAttack, int superAttackDmg) {
        this.health = health;
        this.title = title;
        this.normalAttack = normalAttack;
        this.normalAttackDmg = normalAttackDmg;
        this.superAttack = superAttack;
        this.superAttackDmg = superAttackDmg;
    }

    //accessor methods
    public int getNormalAttackDmg() {
        return normalAttackDmg;
    }

    public void setNormalAttackDmg(int normalAttackDmg) {
        this.normalAttackDmg = normalAttackDmg;
    }

    public int getSuperAttackDmg() {
        return superAttackDmg;
    }

    public void setSuperAttackDmg(int superAttackDmg) {
        this.superAttackDmg = superAttackDmg;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNormalAttack() {
        return normalAttack;
    }

    public void setNormalAttack(String normalAttack) {
        this.normalAttack = normalAttack;
    }

    public String getSuperAttack() {
        return superAttack;
    }

    public void setSuperAttack(String superAttack) {
        this.superAttack = superAttack;
    }
}

