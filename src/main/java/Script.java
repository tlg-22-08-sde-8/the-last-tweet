import java.util.ArrayList;

public class Script {


    // Contains the basic information script about the game
    ArrayList<String> basicInfo = new ArrayList<String>();
    // Contains the first scene of the game
    ArrayList<String> firstScene = new ArrayList<String>();

    /**
     * Constructor for the Script class. It initializes text for all the different static scenarios, i.e., the portions
     * of the script that do not require parsing of text to provide unique information.
     */
    private Script() {

        // Basic information
        basicInfo.add("A Text-Based Parody Survival Horror Adventure Game about the Great Twitter Purge of 2022!");
        basicInfo.add("You navigate and interact with the game by typing in the commands to the console below.");
        basicInfo.add("Type 'help' for a list of commands.");
        basicInfo.add("Developed by: Brenden King, Patrick Lauer, and Mohamed Omar");
        basicInfo.add("Version: 0.1.0");

        // First scene
        firstScene.add("It is October 27th.");
        firstScene.add("You wake up at your desk, groggy from an attack.");
        firstScene.add("You look around you. There are pink slips everywhere. Fires raging. Employees crying.");
        firstScene.add("Elon Musk has acquired Twitter.");
        firstScene.add("Half of the Twitter workforce has been laid off.");
        firstScene.add("You are one of the survivors.");


    }

    public ArrayList<String> getBasicInfo() {
        return basicInfo;
    }


    public void getScriptLine(int line) {
        System.out.println(basicInfo.get(line));
    }
}