package com.twitter.game.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.twitter.game.model.*;
import org.bson.Document;

import javax.sound.sampled.*;
import java.io.*;
import java.util.*;

public class Game {
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private final String[] wordsForNorth = {"north", "n"};
    private final String[] wordsForSouth = {"south", "s"};
    private final String[] wordsForWest = {"west", "w"};
    private final String[] wordsForEast = {"east", "e"};
    private final String[] defaultCommands = {"Inventory", "View Map", "More", "Save", "Load", "Help"};
    private final String[] workstationCommands = {"Code", "Read Book", "Go North", "Go East", "Go West"};
    private final String[] breakRoomCommands = {"Access Vending Machine", "Go South", "Go West", "Go East"};
    private final String[] coffeeBarCommands = {"Brew Coffee","Go North", "Go East"};
    private final String[] emptyWorkstationCommands = {"Search Desk", "Go North", "Go West"};
    private final String[] CEOCommands = {"Go South", "Go East"};
    private Player player;
    private final List<Room> gameMap;
    private final ArrayList<Enemy> enemyArray;
    private final Map<String, Integer> inventory;
    private boolean gameOver = false;
    private int coffeeCount = 0;
    private String dataBaseUserName;
    private Music music;

    public Game(Player player) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        //start background music
        backgroundMusic();

