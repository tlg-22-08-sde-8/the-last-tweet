package com.twitter.game.model;

public final class Script {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";

    // Contains the basic information script about the game
    private static final String basicInfo = "A Text-Based Parody Survival Horror Adventure about the Great \n" +
            "Twitter Purge of 2022! You navigate and interact with the game by typing in the commands to the \n" +
            "console below." +
            "Type 'help' for a list of commands. \n" +
            "Co-Developed by: Brenden King, Patrick Lauer, and Mohamed Omar \n" +
            "Version: 0.0.1";

    //  Contains the first half of the first scene script.
    private static final String firstScene1 = "\n\nIt is October 27th. \n" +
            "You wake up at your desk, groggy from an attack. You look around you. There are pink slips " +
            "everywhere. \n" +
            "Fires raging. Employees crying. \n" +
            "Your memory clears, and you remember what happened. \n" +
            "Elon Musk has acquired Twitter. Half of the Twitter workforce has been laid off. \n" +
            "You are one of the survivors. \n" +
            "You know what you must do. You must keep your job by providing code, but you must also stay employed \n" +
            "by going out into the wasteland of the Twitter office to find food and supplies. You need to keep your \n" +
            "employability up as much as your need to keep up your sanity and keep your hunger in check.\n" +
            "Keep these systems in check. Generate enough code lines to take down the system and ultimately \n" +
            "Elon Musk. \n" +
            "Before you is your desk. On it, you find your work computer with 10 lines of code already generated \n" +
            "and a book.";

    //  Contains the second half of the first scene script.
    private static final String firstScene2 =
            "Your stomach growls ferociously. You search your bag but find nothing. You should probably generate \n" +
            "some code lines on your laptop or take a trip to the break room first to buy some food to stave off \n" +
                    "your hunger. \n";

    //  Generic call for player action.
    private static final String playerRequest = "What do you do?";

    private static final String playerCodes = "You log into the computer using your Twitter credentials, then open \n" +
            "up an Development Environment. Your fingers start tapping away. \n";

    private static final String playerLevels = "The follow program are available for building: \n" +
            //  TODO: This entire String needs to be overhauled
            ANSI_BLUE + "\tBug Fix Suite" + ANSI_RESET + " (cost: 500 lines of code) " +
            "- Promotes you to SDE II (level 2)\n" +
            ANSI_BLUE + "\tTwo Factor Authentication System" + ANSI_RESET + " (cost: 1,000 lines of code) - " +
            "Creating this will promote you to SDE III (Level 3)\n" +
            ANSI_RED + "\t\t NOTE: Must have completed Bug Fix Suite to unlock ability to build this program \n" +
            ANSI_RESET + ANSI_BLUE + "\tDigital Assistant" + ANSI_RESET + " (cost: 2,500 lines of code) - " +
            "Creating this will promote you to SDE 4r (Level 4). \n" +
            ANSI_RED + "\t\t NOTE: Must have completed Two Factor Authentication System to unlock \n" + ANSI_RESET +
            ANSI_BLUE + "\tContent Moderation System" + ANSI_RESET + " (cost: 5,000 lines of code) - " +
            "Creating this will promote you to SDE 5 (Level 5). \n" +
            ANSI_RED + "\t\t NOTE: Must have completed Digital Assistant to unlock \n" + ANSI_RESET +
            "Buy a  program to level up or “q” to go back to your workstation.";

    private static final String playerBuildsProgram = "The following program +" +
            /* Store.getMagicKeyboard() +*/
            "has been built. \n" +
            "You have lost " + /*com.twitter.game.model.Player.getHunger() +*/ "hunger. You have X hunger left.\n";

    private static final String playerChecksStatus = "Status: \n" +
            "\tEmployment: " + /*com.twitter.game.model.Player.getCurrentEmployment() + */ "/" +
            /*com.twitter.game.model.Player.getMaxEmployment() + */ "\n" +
            "\t\tDon't let this reach 0 or it's game over!\n" +
            "\n" +
            "\tSanity: " + /*com.twitter.game.model.Player.getCurrentSanity() + */ "/" +
            /*com.twitter.game.model.Player.getMaxSanity() + */ "\n" +
            "\t\tIf 0, Employment will start depleting instead!\n" +
            "\n" +
            "\tHunger: " + /*com.twitter.game.model.Player.getCurrentHunger() + */ "/" +
            /*com.twitter.game.model.Player.getHunger() + */ "\n" +
            "\t\tIf 0, Sanity will start depleting instead!\n";

