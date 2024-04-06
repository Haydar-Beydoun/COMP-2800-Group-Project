package Utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;

/**
 * Sound class to play sound effects
 */
public class Sound {
    String filePath;
    Clip audioClip;

    /**
     * Constructor
     * @param filePath path to the sound file
     */
    public Sound(String filePath){
        this.filePath = filePath;
    }
    /**
     * Plays the sound.
     */
    public void play(){
        try{
            AudioInputStream sound = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream(filePath)));
            audioClip = AudioSystem.getClip();
            audioClip.open(sound);
            audioClip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Stops the sound.
     */
    public void stop(){
        try{
            audioClip.stop();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * Loops the sound.
     */
    public void loop(){
        try{
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
