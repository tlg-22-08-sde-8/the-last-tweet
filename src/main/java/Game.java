public class Game {

//    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
//    private static final String ANSI_GREEN = "\u001B[32m";
//    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
//    private static final String ANSI_PURPLE = "\u001B[35m";
//    private static final String ANSI_CYAN = "\u001B[36m";
//    private static final String ANSI_WHITE = "\u001B[37m";
//    private static final String ANSI_BLACK = "\u001B[30m";

    public void gameIntro() {
        //create game logo and intro story lines
        String gameLogo = ANSI_BLUE +
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
        String storyIntro = "\nIt is October 27th.You wake up at your desk, groggy from an attack.You look around you. There are pink slips everywhere. Fires raging." + "\n" +
                "Employees crying.Elon Musk has acquired Twitter.Half of the Twitter workforce has been laid off.You are one of the survivors. \n" +
                "You look at your desk. On your laptop, you have X lines of code. \n" +
                "[What would you like to do? \n" +
                "> Code     > Program    > Venture Out         > More ?\n" +
                "(or enter q to quit)";

        //display intro to user
        System.out.println(gameLogo);
        System.out.println(logoSubTitle);
        System.out.println(storyIntro);

    }
}
