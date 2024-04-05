package Utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class Sound {
    String filePath;
    Clip audioClip;

    public Sound(String filePath){
        this.filePath = filePath;
    }
    public void play(){
        try{
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(filePath));
            audioClip = AudioSystem.getClip();
            audioClip.open(sound);
            audioClip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void stop(){
        audioClip.stop();
    }
    public void loop(){
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