        //load in items
        InputStream in = getClass().getResourceAsStream("/items.json");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(in));
        Gson gson2 = new Gson();
        inventory = new HashMap<>();
        List<String> gameItems = gson2.fromJson(br2, new TypeToken<List<String>>() {
        }.getType());
        br2.close();
        for (String s : gameItems) {
            inventory.put(s, 0);
        }
        player.setInventory(inventory);

        //load in rooms
        InputStream in2 = getClass().getResourceAsStream("/rooms.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(in2));
        Gson gson = new Gson();
        gameMap = gson.fromJson(br, new TypeToken<List<Room>>() {
        }.getType());
        br.close();
        gameMap.get(1).setDescription(Script.getPlayerInBreakRoom());
        gameMap.get(2).setDescription(Script.getPlayerFindsAbandonedWorkstation());
        gameMap.get(3).setDescription(Script.getPlayerInCoffeeBar());
        gameMap.get(4).setDescription(Script.getPlayerFindsAbandonedWorkstation());
        gameMap.get(5).setDescription(Script.getPlayerInCEORoom());

        this.player = player;
        player.setRoom(gameMap.get(0));

        //load in enimies
        InputStream in3 = getClass().getResourceAsStream("/enemies.json");
        BufferedReader br3 = new BufferedReader(new InputStreamReader(in3));
        Gson gson3 = new Gson();
        enemyArray = gson3.fromJson(br3, new TypeToken<List<Enemy>>() {
        }.getType());
        br3.close();
    }

    /**
     * Generates background music
     */
    public void backgroundMusic() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        music = new Music();
        music.backgroundMusic();
    }

    /**
     * Generates battle music
     */
    public void battleMusic() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        stopMusic();
        music.battleMusic();
    }

    /**
     * Generates Victory music
     */
    public void victoryMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        music.victoryMusic();
    }

    /**
     * stops playing the current clip of music
     */
    public void stopMusic() {
        music.stopMusic();
    }

    public void gameOverMusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        music.gameOverMusic();
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
        System.out.println(gameIntroLogo);
        System.out.println(logoSubTitle);
        Thread.sleep(7000);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(Script.getFirstScene());
        System.out.println(Script.getPlayerRequest());
    }

    /**
     * Generates game over screen
     */
    public void gameOver() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        //display game over logo
        gameOver = true;
        stopMusic();
        gameOverMusic();
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
        String gameOverLogoSubtitleEmp = ANSI_RED + "\tYou lost all of your employability ..." +
                "you were fired on the spot" + ANSI_RESET;
        String gameOverLogoSubtitleHunger = ANSI_RED + "\tYou starved to death ..." +
                "upon your dead body a pink slip was placed ceremoniously by a shadowy figure, whom proceeded to " +
                "fired you on the spot" + ANSI_RESET;
        String gameOverLogoSubtitleSanity = ANSI_RED + "\tYou went insane ..." +
                "after that you were fired on the spot" + ANSI_RESET;
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
        stopMusic();
    }

    /**
     * Generates main player interface
     */
    public void commandInput() throws IOException, UnsupportedAudioFileException, LineUnavailableException,
            InterruptedException {
        //get commands from player
        String command;
        while (player.getSanity() > 0 && player.getHunger() > 0 && player.getEmployability() > 0 && !gameOver) {
            renderUserInterface();
            System.out.println(Script.getPlayerRequest());

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
        if (currentRoom.equals("CEO")) {
            commands = CEOCommands;
        }
        if (currentRoom.equals("Empty Workstation-1")) {
            commands = emptyWorkstationCommands;
        }
        if (currentRoom.equals("Empty Workstation-2")) {
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
            case "search":
                if (command.equals("search desk")) {
                    if (player.getRoom().getName().equals("Empty Workstation-1") || player.getRoom().getName().equals("Empty Workstation-2")){
                        searchDesk();
                    }
                } else {
                    System.out.println("command not valid");
                }
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
                    music.setMusic(false);
                } else {
                    System.out.println("command not valid");
                }
                break;
            case "start":
                if (command.equals("start music")) {
                    music.setMusic(true);
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
                System.out.println(ANSI_PURPLE + "command not valid" + ANSI_RESET);
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
            attackOrFlee(enemyArray.get(enemyIndex));
        }
    }

    /**
     * determine if player wants to run or fight
     */
    public void attackOrFlee(Enemy enemy) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
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
                    stopMusic();
                    backgroundMusic();
                    return;
                } else {
                    System.out.println("runaway unsuccessful");
                    fight(enemy);
                    break;
                }
            }
            if (command.equals("attack")) {
                fight(enemy);
                break;
            }
            System.out.println("command not valid");
        }
    }

    /**
     * give player a random reward
     */
    public void randomReward(){
        List<String> items = new ArrayList<>(inventory.keySet());
        Random rand2 = new Random();
        String randomItem = items.get(rand2.nextInt(items.size()));
        player.getInventory().put(randomItem, player.getInventory().get(randomItem) + 1);
        System.out.println(ANSI_RED + "Congratulation you gained one " + randomItem + ANSI_RESET);
    }

    /**
     * player battle interface
     */
    public void fight(Enemy enemy) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        int storeEnemyHealth = enemy.getHealth();
        Random rand2 = new Random();
        int cooldown = 0;
        battle:
        while (enemy.getHealth() >= 1 && player.getSanity() >= 1) {
            int determineAttackType = rand2.nextInt(10);
            if (determineAttackType >= 7) {
                player.setSanity(player.getSanity() - enemy.getSuperAttackDmg());
                System.out.println(enemy.getTitle() + " hit you with " + enemy.getSuperAttack() + ANSI_RED + "\nyou lost " + enemy.getSuperAttackDmg() + " sanity\nyou have " + player.getSanity() + " sanity remaining" + ANSI_RESET);
            } else if (determineAttackType == 0) {
                System.out.println(enemy.getTitle() + " missed an attack. you took no damage!");
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
            randomReward();
            Thread.sleep(5000);
            stopMusic();
            enemy.setHealth(storeEnemyHealth);
        }
        backgroundMusic();
    }


    /**
     * saves game if db connection is good
     */
    public void save() throws IOException {
        try {
            System.out.println("do you have an account?");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = br.readLine().toLowerCase();
            if (command.equals("yes")){
                dataBaseUserName = loginUser();
            }
            if (dataBaseUserName == null || dataBaseUserName.equals("") || dataBaseUserName.equals("login unsuccessful")){
                dataBaseUserName = getUserName();
            }
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://The-dream-team:qazwsxQAZWSX123@cluster0.3982aih.mongodb.net/?retryWrites=true&w=majority"));
            MongoDatabase database = mongoClient.getDatabase("Users");
            MongoCollection<Document> collection = database.getCollection("GameStats");
            Document user = new Document();
            Gson gson = new Gson();
            user.append("username", dataBaseUserName.toLowerCase());
            user.append("stats", gson.toJson(player));
            collection.insertOne(user);
            mongoClient.close();
            System.out.println("save successful");
        } catch (Exception e){
            System.out.println("cannot connect to database");
        }
    }

    String loginUser() throws IOException {
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter username");
        String loginUserName = br1.readLine();
        List<Object> results = checkIfUserNameExists(loginUserName);
        if (results.size() > 0){
            String[] userInfo = results.get(0).toString().split(",");
            if (Objects.equals(userInfo[1].replace("}", ""), " username=" + loginUserName)){
                System.out.println("login Successful");
                return loginUserName;
            }
        }
        return "login unsuccessful";
    }

    /**
     * grab username
     */
    private String getUserName() throws IOException {
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter desired Username (must be greater than 4 characters and less than 15 characters)");
            String username = br.readLine();
            if (username.length() > 4 && username.length() < 15) {
                List<Object> userNameArray = checkIfUserNameExists(username);
                if (userNameArray.size() == 0) {
                    return username;
                } else {
                    System.out.println("username is taken");

                }
            }
            else {
                System.out.println("UserName length is invalid");
            }
        }
    }

    /**
     * check if username is in db
     */
    private List<Object> checkIfUserNameExists(String searchingUsername){
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://The-dream-team:qazwsxQAZWSX123@cluster0.3982aih.mongodb.net/?retryWrites=true&w=majority"));
        MongoDatabase database = mongoClient.getDatabase("Users");
        MongoCollection<Document> collection = database.getCollection("GameStats");
        Document query = new Document("username", searchingUsername.toLowerCase());
        List<Object> results = new ArrayList<>();
        collection.find(query).into(results);
        mongoClient.close();
        return results;
    }

    /**
     * loads game if db connection is good
     */
    public void load() throws IOException {
        try {
            if (dataBaseUserName == null || dataBaseUserName.equals("")){
                dataBaseUserName = loginUser();
            }
            List<Object> checkIfUserExists = checkIfUserNameExists(dataBaseUserName);
            if (checkIfUserExists.size() > 0){
                MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://The-dream-team:qazwsxQAZWSX123@cluster0.3982aih.mongodb.net/?retryWrites=true&w=majority"));
                MongoDatabase database = mongoClient.getDatabase("Users");
                MongoCollection<Document> collection = database.getCollection("GameStats");
                Document query = new Document("username", dataBaseUserName.toLowerCase());
                String s = (String) collection.find(query).first().get("stats");
                mongoClient.close();
                Gson gson = new Gson();
                player = gson.fromJson(s, Player.class);
            } else {
                System.out.println("account not found");
            }
        } catch (Exception e) {
            System.out.println("cannot connect to database");
        }
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

    public void searchDesk(){
        Random rand = new Random();
        int successfulSearch = rand.nextInt(10);
        if (successfulSearch >= 7){
            randomReward();
        } else {
            System.out.println(ANSI_RED + "You tried to search and desk and were caught" + ANSI_RESET);
            player.setEmployability(player.getEmployability() - 20);
            System.out.println(ANSI_RED + "You lost 20 Employability" + ANSI_RESET);
        }
    }

}
