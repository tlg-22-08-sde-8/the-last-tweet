public final class Script {


    // Contains the basic information script about the game
    private static final String basicInfo = "A Text-Based Parody Survival Horror Adventure Game about the Great Twitter " +
            "Purge of 2022! You navigate and interact with the game by typing in the commands to the console below." +
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
            "\tDigital Assistant (cost: 10,000 lines of code) - auto generates code by +10 per action while you are away from your workstation. \n" +
            "\n" +
            "To “program” an item into your inventory, press the corresponding number or “q” to go back to your workstation." ;

    private static final String playerBuildsProgram = "The following program +" +
            /* Store.getMagicKeyboard() +*/
            "has been built. \n" +
            "You have lost " + /*Player.getHunger() +*/ "hunger. You have X hunger left.\n";


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