    private static final String playerChecksInventory = " Inventory: \n" +
            "\tLines of Code: " + /*com.twitter.game.model.Player.getCodeLines() +*/ "\n" +
            "\t\t Currency for programming and used in battle for fights. If you are low, you can't attack, so keep " +
            "this high! \n" +
            "\tCoffee: " + /*com.twitter.game.model.Player.getCoffeeSupply() +*/ "\n" +
            "\t\tEach coffee restores 5 sanity\n" +
            "\tFood: " + /*com.twitter.game.model.Player.getFoodSupply() +*/ "\n" +
            "\t\tEach food restores 5 of hunger meter\n";

    private static final String randomEncounter = "You ran into " /*+ com.twitter.game.model.Enemy.getEnemyType()*/;

    private static final String playerReturnsToWorkstation = "You are standing in a bleak, cold room that smells " +
            "like feet and despair. You are exhausted, but alive. You sit back down at your work computer, " +
            "pondering your next steps. \n" +
            "\n" +
            "You have " + /*com.twitter.game.model.Player.getCodeLines() +*/ " lines of code in inventory. ";

    private static final String playerInBreakRoom = "You feel uneasy...there is no presence in this room, as if \n" +
            "those who stayed behind were afraid to take breaks. In the corner, however, is some form of temporary \n" +
            "respite: an old clunky vending machine with a flickering blue light.\n";

    private static final String playerAtVendingMachine = "Food items availible: \n" +
            "Candy Bar (cost: 200 lines of code) - increases hunger by 5 \n" +
            "Chips (cost: 500 lines of code) - increases hunger by 10\n" +
            "Jerky (cost: 650 lines of code) - refill hunger to 15\n" +
            "The purchased item will be added to your inventory. press the corresponding number or “q” to go back.";

    private static final String playerInCEORoom = "You peek inside. You see Elon Musk furiously typing away" +
            "at his phone. Your palms are sweaty. Is it time to take him on? \n" +
            "(recommended codelines: " +  /*com.twitter.game.model.Enemy.bossRecommended() + */ "/current codelines: " +
            /*com.twitter.game.model.Player.getCodeLines() +*/ "\n";

    private static final String PlayerFindsAbandonedWorkstation = "There is an abandoned workstation desk,possibly\n" +
            "belonging to a former employee that was just laid off hours ago. You ponder if there might be \n" +
            "useful supplies left behind within its innards, but I could seriously put \nmy Employability in jeopardy " +
            "if I am caught!";

    private static final String playerSearchesDeskSuccessfully = "You stick your hand into the drawer of the desk." +
            "You feel something push back. \n" +
            "You gained " + /*com.twitter.game.model.Player.gainFood() +*/ "foods, and " +
            /*com.twitter.game.model.Player.getCoffee() +*/ " coffee.";

    private static final String playerSearchesDeskCaught = "You feel a presence behind you as you try to search the " +
            "desk. \n" + "" +
            "The product Manager shows up! \n" +
            "“What ARE YOU DOING?!” \n" +
            "You got reprimanded for your actions!";

    private static final String playerSearchesDeskEmpty = "You stick your hand into the drawer of the desk. \n" +
            "Your hands feel only the desk itself push back against your fingers as you crawl your hand deeper! \n" +
            "“The desk is empty. You attained nothing.” \n";

    private static final String playerInCoffeeBar = "The room is composed of brilliant white marble. The air \n" +
            "smells of citrus. A heavenly glow eminates from the coffee bar, like the open arms of an angel. \n" +
            "The Kuerig machine is running. A lone laptop is in the room.";

    private static final String playerBrewsCoffee = "You put a coffee pod in. The Kuerig machine purrs. " +
            "The smell of earth fills the room.  \n" +
            "You gained " + /*com.twitter.game.model.Player.addCoffee() +*/ " coffee in your inventory.";

    private static final String playerBrewsCoffee3x = "You put a coffee pod in. The Kuerig machine blinks red. " +
            "Seems like it’s about to overheat. I’ll need to come back later when it has cooled down.\n" +
            "No coffee gained.";

