package com.twitter.game.model;

import java.util.List;
import java.util.Map;

public class Room {
    private final int north;
    private final int south;
    private final int west;
    private final int east;
    private final String name;
    private String description;
    private List<Map<String, Map<String, Integer>>> storage;


    //create room for game
    public Room(String name, String description, int mapNorth, int mapSouth, int mapWest, int mapEast, List<Map<String, Map<String, Integer>>> storage) {
        this.name = name;
        this.description = description;
        this.north = mapNorth;
        this.south = mapSouth;
        this.west = mapWest;
        this.east = mapEast;
        this.storage = storage;
    }


    // accessor methods
    public List<Map<String, Map<String, Integer>>> getStorage() {
        return storage;
    }

    public void setStorage(List<Map<String, Map<String, Integer>>> storage) {
        this.storage = storage;
    }

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

    public void setDescription(String description) {
        this.description = description;
    }
}
