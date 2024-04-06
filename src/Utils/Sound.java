package Utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;

public class Sound {
    String filePath;
    Clip audioClip;

    public Sound(String filePath){
        this.filePath = filePath;
    }
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
    public void stop(){
        try{
            audioClip.stop();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void loop(){
        try{
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
