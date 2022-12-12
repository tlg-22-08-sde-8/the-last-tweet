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

    //access methods
    public static String getBasicInfo() {
        return basicInfo;
    }

    public static String getFirstScene() {
        return firstScene;
    }
}