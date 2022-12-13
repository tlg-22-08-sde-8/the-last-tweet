public final class Script {


    // Contains the basic information script about the game
    private static final String basicInfo = "A Text-Based Parody Survival Horror Adventure Game about the Great " +
            "Twitter Purge of 2022! You navigate and interact with the game by typing in the commands to the " +
            "console below." +
            "Type 'help' for a list of commands. \n" +
            "Co-Developed by: Brenden King, Patrick Lauer, and Mohamed Omar \n" +
            "Version: 0.0.1\n";
    private static final String firstScene = "\n\nIt is October 27th. \n" +
            "You wake up at your desk, groggy from an attack. You look around you. There are pink slips everywhere. " +
            "Fires raging. Employees crying. \n" +
            "Elon Musk has acquired Twitter. Half of the Twitter workforce has been laid off. \n" +
            "You are one of the survivors. \n";

    private static final String userRequest = "What do you do?\n";

    private static final String playerCodes = "You log into your work computer. \n" +
            "You’ve gained " /*+ Player.getCodeLines() */+ "lines of code. \n" +
            "\tYou’ve lost 1 hunger. You have " + /* Player.getHunger() +*/ " hunger left. \n";

    private static final String playerProgram = "The follow programs are available for building: \n" +
            "\t\tMagic Keyboard (cost: 1000 lines of code) - Increases Attack in battle by +10 lines per code.  \n" +
            "\t\tMagic mouse (cost: 500 lines of code) - Increases Attack in battle by +5 lines of code. \n" +
            "\t\tDigital Assistant (cost: 10,000 lines of code) - auto generates code by +10 per action while you " +
            "are away from your workstation. \n" +
            "\n" +
            "\tTo “program” an item into your inventory, press the corresponding number or “q” to go back to " +
            "your workstation.\n" ;

    private static final String playerBuildsProgram = "The following program +" +
            /* Store.getMagicKeyboard() +*/
            "has been built. \n" +
            "You have lost " + /*Player.getHunger() +*/ "hunger. You have X hunger left.\n";

    private static final String playerChoosesMore = "What would you like to know?\n";

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

    private static final String randomEncounter = "You ran into " + /*+ Enemy.getEnemyType() +*/ "!\n";

    private static final String playerReturnsToWorkstation = "You are standing in a bleak, cold room that smells " +
            "like feet and despair. You are exhausted, but alive. You sit back down at your work computer, " +
            "pondering your next steps. \n" +
            "\n" +
            "\tYou have " + /*Player.getCodeLines() +*/ " lines of code in inventory.\n";

    private static final String playerInBreakRoom = "You feel uneasy...there is no presence in this room, as if " +
            "those who stayed behind were afraid to take breaks. In the corner, however, is some form of temporary " +
            "respite: an old clunky vending machine with a flickering blue light.\n";

    private static final String playerAtVendingMachine = "Food items availible: \n" +
            "\t\tCandy Bar (cost: 200 lines of code) - increases hunger by 5 \n" +
            "\t\tChips (cost: 500 lines of code) - increases hunger by 10\n"    +
            "\t\tJerky (cost: 650 lines of code) - refill hunger to 15\n" +
            "\t\tGum (cost: 350 line of code) - hunger level doesn’t decrease for 3 moves\n" +
            "\t\tThe purchased item will be added to your inventory. press the corresponding number or “q” to " +
            "go back.\n";

    private static final String playerInMeetingRoom = "This room looks like it belongs to a manager. " +
            "\t(recommended codelines: "+  /*Enemy.bossRecommended() + */ "/current codelines: " +
            /*Player.getCodeLines() +*/ "\n" +
            "\tWhat do you do? \n";

    private static final String PlayerFindsAbandonedWorkstation =  "There is an abandoned workstation desk,possibly" +
            "belonging to a former employee that was just laid off hours ago. You ponder if there might be " +
            "useful supplies left behind within its innards. \n";

    private static final String playerSearchesDeskSuccessfully = "You stick your hand into the drawer of the desk." +
            "You feel something push back. \n" +
            "\tYou gained " + /*Player.gainFood() +*/ "foods, and " + /*Player.getCoffee() +*/ " coffee. \n";

    private static final String playerSearchesDeskCaught = "You feel a presence behind you as you try to search the " +
            "desk. \n" + "" +
            "The product Manager shows up! \n" +
            "“What ARE YOU DOING?!” \n" +
            "\tEmployability reduces to –5. \n";

    private static final String playerSearchesDeskEmpty = "You stick your hand into the drawer of the desk. \n" +
            "Your hands feel only the desk itself push back against your fingers as you crawl your hand deeper! \n" +
            "“The desk is empty. You attained nothing.” \n";

    private static final String playerInCoffeeBar = "The room is composed of brilliant white marble. The air " +
            "smells of citrus. A heavenly glow eminates from the coffee bar, like the open arms of an angel. " +
            "The Kuerig machine is running. A lone laptop is in the room. \n";

    private static final String playerBrewsCoffee = "You put a coffee pod in. The Kuerig machine purrs. " +
            "The smell of earth fills the room.  \n" +
            "\tYou gained " + /*Player.addCoffee() +*/ " coffee in your inventory. \n";

    private static final String playerBrewsCoffee3x = "You put a coffee pod in. The Kuerig machine blinks red. " +
            "Seems like it’s about to overheat. I’ll need to come back later when it has cooled down.\n" +
            "\tNo coffee gained.\n";

    private static final String promotePlayer = "ou have defeated " + /*Enemy.getCurrentBossName() +*/ "\n" +
            "He walks away in shame and passes you his badge. \n" +
            "\n" +
            "\tYou have been promoted to X employee! \n" +
            "\n" +
            "\tYou gained +25 Employability \n" +
            "\n" +
            "\tYou gained +25 Sanity \n" +
            "\n" +
            "\tYou gained +25 Hunger \n" +
            "\n" +
            "\tYou gained +5 coffee \n" +
            "\n" +
            "\tYou gained +5 chips \n" +
            "\n" +
            "Your employability, sanity, and hunger are fully refilled! \n" +
            "\n" +
            "You now have " + /*Enemy.bossesRemaining() +*/ " more bosses to fight! Come back to the Meeting Room " +
            "if you are prepared for the next fight!\n";

    private static final String loseCondition = "You lost your all of your employability... \n" +
            "You are no longer employed at Twitter. \n" +
            "...You collapsed from exhaustion. \n" +
            "\t\t\t\tGAME OVER. \n";

    private static final String winCondition = "Elon Musk, exhausted, tweets out one last time. The last tweet as CEO.  \n" +
            "You stand above him and pull out his CEO badge.  \n" +
            "You are the one. You are the new CEO of Twitter.  \n" +
            "\t\t\t\tYOU WIN! A new social media platform shall rise in your stead.\n";

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