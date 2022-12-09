public class Player {
    private Room room;

    //player room field allows the game class to move character around the map
    public Player() {
    }

    // accessor methods
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
