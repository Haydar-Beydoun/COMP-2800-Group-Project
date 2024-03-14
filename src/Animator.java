import java.awt.image.BufferedImage;

public class Animator {
    public BufferedImage[] frames;
    public BufferedImage currentFrame;
    private int time;
    private int frameDuration;
    private int currentIndex;

    Animator(BufferedImage[] frames, int startIndex, int frameDuration){
        this.frames = frames;
        this.currentIndex = startIndex;
        this.frameDuration = frameDuration;
        this.currentFrame = frames[currentIndex];
    }

    public void update(){
        time++;

        if(time >= frameDuration) {
            time = 0;
            currentIndex++;
            if(currentIndex > frames.length - 1)
                currentIndex = 0;
        }

        currentFrame = frames[currentIndex];

    }

}
