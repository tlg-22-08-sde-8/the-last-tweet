package com.twitter.game.model;

import java.util.ArrayList;

public class GameMaps {

    private static ArrayList<String> gameMapBlock1;
    private static ArrayList<String> gameMapBlock2;
    private static ArrayList<String> gameMapBlock3;
    private static ArrayList<String> gameMapBlock4;
    private static ArrayList<String> gameMapBlock5;
    private static ArrayList<String> gameMapBlock6;
    private static ArrayList<String> gameMapBlock7;
    /**
     *  ASCII of the Work station room separated into rows.
     */
    private static String r1_Workstation = "┌────────────────┐";
    private static String r2_Workstation = "│                │";
    private static String r3_Workstation = "│  Work Station  │";
    private static String r4_Workstation = "│                │";
    private static String r5_Workstation = "└────────────────┘";


    /**
     * ASCII Extended representation of the Meeting Room separated into rows.
     */
    private static String r1_MeetingRoom = "┌────────────────┐";
    private static String r2_MeetingRoom = "│                │";
    private static String r3_MeetingRoom = "│  Meeting Room  │";
    private static String r4_MeetingRoom = "│                │";
    private static String r5_MeetingRoom = "└────────────────┘";

    /**
     * ASCII Extended representation of the Break Room separated into rows.
     */
    private static String r1_BreakRoom = "┌────────────────┐";
    private static String r2_BreakRoom = "│                │";
    private static String r3_BreakRoom = "│   Break Room   │";
    private static String r4_BreakRoom = "│                │";
    private static String r5_BreakRoom = "└────────────────┘";

    /**
     * ASCII Extended representation of the Coffee Bar separated into rows.
     */
    private static String r1_CoffeeBar = "┌────────────────┐";
    private static String r2_CoffeeBar = "│                │";
    private static String r3_CoffeeBar = "│  Coffee Bar    │";
    private static String r4_CoffeeBar = "│                │";
    private static String r5_CoffeeBar = "└────────────────┘";

    /**
     *  ASCII Extended representation of the Empty Workstation room separated into rows.
     */
    private static String r1_EmptyWorkstation = "┌────────────────┐";
    private static String r2_EmptyWorkstation = "│     Empty      │";
    private static String r3_EmptyWorkstation = "│                │";
    private static String r4_EmptyWorkstation = "│  Workstation   │";
    private static String r5_EmptyWorkstation = "└────────────────┘";

    /**
     *  ASCII Extended representation of the Horizontal Arrows separated into rows.
     */
    private static String r1_HorizontalArrows = "                  ";

    private static String r2_HorizontalArrows = "┌────────────────►";
    private static String r3_HorizontalArrows = "                  ";
    private static String r4_HorizontalArrows = "◄────────────────┘";
    private static String r5_HorizontalArrows = "                  ";

    /**
     *  ASCII Extended representation of the Vertical Arrows separated into rows.
     */
    private static String r1_VerticalArrows = "       ┌ ▲        ";
    private static String r2_VerticalArrows = "       │ │        ";
    private static String r3_VerticalArrows = "       │ │        ";
    private static String r4_VerticalArrows = "       │ │        ";
    private static String r5_VerticalArrows = "       ▼ ┘        ";


    private static String r1_EmptySpace = "                  ";
    private static String r2_EmptySpace = "                  ";
    private static String r3_EmptySpace = "                  ";
    private static String r4_EmptySpace = "                  ";
    private static String r5_EmptySpace = "                  ";


    private GameMaps()   {
        //  Construction not possible
    }

    /**
     * Generates the easy map in the console.
     */
    public static void getGameMap()  {
        System.out.println("\t\tYou pulled out the paper map of the Twitter Office.");
        gameMapBlock1 = new ArrayList<>();
        gameMapBlock2 = new ArrayList<>();
        gameMapBlock3 = new ArrayList<>();

        gameMapBlock1.add(r1_MeetingRoom + r1_HorizontalArrows + r1_BreakRoom + r1_HorizontalArrows + r1_MeetingRoom );
        gameMapBlock1.add(r2_MeetingRoom + r2_HorizontalArrows + r2_BreakRoom + r2_HorizontalArrows + r2_MeetingRoom );
        gameMapBlock1.add(r3_MeetingRoom + r3_HorizontalArrows + r3_BreakRoom + r3_HorizontalArrows + r3_MeetingRoom );
        gameMapBlock1.add(r4_MeetingRoom + r4_HorizontalArrows + r4_BreakRoom + r4_HorizontalArrows + r4_MeetingRoom );
        gameMapBlock1.add(r5_MeetingRoom + r5_HorizontalArrows + r5_BreakRoom + r5_HorizontalArrows + r5_MeetingRoom);

        gameMapBlock2.add(r1_VerticalArrows + r1_EmptySpace + r1_VerticalArrows + r1_EmptySpace + r1_VerticalArrows );
        gameMapBlock2.add(r2_VerticalArrows + r2_EmptySpace + r2_VerticalArrows + r2_EmptySpace + r2_VerticalArrows );
        gameMapBlock2.add(r3_VerticalArrows + r3_EmptySpace + r3_VerticalArrows + r3_EmptySpace + r3_VerticalArrows );
        gameMapBlock2.add(r4_VerticalArrows + r4_EmptySpace + r4_VerticalArrows + r4_EmptySpace + r4_VerticalArrows );
        gameMapBlock2.add(r5_VerticalArrows + r5_EmptySpace + r5_VerticalArrows + r5_EmptySpace + r5_VerticalArrows);

        gameMapBlock3.add(r1_CoffeeBar + r1_HorizontalArrows + r1_Workstation + r1_HorizontalArrows + r1_EmptyWorkstation );
        gameMapBlock3.add(r2_CoffeeBar + r2_HorizontalArrows + r2_Workstation + r2_HorizontalArrows + r2_EmptyWorkstation );
        gameMapBlock3.add(r3_CoffeeBar + r3_HorizontalArrows + r3_Workstation + r3_HorizontalArrows + r3_EmptyWorkstation );
        gameMapBlock3.add(r4_CoffeeBar + r4_HorizontalArrows + r4_Workstation + r4_HorizontalArrows + r4_EmptyWorkstation );
        gameMapBlock3.add(r5_CoffeeBar + r5_HorizontalArrows + r5_Workstation + r5_HorizontalArrows + r5_EmptyWorkstation);
        for (String s : gameMapBlock1) {
            System.out.println(s);
        }
        for (String s: gameMapBlock2) {
            System.out.println(s);
        }
        for (String s: gameMapBlock3) {
            System.out.println(s);
        }
    }




}



