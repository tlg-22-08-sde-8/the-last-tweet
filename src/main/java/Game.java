import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class Game {
    //    private static final String ANSI_PURPLE = "\u001B[35m";
//    private static final String ANSI_CYAN = "\u001B[36m";
//    private static final String ANSI_WHITE = "\u001B[37m";
//    private static final String ANSI_BLACK = "\u001B[30m";
//    private static final String ANSI_GREEN = "\u001B[32m";
//    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private Player player;
    private Enemy enemy;
    private final List<Room> gameMap;
    private final ArrayList<Enemy> enemyArray;
    private final String[] wordsForNorth = {"north", "n"};
    private final String[] wordsForSouth = {"south", "s"};
    private final String[] wordsForWest = {"west", "w"};
    private final String[] wordsForEast = {"east", "e"};
    private final String[] workstationCommands = {"Code", "Venture out", "More"};
    private final String[] breakRoomCommands = {"Access Vending Machine", "Venture Out", "More"};
    private final String[] coffeeBarCommands = {"Make Coffee", "Venture out", "More"};
    private final String[] emptyWorkstationCommands = {"Search Desk", "Venture out", "More"};
    private final String[] meetingRoomCommands = {"Negotiate with Manager", "Venture out", "More"};

    public Game(Player player) {
        //array of rooms and set player location to workstation
        gameMap = new ArrayList<>();
        gameMap.add(new Room("WorkStation", "You are standing in a bleak, cold room that smells like feet and despair. You are exhausted, but alive. You sit back down at your work computer, pondering your next steps. ", 1, -1, 3, 4));
        gameMap.add(new Room("Break Room", "Huh weird this room is empty. ", -1, -1, 2, 5));
        gameMap.add(new Room("Meeting Room-1", "The room is composed of brilliant white marble. The air smells of citrus. A heavenly glow eliminates from the coffee bar, like the open arms of an angel. The Kuerig machine is running. A lone laptop is in the room. ", -1, 3, -1, 1));
        gameMap.add(new Room("Coffee Bar", "A nasty, dark cell", 2, -1, -1, 0));
        gameMap.add(new Room("Empty workstation", "This workstation still has pictures of a recently fired employee and their family", 5, -1, 0, -1));
        gameMap.add(new Room("Meeting Room-2", "The room is composed of brilliant white marble. The air smells of citrus. A heavenly glow eliminates from the coffee bar, like the open arms of an angel. The Kuerig machine is running. A lone laptop is in the room. ", -1, 4, 1, -1));
        //set player room
        this.player = player;
        player.setRoom(gameMap.get(0));
        //create enemies
        enemyArray = new ArrayList<Enemy>();
        enemyArray.add(new Enemy(10, "jrDev", "ask-for-help", 5, "merge-with-main", 10));
        enemyArray.add(new Enemy(10, "SrDev", "negative-feedback", 5, "unreasonable-deadline", 10));
        enemyArray.add(new Enemy(10, "Product-Manager", "promised-feature-to-client", 5, "set-secret-deadline", 10));
    }

    public void gameIntro() throws IOException {
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
                "                                                                                                         "
                + ANSI_RESET;
        String logoSubTitle = ANSI_BLUE + "\t\t\t\t\t\t\t\tThe Last Tweet: A Twitter Survival Game" + ANSI_RESET;
        String storyIntro = "You look at your desk. On your laptop, you have X lines of code.";
        //display intro to user
        System.out.println(gameIntroLogo);
        System.out.println(logoSubTitle);
        System.out.println(Script.getFirstScene());
        System.out.println(storyIntro);
    }


    public void ventureOut() throws IOException {
        //move player to different rooms
        System.out.println("where would you like to go?");
        System.out.print("> ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        String[] checkDirection = command.split(" ");
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
            System.out.println(ANSI_RED + "You traveled " + direction + ANSI_RESET + "\n" + gameMap.get(go).getName() + "\n" + gameMap.get(go).getDescription());
        } else {
            System.out.println("looks like this way is blocked");
//            System.out.println(gameMap.get(0).getName() + " " + gameMap.get(0).getDescription());
        }
    }

    public void renderUserInterface() {
        //display user stats
        String userStats =
                "==============================================================================================================================\n" +
                        "  Location = " + player.getRoom().getName() + "                      hunger = " + player.getHunger() + "   employability = " + player.getEmployability() + "  sanity = " + player.getSanity() + "                            SDE-1 \n" +
                        "==============================================================================================================================";
        System.out.println(userStats);
    }

    public void commandInput() throws IOException {
        //get commands from player
        while (true) {
            renderUserInterface();
            System.out.println("\nWhat would you like to do?");
            String[] determineAvailableCommands = determineAvailableCommands(player.getRoom().getName());
            for (String c : determineAvailableCommands) {
                System.out.print("> " + c + "    ");
            }
            System.out.print("\n> ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = br.readLine().strip().toLowerCase();
            if (Objects.equals(command, "venture out")) {
                ventureOut();
            }
            if (Objects.equals(command, "save")) {
                save();
            }
            if (Objects.equals(command, "load")) {
                load();
            } else {
                break;
            }
        }

    }

    public void save() throws IOException {
        //save game
        System.out.println("Saving game...");
        String saveFile = "save.json";
        BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile));
        Gson gson = new Gson();
        bw.write(gson.toJson(player));
        bw.close();
        System.out.println("Game saved!");
    }

    public void load() throws IOException   {
        //load game
        System.out.println("Loading game...");
        String saveFile = "save.json";
        BufferedReader br = new BufferedReader(new FileReader(saveFile));
        Gson gson = new Gson();
        player = gson.fromJson(br, Player.class);
        System.out.println("Game loaded!");
    }

    private String[] determineAvailableCommands(String currentRoom) {
        //check if command can be completed in room
        String[] commands = new String[0];
        if (Objects.equals(currentRoom, "WorkStation")) {
            commands = workstationCommands;
        }
        if (Objects.equals(currentRoom, "Break Room")) {
            commands = breakRoomCommands;
        }
        if (Objects.equals(currentRoom, "Coffee Bar")) {
            commands = coffeeBarCommands;
        }
        if (Objects.equals(currentRoom, "Meeting Room-1")) {
            commands = meetingRoomCommands;
        }
        if (Objects.equals(currentRoom, "Meeting Room-2")) {
            commands = meetingRoomCommands;
        }
        if (Objects.equals(currentRoom, "Empty workstation")) {
            commands = emptyWorkstationCommands;
        }
        return commands;
    }

    public void determineBattle() throws IOException {
        Random rand = new Random();
        int battleNum = rand.nextInt(10);
        if (battleNum >= 7) {
            return;
        } else {
            Random rand1 = new Random();
            int enemyIndex = rand1.nextInt(enemyArray.size() - 1);
            battle(enemyArray.get(enemyIndex));
        }
    }


    public void battle(Enemy enemy) throws IOException {
        System.out.println(ANSI_RED + "You are starting a battle" + ANSI_RESET);
        while (true) {
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
            if (command.equals("attack")){
                break;
            }
            System.out.println("command not valid");
        }
        Random rand2 = new Random();
        int determineAttackType = rand2.nextInt(10);
        while (enemy.getHealth() > 0) {
            if (determineAttackType >= 7) {
                System.out.println(enemy.getTitle() + " hit you with " + enemy.getSuperAttack() + ANSI_RED + "\nyou lost " + enemy.getSuperAttackDmg() + ANSI_RESET);
                player.setSanity(player.getSanity() - enemy.getSuperAttackDmg());
            } else {
                System.out.println(enemy.getTitle() + " hit you with " + enemy.getNormalAttack() + ANSI_RED + "\nyou lost " + enemy.getNormalAttackDmg() + ANSI_RESET);
                player.setSanity(player.getSanity() - enemy.getNormalAttackDmg());
            }
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("Which attack would you like to use \n> normal > super ");
                String attack = br1.readLine().toLowerCase();
                if (attack.equals("normal")) {
                    System.out.println("you attacked with x \n" + enemy.getTitle() + " lost x health");
                    enemy.setHealth(enemy.getHealth() - 3);
                    break;
                }
                if (attack.equals("super")) {
                    System.out.println("you attacked with x \n" + enemy.getTitle() + " lost x health");
                    enemy.setHealth(enemy.getHealth() - 7);
                    break;
                }
                System.out.println("command not valid");
            }

        }
        System.out.println(ANSI_RED + "You won!" + ANSI_RESET);
    }


    public void gameOver() {
        //display game over logo
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
                "                                                     ░                 "
                + ANSI_RESET;
        String gameOverLogoSubtitle = ANSI_RED + "\tYou lost your employability .....you were fired on the spot" + ANSI_RESET;

        //display game over to user
        System.out.println(gameOverLogo);
        System.out.println(gameOverLogoSubtitle);
    }


}
