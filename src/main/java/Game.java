import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    private final Player player = new Player();
    private final List<Room> gameMap;
    private final String[] wordsForNorth = {"north", "n"};
    private final String[] wordsForSouth = {"south", "s"};
    private final String[] wordsForWest = {"west", "w"};
    private final String[] wordsForEast = {"east", "e"};

    public Game() {
        //array of rooms and set player location to workstation
        gameMap = new ArrayList<>();
        gameMap.add(new Room("WorkStation", "You are standing in a bleak, cold room that smells like feet and despair. You are exhausted, but alive. You sit back down at your work computer, pondering your next steps. ",1, -1,3, 4));
        gameMap.add(new Room("Break Room", "Huh weird this room is empty. ", -1, -1, 2, 5));
        gameMap.add(new Room("Meeting Room-1", "The room is composed of brilliant white marble. The air smells of citrus. A heavenly glow eliminates from the coffee bar, like the open arms of an angel. The Kuerig machine is running. A lone laptop is in the room. ", -1, 3, -1, 1));
        gameMap.add(new Room("Coffee Bar", "A nasty, dark cell", 2, -1, -1, 0));
        gameMap.add(new Room("Empty workstation", "This workstation still has pictures of a recently fired employee and their family", 5, -1, 0, -1));
        gameMap.add(new Room("Meeting Room-2", "The room is composed of brilliant white marble. The air smells of citrus. A heavenly glow eliminates from the coffee bar, like the open arms of an angel. The Kuerig machine is running. A lone laptop is in the room. ", -1, 4, 1, -1));
        player.setRoom(gameMap.get(0));
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
        String gameIntroLogoSubtitle = ANSI_BLUE + "\t\t\t\t\t\t\t\tThe Last Tweet: A Twitter Survival Game" + ANSI_RESET;
        String storyIntro =
                "\nIt is October 27th.You wake up at your desk, groggy from an attack.You look around you. There are pink slips everywhere. Fires raging." + "\n" +
                "Employees crying.Elon Musk has acquired Twitter.Half of the Twitter workforce has been laid off.You are one of the survivors. \n" +
                "You look at your desk. On your laptop, you have X lines of code. \n" +
                "What would you like to do? \n" +
                "> Code   > Program   > Venture Out   > More  > Quit";

        //display intro to user
        System.out.println(gameIntroLogo);
        System.out.println(gameIntroLogoSubtitle);
        System.out.println(storyIntro);
        System.out.print("> ");

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
        if (go != -1){
            player.setRoom(gameMap.get(go));
            System.out.println(gameMap.get(go).getName() + " " + gameMap.get(go).getDescription());
        }
        else {
            System.out.println("looks like this way is blocked");
//            System.out.println(gameMap.get(0).getName() + " " + gameMap.get(0).getDescription());
        }
    }

    public void renderUserInterface(){
        String userStats =
                "==============================================================================================================================\n" +
                "  Location = " + player.getRoom().getName() + "                    hunger = 0   employability = 0   sanity = 0                               SDE-1 \n" +
                "==============================================================================================================================";
        System.out.println(userStats);
    }


    public void commandInput() throws IOException {
        renderUserInterface();
        //get commands from player
        gameMenu:
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.print("> ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = br.readLine().strip();
            if (Objects.equals(command, "venture out")) {
                ventureOut();
            }
            else {
                break gameMenu;
            }
        }

    }


    public void gameOver(){
        //create game over logo
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
        String gameOverLogoSubtitle = ANSI_RED+ "\tYou lost your employability .....you were fired on the spot" + ANSI_RESET;

        //display game over to user
        System.out.println(gameOverLogo);
        System.out.println(gameOverLogoSubtitle);
    }
}
