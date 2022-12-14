public final class Script {


    // Contains the basic information script about the game
    private static final String basicInfo = "A Text-Based Parody Survival Horror Adventure Game about the Great " +
            "Twitter Purge of 2022! You navigate and interact with the game by typing in the commands to the " +
            "console below." +
            "Type 'help' for a list of commands. \n" +
            "Co-Developed by: Brenden King, Patrick Lauer, and Mohamed Omar \n" +
            "Version: 0.0.1";
    private static final String firstScene = "\n\nIt is October 27th. \n" +
            "You wake up at your desk, groggy from an attack. You look around you. There are pink slips everywhere. " +
            "Fires raging. Employees crying. \n" +
            "Elon Musk has acquired Twitter. Half of the Twitter workforce has been laid off. \n" +
            "You are one of the survivors. \n";

    private static final String playerRequest = "What do you do?";

    private static final String playerCodes = "You log into the computer using your Twitter credentials, then open" +
            "up an Development Environment. Your fingers start tapping away. \n" +
            "You’ve gained " /*+ Player.getCodeLines() */ + "lines of code. \n" +
            "You’ve lost 1 hunger. You have X hunger left. ";

    private static final String playerPrograms = "The follow programs are available for building: \n" +
            "\tMagic Keyboard (cost: 1000 lines of code) - Increases Attack in battle by +10 lines per code.  \n" +
            "\tMagic mouse (cost: 500 lines of code) - Increases Attack in battle by +5 lines of code. \n" +
            "\tDigital Assistant (cost: 10,000 lines of code) - auto generates code by +10 per action while you " +
            "are away from your workstation. \n" +
            "\n" +
            "To “program” an item into your inventory, press the corresponding number or “q” to go back to " +
            "your workstation.";

    private static final String playerBuildsProgram = "The following program +" +
            /* Store.getMagicKeyboard() +*/
            "has been built. \n" +
            "You have lost " + /*Player.getHunger() +*/ "hunger. You have X hunger left.\n";

    private static final String playerChoosesMore = "What would you like to know?";

    private static final String playerChecksStatus = "Status: \n" +
            "\tEmployment: " + /*Player.getCurrentEmployment() + */ "/" + /*Player.getMaxEmployment() + */ "\n" +
            "\t\tDon't let this reach 0 or it's game over!\n" +
            "\n" +
            "\tSanity: " + /*Player.getCurrentSanity() + */ "/" + /*Player.getMaxSanity() + */ "\n" +
            "\t\tIf 0, Employment will start depleting instead!\n" +
            "\n" +
            "\tHunger: " + /*Player.getCurrentHunger() + */ "/" + /*Player.getHunger() + */ "\n" +
            "\t\tIf 0, Sanity will start depleting instead!\n";

    private static final String playerChecksInventory = " Inventory: \n" +
            "\tLines of Code: " + /*Player.getCodeLines() +*/ "\n" +
            "\t\t Currency for programming and used in battle for fights. If you are low, you can't attack, so keep " +
            "this high! \n" +
            "\tCoffee: " + /*Player.getCoffeeSupply() +*/ "\n" +
            "\t\tEach coffee restores 5 sanity\n" +
            "\tFood: " + /*Player.getFoodSupply() +*/ "\n" +
            "\t\tEach food restores 5 of hunger meter\n";

    private static final String randomEncounter = "You ran into " /*+ Enemy.getEnemyType()*/;

    private static final String playerReturnsToWorkstation = "You are standing in a bleak, cold room that smells " +
            "like feet and despair. You are exhausted, but alive. You sit back down at your work computer, " +
            "pondering your next steps. \n" +
            "\n" +
            "You have " + /*Player.getCodeLines() +*/ " lines of code in inventory. ";

    private static final String playerInBreakRoom = "You feel uneasy...there is no presence in this room, as if " +
            "those who stayed behind were afraid to take breaks. In the corner, however, is some form of temporary " +
            "respite: an old clunky vending machine with a flickering blue light.";

    private static final String playerAtVendingMachine = "Food items availible: \n" +
            "Candy Bar (cost: 200 lines of code) - increases hunger by 5 \n" +
            "Chips (cost: 500 lines of code) - increases hunger by 10\n" +
            "Jerky (cost: 650 lines of code) - refill hunger to 15\n" +
            "The purchased item will be added to your inventory. press the corresponding number or “q” to go back.";

    private static final String playerInMeetingRoom = "This room looks like it belongs to a manager. " +
            "(recommended codelines: " +  /*Enemy.bossRecommended() + */ "/current codelines: " +
            /*Player.getCodeLines() +*/ "\n" +
            "\tWhat do you do?";

    private static final String PlayerFindsAbandonedWorkstation = "There is an abandoned workstation desk,possibly" +
            "belonging to a former employee that was just laid off hours ago. You ponder if there might be " +
            "useful supplies left behind within its innards, but I could seriously put my Employability in jeopardy " +
            "if I am caught!";

    private static final String playerSearchesDeskSuccessfully = "You stick your hand into the drawer of the desk." +
            "You feel something push back. \n" +
            "You gained " + /*Player.gainFood() +*/ "foods, and " + /*Player.getCoffee() +*/ " coffee.";

    private static final String playerSearchesDeskCaught = "You feel a presence behind you as you try to search the " +
            "desk. \n" + "" +
            "The product Manager shows up! \n" +
            "“What ARE YOU DOING?!” \n" +
            "Employability reduces to –5. ";

    private static final String playerSearchesDeskEmpty = "You stick your hand into the drawer of the desk. \n" +
            "Your hands feel only the desk itself push back against your fingers as you crawl your hand deeper! \n" +
            "“The desk is empty. You attained nothing.” \n";

    private static final String playerInCoffeeBar = "The room is composed of brilliant white marble. The air " +
            "smells of citrus. A heavenly glow eminates from the coffee bar, like the open arms of an angel. " +
            "The Kuerig machine is running. A lone laptop is in the room.";

    private static final String playerBrewsCoffee = "You put a coffee pod in. The Kuerig machine purrs. " +
            "The smell of earth fills the room.  \n" +
            "You gained " + /*Player.addCoffee() +*/ " coffee in your inventory.";

    private static final String playerBrewsCoffee3x = "You put a coffee pod in. The Kuerig machine blinks red. " +
            "Seems like it’s about to overheat. I’ll need to come back later when it has cooled down.\n" +
            "No coffee gained.";


    //access methods

    /**
     * Returns the basic information script about the game.
     * User requested when they type "about" into the console in the "more" menu.
     *
     * @return basicInfo
     */
    public static String getBasicInfo() {
        return basicInfo;
    }

    /**
     * The first lines of the game when the game starts.
     * Returns the first scene script.
     *
     * @return firstScene
     */
    public static String getFirstScene() {
        return firstScene;
    }

    /**
     * Returns the script to ask the player what they would like to do before being presented with options.
     * @return the first lines the player sees.
     */
    public static String getPlayerRequest() {
        return playerRequest;
    }

    /**
     * Returns the script when the player decides they want to add 'code lines' to their inventory.
     * @return
     */
    public static String getPlayerCodes() {
        return playerCodes;
    }

    public static String getPlayerPrograms() {
        return playerPrograms;
    }

    public static String getPlayerBuildsProgram() {
        return playerBuildsProgram;
    }

    public static String getPlayerChoosesMore() {
        return playerChoosesMore;
    }

    public static String getPlayerChecksStatus() {
        return playerChecksStatus;
    }

    public static String getPlayerChecksInventory() {
        return playerChecksInventory;
    }

    public static String getRandomEncounter() {
        return randomEncounter;
    }

    public static String getPlayerReturnsToWorkstation() {
        return playerReturnsToWorkstation;
    }

    public static String getPlayerInBreakRoom() {
        return playerInBreakRoom;
    }

    public static String getPlayerAtVendingMachine() {
        return playerAtVendingMachine;
    }

    public static String getPlayerInMeetingRoom() {
        return playerInMeetingRoom;
    }

    public static String getPlayerFindsAbandonedWorkstation() {
        return PlayerFindsAbandonedWorkstation;
    }

    public static String getPlayerSearchesDeskSuccessfully() {
        return playerSearchesDeskSuccessfully;
    }

    public static String getPlayerSearchesDeskCaught() {
        return playerSearchesDeskCaught;
    }

    public static String getPlayerSearchesDeskEmpty() {
        return playerSearchesDeskEmpty;
    }

    public static String getPlayerInCoffeeBar() {
        return playerInCoffeeBar;
    }

    public static String getPlayerBrewsCoffee() {
        return playerBrewsCoffee;
    }

    public static String getPlayerBrewsCoffee3x() {
        return playerBrewsCoffee3x;
    }





}