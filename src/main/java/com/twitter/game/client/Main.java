package com.twitter.game.client;

import com.twitter.game.controller.Game;
import com.twitter.game.model.Player;

import javax.sound.sampled.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        Clip clip;
        //create new game
        while (true) {
            final Player player = new Player(25, 25, 25);
            Game game = new Game(player);
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
