package com.twitter.game.client;

import com.twitter.game.controller.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.OFF);
        while (true) {
            //start game
            Game game = new Game();
            game.gameIntro();
            game.commandInput();
            //new game logic
            System.out.println("want to play again? (enter 'yes' for new game)");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String newGame = br.readLine().toLowerCase();
            if (!newGame.equals("yes")){
                break;
            }
        }

    }
}