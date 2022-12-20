package com.twitter.game.model;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Music {
    //fields
    private boolean music = true;
    private Clip clip;
    private float backgroundVolume = .7f;
    private float battleVolume = .7f;

    //ctor
    public Music() {
    }

    /**
     * Generates background music
     */
    //business
    public void backgroundMusic() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        if (music) {
            URL resource = getClass().getClassLoader().getResource("Minecraft.wav");
            if (resource == null)
                throw new IllegalArgumentException("file not found!");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(resource);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(backgroundVolume));
            clip.start();
        }
    }

    public void endingMusic() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        if (music) {
            URL resource = getClass().getClassLoader().getResource("ending.wav");
            if (resource == null)
                throw new IllegalArgumentException("file not found!");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(resource);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(backgroundVolume));
            clip.start();
        }
    }

    /**
     * Generates battle music
     */
    public void battleMusic() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        clip.stop();
        if (music) {
            URL resource = getClass().getClassLoader().getResource("Pokemon.wav");
            if (resource == null)
                throw new IllegalArgumentException("file not found!");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(resource);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(battleVolume));
            clip.start();
        }
    }

    /**
     * Generates Victory music
     */
    public void victoryMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (music) {
            URL resource = getClass().getClassLoader().getResource("win_pokemon.wav");
            if (resource == null)
                throw new IllegalArgumentException("file not found!");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(resource);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(battleVolume));
            clip.start();
        }
    }

    public void gameOverMusic() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        URL resource = getClass().getClassLoader().getResource("game-over.wav");
        if (resource == null)
            throw new IllegalArgumentException("file not found!");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(resource);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(backgroundVolume - .2));
        clip.start();
    }


    /**
     * stops playing the current clip of music
     */
    public void stopMusic() {
        clip.stop();
    }

    //get and set
    public float getBackgroundVolume() {
        return backgroundVolume;
    }

    public void setBackgroundVolume(float backgroundVolume) {
        this.backgroundVolume = backgroundVolume;
    }

    public float getBattleVolume() {
        return battleVolume;
    }

    public void setBattleVolume(float battleVolume) {
        this.battleVolume = battleVolume;
    }

    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

}
