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

    private static final String userRequest = "What do you do?";

    private static final String playerCodes = "You log into your work computer. \n" +
            "You’ve gained " /*+ Player.getCodeLines() */+ "lines of code. \n" +
            "You’ve lost 1 hunger. You have X hunger left. ";

    private static final String playerProgram = "The follow programs are available for building: \n" +
            "\tMagic Keyboard (cost: 1000 lines of code) - Increases Attack in battle by +10 lines per code.  \n" +
            "\tMagic mouse (cost: 500 lines of code) - Increases Attack in battle by +5 lines of code. \n" +
            "\tDigital Assistant (cost: 10,000 lines of code) - auto generates code by +10 per action while you " +
            "are away from your workstation. \n" +
            "\n" +
            "To “program” an item into your inventory, press the corresponding number or “q” to go back to " +
            "your workstation." ;

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

    private static final String PlayerAtVendingMachine = "Food items availible: \n" +
            "Candy Bar (cost: 200 lines of code) - increases hunger by 5 \n" +
            "Chips (cost: 500 lines of code) - increases hunger by 10\n"    +
            "Jerky (cost: 650 lines of code) - refill hunger to 15\n" +
            "Gum (cost: 350 line of code) - hunger level doesn’t decrease for 3 moves\n" +
            "The purchased item will be added to your inventory. press the corresponding number or “q” to go back.";

    private static final String PlayerInMeetingRoom = "This room looks like it belongs to a manager. " +
            "(recommeded codelines: "+  /*Enemy.bossRecommended() + */ "/current codelines: " +
            /*Player.getCodeLines() +*/ "\n" +
            "\tWhat do you do?";

    private static final String PlayerFindsAbandonedWorkstation =  "There is an abandoned workstation desk,possibly" +
            "belonging to a former employee that was just laid off hours ago. You ponder if there might be " +
            "useful supplies left behind within its innards.";

    private static final String PlayerSearchesDeskSuccessfully = "You stick your hand into the drawer of the desk." +
            "You feel something push back. \n" +
            "You gained " + /*Player.gainFood() +*/ "foods, and " + /*Player.getCoffee() +*/ " coffee.";














    //access methods

    /**
     * Returns the basic information script about the game.
     * User requested when they type "about" into the console in the "more" menu.
     * @return basicInfo
     */
    public static String getBasicInfo() {
        return basicInfo;
    }

    /**
     * The first lines of the game when the game starts.
     * Returns the first scene script.
     * @return firstScene
     */
    public static String getFirstScene() {
        return firstScene;
    }
}