    private static final String survivalGuide =
                    "╭━━━━╮╱╱╱╱╭╮╱╭╮╱╱╱╱╱╱╭━━━╮╱╱╱╱╱╱╱╱╱╱╱╱╱╱╭╮╱╭━━━╮╱╱╱╱╱╭╮\n" +
                    "┃╭╮╭╮┃╱╱╱╭╯╰┳╯╰╮╱╱╱╱╱┃╭━╮┃╱╱╱╱╱╱╱╱╱╱╱╱╱╱┃┃╱┃╭━╮┃╱╱╱╱╱┃┃\n" +
                    "╰╯┃┃┣┫╭╮╭╋╮╭┻╮╭╋━━┳━╮┃╰━━┳╮╭┳━┳╮╭┳┳╮╭┳━━┫┃╱┃┃╱╰╋╮╭┳┳━╯┣━━╮\n" +
                    "╱╱┃┃┃╰╯╰╯┣┫┃╱┃┃┃┃━┫╭╯╰━━╮┃┃┃┃╭┫╰╯┣┫╰╯┃╭╮┃┃╱┃┃╭━┫┃┃┣┫╭╮┃┃━┫\n" +
                    "╱╱┃┃╰╮╭╮╭┫┃╰╮┃╰┫┃━┫┃╱┃╰━╯┃╰╯┃┃╰╮╭┫┣╮╭┫╭╮┃╰╮┃╰┻━┃╰╯┃┃╰╯┃┃━┫\n" +
                    "╱╱╰╯╱╰╯╰╯╰┻━╯╰━┻━━┻╯╱╰━━━┻━━┻╯╱╰╯╰╯╰╯╰╯╰┻━╯╰━━━┻━━┻┻━━┻━━╯\n" +

            "Hi I don't know who is reading this but I was recently promoted to Principal Engineer by " +
                            "Elon Musk himself \n" +
             "In order to survive and thrive at Twitter I have a couple recommendations: \n" +
                            "1. Before you travel anywhere in the office have at least 50 lines of code ready. " +
                            "The office is scattered with people who want to outperform/embarrass you \n" +
                            "2. If you start feeling like your going insane, grab a cup of coffee. It has always " +
                            "helped me \n" +
                            "3. Remember to get some food when your hungry. There is a break room for a reason"

            ;

    public static final String youDefeatedTheFinalBoss =
            "Elon Musk, exasperated from expended energy and struggling to stay on the one knee he is on, \n" +
                    "looks up at you and says, “\"Don't think I have any intention of caressing your cheek \n" +
                    "and saying I was wrong. I will not weep and wonder what might have been. \n" +
                    "I'm sure you understand. \n" +
                    "Still... I'm proud of you in a way. \n" +
                    "You have shown great conviction. Strength. Courage. All mankind-shaping qualities.";

    public static final String finalWordsOfElon = "\n\n...I should have fired you long ago.\"\n\n";

    public static String postElon =
            "And with those final last words, Elon Musk collapses - a symbolic stepping down of his reign.\n" +
            "You walk towards his body and pick up his CEO badge. You place it upon your breast pocket. \n" +
            "You are now the CEO of Twitter. With a sigh of relief, you look forward to try and salvage Twitter.\n";
    //TODO: add either YOU WIN or THE END in ASCII.

    //access methods
    public static String getSurvivalGuide(){
        return survivalGuide;
    }


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
    public static void getFirstScene(Player player) {
        System.out.println(firstScene1);
        System.out.println(ANSI_RED + "You have " + player.getCodeLines() + " lines of code.\n" + ANSI_RESET);
        System.out.println(firstScene2);
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

    /**
     * Returns the script when the player decides they want to add 'code lines' to their inventory.
     * @return playerPrograms - returns a string that says that the player is programming.
     */
    public static String getPlayerLevels() {
        return playerLevels;
    }

    public static String getPlayerBuildsProgram() {
        return playerBuildsProgram;
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

    public static String getPlayerInCEORoom() {
        return playerInCEORoom;
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

    public static void getPostFinalBossDialogue() throws InterruptedException {
        System.out.println(youDefeatedTheFinalBoss);
        Thread.sleep(5000);
        System.out.println(finalWordsOfElon);
        Thread.sleep(3000);
        System.out.println(postElon);
    }







}