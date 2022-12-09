public class Room {
    private final int north;
    private final int south;
    private final int west;
    private final int east;
    private final String name;
    private final String description;

    public Room(String name, String description, int mapNorth, int mapSouth, int mapWest, int mapEast) {
        this.name = name;
        this.description = description;
        this.north = mapNorth;
        this.south = mapSouth;
        this.west = mapWest;
        this.east = mapEast;
    }

    // --- accessor methods ---
    public int getNorth() {
        return north;
    }

    public int getSouth() {
        return south;
    }

    public int getWest() {
        return west;
    }

    public int getEast() {
        return east;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
