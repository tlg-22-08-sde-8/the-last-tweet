package com.twitter.game.model;

import com.twitter.game.model.Room;

import java.util.Map;

public class Player {

    private Room room;
    private int hunger;
    private int employability;
    private int sanity;
    private int score;
    private int codeLines = 0;
    private int level = 1;
    private boolean bugfix = false;
    private boolean twoFactor = false;
    private boolean digitalAss = false;
    private boolean contentModeration = false;
    private Map<String, Integer> inventory;

    //player room field allows the game class to move character around the map
    public Player(int hunger, int employability, int sanity) {
        this.hunger = hunger;
        this.sanity = sanity;
        this.employability = employability;

    }

    public int normalAttack(){
        return 10 * level;
    }

    public int superAttack(){
        return normalAttack() * 2;
    }

    // accessor methods
    public boolean isBugfix() {
        return bugfix;
    }

    public void setBugfix(boolean bugfix) {
        this.bugfix = bugfix;
    }

    public boolean isTwoFactor() {
        return twoFactor;
    }

    public void setTwoFactor(boolean twoFactor) {
        this.twoFactor = twoFactor;
    }

    public boolean isDigitalAss() {
        return digitalAss;
    }

    public void setDigitalAss(boolean digitalAss) {
        this.digitalAss = digitalAss;
    }

    public boolean isContentModeration() {
        return contentModeration;
    }

    public void setContentModeration(boolean contentModeration) {
        this.contentModeration = contentModeration;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}
