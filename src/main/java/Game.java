import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private final Player player;
    private Enemy enemy;
    private final List<Room> gameMap;
    private final ArrayList<Enemy> enemyArray;
    private final String[] wordsForNorth = {"north", "n"};
    private final String[] wordsForSouth = {"south", "s"};
    private final String[] wordsForWest = {"west", "w"};
    private final String[] wordsForEast = {"east", "e"};
    private final String[] workstationCommands = {"Code", "Venture out", "More", "quit", "help"};
    private final String[] breakRoomCommands = {"Venture Out", "More", "quit", "help"};
    private final String[] coffeeBarCommands = {"Venture out", "More", "quit", "help"};
    private final String[] emptyWorkstationCommands = {"Venture out", "More", "quit", "help"};
    private final String[] meetingRoomCommands = {"Venture out", "More", "quit", "help"};

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
        enemyArray = new ArrayList<>();
        enemyArray.add(new Enemy(10, "jrDev", "ask-for-help", 5, "merge-with-main", 10));
        enemyArray.add(new Enemy(20, "SrDev", "negative-feedback", 5, "unreasonable-deadline", 10));
        enemyArray.add(new Enemy(30, "Product-Manager", "promised-feature-to-client", 5, "set-secret-deadline", 10));
    }

    public void gameIntro() {
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
        while (true) {
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
                System.out.println(go);
                go = currentRoom.getNorth();
                System.out.println(go);
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
                player.setHunger(player.getHunger() - 5);
                break;
            } else {
                System.out.println("looks like this way is blocked");
            }
        }
    }

    public void code() {
        System.out.println(ANSI_RED + "you gained 1 code-line" + ANSI_RESET);
        player.setCodeLines(player.getCodeLines() + 1);
    }

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

    public void help() {
        System.out.println("Game Description:\n" + Script.getBasicInfo() + "\n");
        System.out.println("Commands: \n" +
                "Travel through the game: go {direction} ex: go east,  *each time you travel you have a random chance for a battle*\n" +
                "Quit: quit [enter]\n" +
                "To gain code-line: select code option at workstation or defeat enemies\n"
        );
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
        while (player.getSanity() > 0 || player.getHunger() > 0 || player.getEmployability() > 0) {
            renderUserInterface();
            System.out.println("\nWhat would you like to do?");
            String[] determineAvailableCommands = determineAvailableCommands(player.getRoom().getName());
            for (String c : determineAvailableCommands) {
                System.out.print("> " + c + "    ");
            }
            System.out.print("\n> ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = br.readLine().strip().toLowerCase();
            //travel
            if (command.equals("venture out")) {
                ventureOut();
            }
            //gain one line of code
            if (command.equals("code")) {
                if (player.getRoom().getName().equals("WorkStation")) {
                    code();
                    continue;
                } else {
                    System.out.println("command not valid in this location");
                }
            }
            //display stats and instructions
            if (command.equals("more")) {
                more();
                continue;
            }
            //help
            if (command.equals("help")) {
                help();
                continue;
            }
            //quit game
            if (Objects.equals(command, "quit")) {
                gameOver();
                return;
            }
            System.out.println("command not valid");
        }

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
        if (!(battleNum >= 7)) {
            Random rand1 = new Random();
            int enemyIndex = rand1.nextInt(enemyArray.size() - 1);
            battle(enemyArray.get(enemyIndex));
        }
    }


    public void battle(Enemy enemy) throws IOException {
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
        Random rand2 = new Random();
        int determineAttackType = rand2.nextInt(10);
        while (enemy.getHealth() > 0) {
            if (determineAttackType >= 7) {
                player.setSanity(player.getSanity() - enemy.getSuperAttackDmg());
                System.out.println(enemy.getTitle() + " hit you with " + enemy.getSuperAttack() + ANSI_RED + "\nyou lost " + enemy.getSuperAttackDmg() + " sanity\n you have " + player.getSanity() + " remaining" + ANSI_RESET);
            } else {
                System.out.println(enemy.getTitle() + " hit you with " + enemy.getNormalAttack() + ANSI_RED + "\nyou lost " + enemy.getNormalAttackDmg() + " sanity\n you have " + player.getSanity() + " remaining" + ANSI_RESET);
                player.setSanity(player.getSanity() - enemy.getNormalAttackDmg());
            }
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("Which attack would you like to use \n> normal > super ");
                String attack = br1.readLine().toLowerCase();
                if (attack.equals("normal")) {
                    enemy.setHealth(enemy.getHealth() - player.normalAttack());
                    System.out.println(ANSI_YELLOW + "you attacked with code block \n" + enemy.getTitle() + " lost  " + player.normalAttack() + " health \n they have " + enemy.getHealth() + "remaining" + ANSI_RESET);
                    break;
                }
                if (attack.equals("super")) {
                    enemy.setHealth(enemy.getHealth() - player.superAttack());
                    System.out.println(ANSI_YELLOW + "you attacked with a class generation \n" + enemy.getTitle() + " lost " + player.superAttack() + " health" + ANSI_RESET);
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
