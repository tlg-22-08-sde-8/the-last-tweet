import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

public class Sound {

  public static void main(String[] args)throws UnsupportedOperationException, IOException, LineUnavailableException, LineUnavailableException {
    Scanner scanner = new Scanner(System.in);
    File file = new File(source);
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    Clip clip = AudioSystem.getClip();
    clip.open(audioStream);

    String response = "";

    while(!response.equals("Q")) {

      response
    }
  }
}
