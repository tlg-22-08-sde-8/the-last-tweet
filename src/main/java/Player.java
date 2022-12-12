public class Player {
    private Room room;
    private int hunger;
    private int employability;
    private int sanity;
    private int score;
    private int codeLines = 0;

    //player room field allows the game class to move character around the map
    public Player(int hunger, int employability, int sanity) {
        this.hunger = hunger;
        this.sanity = sanity;
        this.employability = employability;
    }

    public int normalAttack(){
        return 4;
    }

    public int superAttack(){
        return 7;
    }



    // accessor methods
    public int getCodeLines() {
        return codeLines;
    }

    public void setCodeLines(int codeLines) {
        this.codeLines = codeLines;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getEmployability() {
        return employability;
    }

    public void setEmployability(int employability) {
        this.employability = employability;
    }

    public int getSanity() {
        return sanity;
    }

    public void setSanity(int sanity) {
        this.sanity = sanity;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
