package com.twitter.game.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twitter.game.model.*;

import javax.sound.sampled.*;
import java.io.*;
import java.util.*;

public class Game {
//    private static final String ANSI_PURPLE = "\u001B[35m";
//    private static final String ANSI_CYAN = "\u001B[36m";
//    private static final String ANSI_WHITE = "\u001B[37m";
//    private static final String ANSI_BLACK = "\u001B[30m";
//    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private final String[] wordsForNorth = {"north", "n"};
    private final String[] wordsForSouth = {"south", "s"};
    private final String[] wordsForWest = {"west", "w"};
    private final String[] wordsForEast = {"east", "e"};
    private final String[] defaultCommands = {"Inventory", "View Map", "More", "save", "load", "help"};
    private final String[] workstationCommands = {"Code", "Read Book", "go north", "go east", "go west"};
    private final String[] breakRoomCommands = {"access vending machine", "go south", "go west", "go east"};
    private final String[] coffeeBarCommands = {"Brew Coffee","go north", "go east"};
    private final String[] emptyWorkstationCommands = {"go north", "go west"};
    private final String[] meetingRoom1Commands = {"go south", "go east"};
    private final String[] meetingRoom2Commands = {"go south", "go west"};
    private Player player;
    private final List<Room> gameMap;
    private final ArrayList<Enemy> enemyArray;
    private Clip clip;
    private boolean gameOver = false;
    private boolean music = true;
    private int coffeeCount = 0;
    private Map <String, Integer> inventory;

    public Game(Player player) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        /**
         * Start background music
         */
        backgroundMusic();

        /**
         * Loads in items and init player inventory to zero
         */
        BufferedReader br2 = new BufferedReader(new FileReader("resources/items.json"));
        Gson gson2 = new Gson();
        inventory = new HashMap<>();
        List<String> gameItems = gson2.fromJson(br2, new TypeToken<List<String>>() {
        }.getType());
        br2.close();
        for (String s : gameItems) {
            inventory.put(s, 0);
        }
        player.setInventory(inventory);

        /**
         * Load in rooms
         */
        BufferedReader br = new BufferedReader(new FileReader("resources/rooms.json"));
        Gson gson = new Gson();
        gameMap = gson.fromJson(br, new TypeToken<List<Room>>() {
        }.getType());
        br.close();
        gameMap.get(1).setDescription(Script.getPlayerInBreakRoom());
        gameMap.get(2).setDescription(Script.getPlayerInMeetingRoom());
        gameMap.get(3).setDescription(Script.getPlayerInCoffeeBar());
        gameMap.get(4).setDescription(Script.getPlayerFindsAbandonedWorkstation());
        gameMap.get(5).setDescription(Script.getPlayerInMeetingRoom());

        /**
         * assign player to starting location
         */
        this.player = player;
        player.setRoom(gameMap.get(0));

