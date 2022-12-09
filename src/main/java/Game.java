import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        String logoSubTitle = ANSI_BLUE + "\t\t\t\t\t\t\t\tThe Last Tweet: A Twitter Survival Game" + ANSI_RESET;
        String storyIntro = "You look at your desk. On your laptop, you have X lines of code. \n" +
                "What would you like to do? \n" +
                "> Code     > Program    > Venture Out         > More ?\n" +
                "(or enter q to quit)";

        //display intro to user
        System.out.println(gameLogo);
        System.out.println(logoSubTitle);
        System.out.println(Script.getFirstScene());
        System.out.println(storyIntro);
        System.out.print("> ");

    }

    public void ventureOut() throws IOException {
        //move player to different rooms
        System.out.println("where would you like to go?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        int go;
        Room currentRoom = player.getRoom();
        switch (command) {
            case "go north":
                go = currentRoom.getNorth();
                break;
            case "go south":
                go = currentRoom.getSouth();
                break;
            case "go west":
                go = currentRoom.getWest();
                break;
            case "go east":
                go = currentRoom.getEast();
                break;
            default:
                go = -1;
                break;
        }
        if (go != -1){
            player.setRoom(gameMap.get(go));
            System.out.println(gameMap.get(go).getName() + " " + gameMap.get(go).getDescription());
        }
        else {
            System.out.println("hmm you cant go that way");
            System.out.println(gameMap.get(0).getName() + " " + gameMap.get(0).getDescription());
        }
    }

    public void commandInput() throws IOException {
        //get commands from player
        gameMenu:
        while (true) {
            System.out.println("What would you like to do?");
            System.out.print("> ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = br.readLine();
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
