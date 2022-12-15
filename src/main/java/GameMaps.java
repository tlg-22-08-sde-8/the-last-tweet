public class GameMaps {

    private static ArrayList<String> gameMap;
    /**
     *  ASCII of the Work station room
     */
    private static String r1_Workstation = "┌────────────────┐";
    private static String r2_Workstation = "│                │";
    private static String r3_Workstation = "│                │";
    private static String r4_Workstation = "│  Work Station  │";
    private static String r5_Workstation = "│                │";
    private static String r6_Workstation = "│                │";
    private static String r7_Workstation = "└────────────────┘";


    /**
     * ASCII Extended representation of the Meeting Room
     */
    private static String r1_MeetingRoom = "┌────────────────┐";
    private static String r2_MeetingRoom = "│                │";
    private static String r3_MeetingRoom = "│                │";
    private static String r4_MeetingRoom = "│  Meeting Room  │";
    private static String r5_MeetingRoom = "│                │";
    private static String r6_MeetingRoom = "│                │";
    private static String r7_MeetingRoom = "└────────────────┘";


    private static String r1_BreakRoom = "┌────────────────┐";
    private static String r2_BreakRoom = "│                │";
    private static String r3_BreakRoom = "│                │";
    private static String r4_BreakRoom = "│   Break Room   │";
    private static String r5_BreakRoom = "│                │";
    private static String r6_BreakRoom = "│                │";
    private static String r7_BreakRoom = "└────────────────┘";

    private static String r1_CoffeeBar = "┌────────────────┐";
    private static String r2_CoffeeBar = "│                │";
    private static String r3_CoffeeBar = "│                │";
    private static String r4_CoffeeBar = "│  Coffee Bar    │";
    private static String r5_CoffeeBar = "│                │";
    private static String r6_CoffeeBar = "│                │";
    private static String r7_CoffeeBar = "└────────────────┘";

    private static String r1_EmptyWorkstation = "┌────────────────┐";
    private static String r2_EmptyWorkstation = "│                │";
    private static String r3_EmptyWorkstation = "│     Empty      │";
    private static String r4_EmptyWorkstation = "│                │";
    private static String r5_EmptyWorkstation = "│  Workstation   │";
    private static String r6_EmptyWorkstation = "│                │";
    private static String r7_EmptyWorkstation = "└────────────────┘";


    private static String r1_HorizontalArrows = "                  ";
    private static String r2_HorizontalArrows = "                  ";
    private static String r3_HorizontalArrows = "┌────────────────►";
    private static String r4_HorizontalArrows = "                  ";
    private static String r5_HorizontalArrows = "◄────────────────┘";
    private static String r6_HorizontalArrows = "                  ";
    private static String r7_HorizontalArrows = "                  ";

    private static String r1_VerticalArrows = "         ┌▲\n" +
                    "││\n" +
                    "││\n" +
                    "││\n" +
                    "││\n" +
                    "││\n" +
                    "▼┘";



     public GameMaps() {
        gameMap = new ArrayList<>();
        gameMap.add(workStationRoom);
        gameMap.add(meetingRoom);
        gameMap.add(breakRoom);
        gameMap.add(coffeeBarRoom);
        gameMap.add(emptyWorkstationRoom);
        gameMap.add(horizontalArrows);
        gameMap.add(verticalArrows);
     }

}