        /**
         * Load enemies
         */
        BufferedReader br3 = new BufferedReader(new FileReader("resources/enemies.json"));
        Gson gson3 = new Gson();
        enemyArray = gson3.fromJson(br3, new TypeToken<List<Enemy>>() {
        }.getType());
        br3.close();
    }

    /**
     * Generates background music
     */
    public void backgroundMusic() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        if (music) {
            File file = new File("resources/Minecraft.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(.7));
            clip.start();
        }
    }

    /**
     * Generates battle music
     */
    public void battleMusic() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        clip.stop();
        if (music) {
            File file = new File("resources/Pokemon.wav");
            AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream1);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(.1));
            clip.start();
        }
    }

    /**
     * Generates Victory music
     */
    public void victoryMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (music) {
            File file1 = new File("resources/win_pokemon.wav");
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file1);
            clip = AudioSystem.getClip();
            clip.open(audioStream2);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(.1));
            clip.start();
        }
    }

    /**
     * stops playing the current clip of music
     */
    public void stopMusic() {
        clip.stop();
    }


    /**
     * Generates title/splash screen
     */
    public void gameIntro() throws InterruptedException {
        //create game intro logo and intro story lines
        String gameIntroLogo = ANSI_BLUE +
                "▄▄▄█████▓ ██░ ██ ▓█████     ██▓     ▄▄▄        ██████ ▄▄▄█████▓   ▄▄▄█████▓ █     █░▓█████ ▓█████ ▄▄▄█████▓\n" +
                "▓  ██▒ ▓▒▓██░ ██▒▓█   ▀    ▓██▒    ▒████▄    ▒██    ▒ ▓  ██▒ ▓▒   ▓  ██▒ ▓▒▓█░ █ ░█░▓█   ▀ ▓█   ▀ ▓  ██▒ ▓▒\n" +
                "▒ ▓██░ ▒░▒██▀▀██░▒███      ▒██░    ▒██  ▀█▄  ░ ▓██▄   ▒ ▓██░ ▒░   ▒ ▓██░ ▒░▒█░ █ ░█ ▒███   ▒███   ▒ ▓██░ ▒░\n" +
                "░ ▓██▓ ░ ░▓█ ░██ ▒▓█  ▄    ▒██░    ░██▄▄▄▄██   ▒   ██▒░ ▓██▓ ░    ░ ▓██▓ ░ ░█░ █ ░█ ▒▓█  ▄ ▒▓█  ▄ ░ ▓██▓ ░ \n" +
                "  ▒██▒ ░ ░▓█▒░██▓░▒████▒   ░██████▒ ▓█   ▓██▒▒██████▒▒  ▒██▒ ░      ▒██▒ ░ ░░██▒██▓ ░▒████▒░▒████▒  ▒██▒ ░ \n" +
                "  ▒ ░░    ▒ ░░▒░▒░░ ▒░ ░   ░ ▒░▓  ░ ▒▒   ▓▒█░▒ ▒▓▒ ▒ ░  ▒ ░░        ▒ ░░   ░ ▓░▒ ▒  ░░ ▒░ ░░░ ▒░ ░  ▒ ░░   \n" +
                "    ░     ▒ ░▒░ ░ ░ ░  ░   ░ ░ ▒  ░  ▒   ▒▒ ░░ ░▒  ░ ░    ░           ░      ▒ ░ ░   ░ ░  ░ ░ ░  ░    ░    \n" +
                "  ░       ░  ░░ ░   ░        ░ ░     ░   ▒   ░  ░  ░    ░           ░        ░   ░     ░      ░     ░      \n" +
                "          ░  ░  ░   ░  ░       ░  ░      ░  ░      ░                           ░       ░  ░   ░  ░         \n" +
                "                                                                                                           "
                + ANSI_RESET;
        String logoSubTitle = ANSI_BLUE + "\t\t\t\t\t\t\tThe Last Tweet: A Twitter Survival Game" + ANSI_RESET;
        String storyIntro = "You look at your desk. On your laptop, you have X lines of code.";
        //display intro to user
        System.out.println(gameIntroLogo);
        System.out.println(logoSubTitle);
        Thread.sleep(7000);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(Script.getFirstScene());
        System.out.println("You cant let this happen, You need to replace Elon as CEO");
        System.out.println(storyIntro);
        System.out.println("There is a book on your desk");
    }

    /**
     * Generates game over screen
     */
    public void gameOver() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        //display game over logo
        gameOver = true;
        clip.stop();
        File file = new File("resources/game-over.wav");
        AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream1);
        clip.start();
        String gameOverLogo = "\n\n" + ANSI_RED +
                "  ▄████  ▄▄▄       ███▄ ▄███▓▓█████     ▒█████   ██▒   █▓▓█████  ██▀███  \n" +
                " ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀    ▒██▒  ██▒▓██░   █▒▓█   ▀ ▓██ ▒ ██▒\n" +
                "▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███      ▒██░  ██▒ ▓██  █▒░▒███   ▓██ ░▄█ ▒\n" +
                "░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄    ▒██   ██░  ▒██ █░░▒▓█  ▄ ▒██▀▀█▄  \n" +
                "░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒   ░ ████▓▒░   ▒▀█░  ░▒████▒░██▓ ▒██▒\n" +
                " ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░   ░ ▒░▒░▒░    ░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░\n" +
                "  ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░     ░ ▒ ▒░    ░ ░░   ░ ░  ░  ░▒ ░ ▒░\n" +
                "░ ░   ░   ░   ▒   ░      ░      ░      ░ ░ ░ ▒       ░░     ░     ░░   ░ \n" +
                "      ░       ░  ░       ░      ░  ░       ░ ░        ░     ░  ░   ░     \n" +
                "                                                     ░                   "
                + ANSI_RESET;
        String gameOverLogoSubtitleEmp = ANSI_RED + "\tYou lost your employability .....you were fired on the spot" + ANSI_RESET;
        String gameOverLogoSubtitleHunger = ANSI_RED + "\tYou starved to death.....after that you were fired on the spot" + ANSI_RESET;
        String gameOverLogoSubtitleSanity = ANSI_RED + "\tYou went insane ..... after that you were fired on the spot" + ANSI_RESET;
        //display game over to user
        System.out.println(gameOverLogo);
        if (player.getSanity() == 0){
            System.out.println(gameOverLogoSubtitleSanity);
        } else if (player.getHunger() == 0) {
            System.out.println(gameOverLogoSubtitleHunger);
        } else {
                System.out.println(gameOverLogoSubtitleEmp);
        }
        Thread.sleep(4000);
        clip.stop();
    }

    /**
     * Generates main player interface
     */
    public void commandInput() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        //get commands from player
        String command;
        while (player.getSanity() > 0 && player.getHunger() > 0 && player.getEmployability() > 0 && !gameOver) {
            renderUserInterface();
            System.out.println("\nWhat would you like to do?");

            String[] determineAvailableCommands = determineAvailableCommands(player.getRoom().getName());
            for (String c : determineAvailableCommands) {
                System.out.print("> " + c + "    ");
            }
            for (String c : defaultCommands) {
                System.out.print("> " + c + "    ");
            }
            System.out.print("\n> ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command = br.readLine().strip().toLowerCase();
            parseCommand(command);
        }

        if (!gameOver) {
            gameOver();
        }
    }

    /**
     * display user status bar
     */
    public void renderUserInterface() {
        //display user stats
        String userStats =
                "==============================================================================================================================\n" +
                        "  Location = " + player.getRoom().getName() + "                      hunger = " + player.getHunger() + "   employability = " + player.getEmployability() + "  sanity = " + player.getSanity() + "                            SDE-1 \n" +
                        "==============================================================================================================================";
        System.out.println(userStats);
    }

    /**
     * adds room specific commands to main player interface
     */
    private String[] determineAvailableCommands(String currentRoom) {
        //check if command can be completed in room
        String[] commands = new String[0];
        if (currentRoom.equals("WorkStation")) {
            commands = workstationCommands;
        }
        if (currentRoom.equals("Break Room")) {
            commands = breakRoomCommands;
        }
        if (currentRoom.equals("Coffee Bar")) {
            commands = coffeeBarCommands;
        }
        if (currentRoom.equals("Meeting Room-1")) {
            commands = meetingRoom1Commands;
        }
        if (currentRoom.equals("Meeting Room-2")) {
            commands = meetingRoom2Commands;
        }
        if (currentRoom.equals("Empty workstation")) {
            commands = emptyWorkstationCommands;
        }
        return commands;
    }

    /**
     * parse user commands
     */
    public void parseCommand(String command) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        String[] commands = command.split(" ");
        //travel
        switch (commands[0]) {
            case "go":
                travel(commands);
                break;
            //gain one line of code
            case "code":
                if (player.getRoom().getName().equals("WorkStation")) {
                    code();
                } else {
                    System.out.println("command not valid");
                }
                break;
            //display stats and instructions
            case "access":
                if (player.getRoom().getName().equals("Break Room")) {
                    accessVendingMachine();
                } else {
                    System.out.println("command not valid");
                }
                break;
            case "brew":
                if (command.equals("brew coffee")){
                    if (player.getRoom().getName().equals("Coffee Bar")) {
                        brewCoffee();
                    }
                } else {
                    System.out.println("command not valid");
                }
                break;
            case "more":
                more();
                break;
            //help
            case "help":
                help();
                break;
            case "save":
                save();
                break;
            case "load":
                load();
                break;
            case "inventory":
                Inventory();
                break;
            case "view":
                if (command.equals("view map")){
                    viewMap();
                }
                break;
            case "stop":
                if (command.equals("stop music")) {
                    stopMusic();
                    music = false;
                } else {
                    System.out.println("command not valid");
                }
                break;
            case "start":
                if (command.equals("start music")) {
                    music = true;
                    backgroundMusic();

                } else {
                    System.out.println("command not valid");
                }
                break;
            //quit game
            case "read":
                if (player.getRoom().getName().equals("WorkStation") && command.equals("read book")) {
                    System.out.println(Script.getSurvivalGuide());
                } else {
                    System.out.println("command not valid");
                }
                break;
            case "quit":
                gameOver();
                break;
            default:
                System.out.println("command not valid");
        }
    }

    public void viewMap(){
        GameMaps.getGameMap();
    }


    /**
     * moves player to different rooms
     */
    public void travel(String[] checkDirection) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        //move player to a different room
        if (checkDirection.length == 2) {
            String direction = checkDirection[1].toLowerCase();
            int go = -1;
            Room currentRoom = player.getRoom();
            //check command for direction
            if (Arrays.asList(wordsForNorth).contains(direction)) {
                go = currentRoom.getNorth();
            }
            if (Arrays.asList(wordsForSouth).contains(direction)) {
                go = currentRoom.getSouth();
            }
            if (Arrays.asList(wordsForWest).contains(direction)) {
                go = currentRoom.getWest();
            }
            if (Arrays.asList(wordsForEast).contains(direction)) {
                go = currentRoom.getEast();
            }
            //determine if direction exists
            if (go != -1) {
                player.setRoom(gameMap.get(go));
                determineBattle();
                if (player.getSanity() > 0 && player.getHunger() > 0 && player.getEmployability() > 0) {
                    System.out.println(ANSI_RED + "You traveled " + direction + ANSI_RESET + "\n" + gameMap.get(go).getName() + "\n" + gameMap.get(go).getDescription());
                    player.setHunger(player.getHunger() - 5);
                }
                coffeeCount = 0;
            } else {
                System.out.println("looks like this way is blocked");
            }
        }
    }

    /**
     * determines if player will battle during travel
     */
    public void determineBattle() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        Random rand = new Random();
        int battleNum = rand.nextInt(10);
        if (battleNum >= 7) {
            Random rand1 = new Random();
            int enemyIndex = rand1.nextInt(enemyArray.size() - 1);
            battle(enemyArray.get(enemyIndex));
        }
    }

    public void battleReward(){
        List<String> items = new ArrayList<>(inventory.keySet());
        Random rand2 = new Random();
        String randomItem = items.get(rand2.nextInt(items.size()));
        player.getInventory().put(randomItem, player.getInventory().get(randomItem) + 1);
        System.out.println(ANSI_RED + "Congratulation you gained one " + randomItem + ANSI_RESET);
    }

    /**
     * player battle interface
     */
    public void battle(Enemy enemy) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        battleMusic();
        System.out.println(ANSI_RED + "You are starting a battle" + ANSI_RESET);
        while (true) {
            //determine if player wants to battle
            System.out.println("What would you like to do \n > Attack > Runaway");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = br.readLine().toLowerCase();
            if (command.equals("runaway")) {
                Random rand1 = new Random();
                int determineSuccessfulRun = rand1.nextInt(10);
                if (determineSuccessfulRun >= 7) {
                    System.out.println("You got away successfully");
                    return;
                } else {
                    System.out.println("runaway unsuccessful");
                    break;
                }
            }
            if (command.equals("attack")) {
                break;
            }
            System.out.println("command not valid");
        }

        //fight logic
        int storeEnemyHealth = enemy.getHealth();
        Random rand2 = new Random();
        int determineAttackType = rand2.nextInt(10);
        int cooldown = 0;
        battle:
        while (enemy.getHealth() > 1 && player.getSanity() > 0) {
            if (determineAttackType >= 7) {
                player.setSanity(player.getSanity() - enemy.getSuperAttackDmg());
                System.out.println(enemy.getTitle() + " hit you with " + enemy.getSuperAttack() + ANSI_RED + "\nyou lost " + enemy.getSuperAttackDmg() + " sanity\nyou have " + player.getSanity() + " sanity remaining" + ANSI_RESET);
            } else {
                System.out.println(enemy.getTitle() + " hit you with " + enemy.getNormalAttack() + ANSI_RED + "\nyou lost " + enemy.getNormalAttackDmg() + " sanity\nyou have " + player.getSanity() + " remaining" + ANSI_RESET);
                player.setSanity(player.getSanity() - enemy.getNormalAttackDmg());
            }
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if (player.getSanity() < 0) {
                    break battle;
                }
                System.out.println("Which attack would you like to use \n> normal > super ");
                String attack = br1.readLine().toLowerCase();
                if (attack.equals("super")) {
                    if (player.getCodeLines() - player.superAttack() > 0 && cooldown == 0) {
                        cooldown = 3;
                        enemy.setHealth(enemy.getHealth() - player.superAttack());
                        System.out.println(ANSI_YELLOW + "you attacked with a class generation \n" + enemy.getTitle() + " lost " + player.superAttack() + " health\nthey have " + enemy.getHealth() + " remaining" + ANSI_RESET);
                        player.setCodeLines(player.getCodeLines() - player.superAttack());
                        break;
                    } else if (!(cooldown ==0)) {
                        System.out.println(ANSI_RED + "super cool-down at " + cooldown + ANSI_RESET);
                        break;
                    } else {
                        System.out.println(ANSI_RED + "you do not have enough code-lines" + ANSI_RESET);
                        break;
                    }
                }
                if (attack.equals("normal")) {
                    if (player.getCodeLines() - player.normalAttack() > 0){
                        cooldown--;
                        enemy.setHealth(enemy.getHealth() - player.normalAttack());
                        System.out.println(ANSI_YELLOW + "you attacked with code block \n" + enemy.getTitle() + " lost  " + player.normalAttack() + " health \nthey have " + enemy.getHealth() + " remaining" + ANSI_RESET);
                        player.setCodeLines(player.getCodeLines() - player.normalAttack());
                        break;
                    } else {
                        System.out.println(ANSI_RED + "you do not have enough code-lines" + ANSI_RESET);
                        break;
                    }
                }
                System.out.println("command not valid");
            }
        }
        stopMusic();
        if (player.getSanity() > 0 && player.getHunger() > 0 && player.getEmployability() > 0) {
            victoryMusic();
            System.out.println(ANSI_RED + "You won!" + ANSI_RESET);
            battleReward();
            Thread.sleep(5000);
            stopMusic();
            enemy.setHealth(storeEnemyHealth);
        }
        backgroundMusic();
    }

    /**
     * saves game
     */
    public void save() throws IOException {
        //save game
        System.out.println("Saving game...");
        String saveFile = "resources/save.json";
        BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile));
        Gson gson = new Gson();
        bw.write(gson.toJson(player));
        bw.close();
        System.out.println("Game saved!");
    }

    /**
     * loads game
     */
    public void load() throws IOException {
        //load game
        System.out.println("Loading game...");
        String saveFile = "resources/save.json";
        BufferedReader br = new BufferedReader(new FileReader(saveFile));
        Gson gson = new Gson();
        player = gson.fromJson(br, Player.class);
        br.close();
        System.out.println("Game loaded!");
    }

    /**
     * adds one code-line to player inventory
     */
    public void code() {
        System.out.println(ANSI_RED + "you gained 1 code-line" + ANSI_RESET);
        player.setCodeLines(player.getCodeLines() + 1);
    }

    /**
     * display player stats
     */
    public void more() {
        System.out.println("Player Stats: \n" +
                "Hunger = " + player.getHunger() + "\n" +
                "Sanity = " + player.getSanity() + "\n" +
                "Score = " + player.getScore() + "\n" +
                "Employability = " + player.getEmployability() + "\n" +
                "code-lines = " + player.getCodeLines() + "\n" +
                "Room = " + player.getRoom().getName() + "\n"
        );
    }

    /**
     * display helpful commands
     */
    public void help() {
        System.out.println("Game Description:\n" + Script.getBasicInfo() + "\n");
        System.out.println(
                "|ACTION       | TYPE             | \n" +
                        "|Travel       | go N or go North | \n" +
                        "|Quit         | quit             | \n" +
                        "|Player Stats | More             | \n" +
                        "|Save Game    | save             | \n" +
                        "|Load Game    | load             | \n" +
                        "|Stop Music   | stop music       | \n" +
                        "|Start Music  | start music      | \n"
        );
    }


    /**
     * display player inventory
     */
    public void Inventory() throws IOException {
        System.out.println("ITEM:   QUANTITY");
        for (String m : player.getInventory().keySet()) {
            System.out.printf("%s:   %d \n", m, player.getInventory().get(m));
        }
        for (String m : player.getInventory().keySet()) {
            System.out.printf("> use %s    ", m);
        }
        System.out.print("> Exit \n");
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = br.readLine().toLowerCase();
            String[] commands = command.split(" ");
            if (commands[0].equals("use")) {
                if (command.equals("use jerky")) {
                    if (player.getInventory().get("Jerky") >= 1) {
                        System.out.println(ANSI_RED + "You used 1 jerky" + ANSI_RESET);
                        player.setHunger(player.getHunger() + 10);
                        player.getInventory().put("Jerky", player.getInventory().get("Jerky") - 1);
                    } else {
                        System.out.println("You don't have enough");
                    }
                    break;
                }
                if (command.equals("use chips")) {
                    if (player.getInventory().get("Chips") >= 1) {
                        System.out.println(ANSI_RED + "You used 1 chips" + ANSI_RESET);
                        player.setHunger(player.getHunger() + 10);
                        player.getInventory().put("Chips", player.getInventory().get("Chips") - 1);
                    } else {
                        System.out.println("You don't have enough");
                    }
                    break;
                }
                if (command.equals("use candy bar")) {
                    if (player.getInventory().get("Candy Bar") >= 1) {
                        System.out.println(ANSI_RED + "You used 1 candy bar" + ANSI_RESET);
                        player.setHunger(player.getHunger() + 5);
                        player.getInventory().put("Candy Bar", player.getInventory().get("Candy Bar") - 1);
                    } else {
                        System.out.println("You don't have enough");
                    }
                    break;
                }
            }
            if (command.equals("exit")) {
                break;
            }
            System.out.println("command not valid");
        }
    }

    /**
     * vending machine interface
     */
    public void accessVendingMachine() throws IOException {
        while (true) {
            System.out.println(Script.getPlayerAtVendingMachine());
            System.out.println("> Buy Jerky    > Buy Chips    > Buy Candy Bar > quit");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String choice = br.readLine().toLowerCase();
            if ("buy jerky".equals(choice)) {
                if (player.getCodeLines() >= 650) {
                    player.getInventory().put("jerky", player.getInventory().get("jerky") + 1);
                    player.setCodeLines(player.getCodeLines() - 650);
                    System.out.println(ANSI_RED + "You gained 1 jerky" + ANSI_RESET);
                } else {
                    System.out.println("you do not have enough code-lines");
                }
                continue;
            }
            if ("buy chips".equals(choice)) {
                if (player.getCodeLines() >= 500) {
                    player.getInventory().put("Chips", player.getInventory().get("Chips") + 1);
                    player.setCodeLines(player.getCodeLines() - 500);
                    System.out.println(ANSI_RED + "You gained 1 Chip" + ANSI_RESET);
                } else {
                    System.out.println("you do not have enough code-lines");
                }
                continue;
            }
            if ("buy candy bar".equals(choice)) {
                if (player.getCodeLines() >= 200) {
                    player.getInventory().put("Candy Bar", player.getInventory().get("Candy Bar") + 1);
                    player.setCodeLines(player.getCodeLines() - 200);
                    System.out.println(ANSI_RED + "You gained 1 Candy Bar" + ANSI_RESET);
                } else {
                    System.out.println("you do not have enough code-lines");
                }
                continue;
            }
            if ("q".equals(choice) || "quit".equals(choice)) {
                break;
            }
            System.out.println("command not valid");
        }
    }

    /**
     * brew coffee in coffee room
     */
    public void brewCoffee() throws IOException {
        player.getInventory().put("Coffee", player.getInventory().get("Coffee") + 1);
        System.out.println(ANSI_RED + "you gained 1 coffee" + ANSI_RESET);
        coffeeCount++;
         while (true) {
            System.out.println("What would you like to do? \n> Brew Again    > Quit \n>");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String choice = br.readLine().toLowerCase();
            if (choice.equals("brew again")){
                if (coffeeCount < 3) {
                    player.getInventory().put("Coffee", player.getInventory().get("Coffee") + 1);
                    System.out.println(ANSI_RED + "you gained 1 coffee" + ANSI_RESET);
                    coffeeCount++;
                    continue;
                } else {
                    System.out.println( ANSI_RED +"coffee machine needs to cool down" + ANSI_RESET);
                    continue;
                }
            }
            if (choice.equals("quit")){
                break;
            }
            System.out.println("Command not valid");
        }
    }
}
