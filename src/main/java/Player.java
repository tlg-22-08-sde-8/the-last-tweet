public class Player {
    private Room room;
    private int hunger;
    private int employability;
    private int sanity;
    private int score;

    //player room field allows the game class to move character around the map
    public Player(int hunger, int employability, int sanity) {
        this.hunger = hunger;
        this.sanity = sanity;
        this.employability = employability;
    }

    // accessor methods
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
