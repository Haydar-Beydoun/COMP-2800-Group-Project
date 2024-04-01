import java.awt.image.BufferedImage;

public class Animator {
    public BufferedImage[] frames;
    public BufferedImage currentFrame;

    private int time = 0;
    public int currentIndex;
    public int durationPerFrame;


    public Animator(BufferedImage[] frames,int startIndex,int durationPerFrame){
        this.frames = frames;
        this.currentIndex = startIndex;
        this.durationPerFrame = durationPerFrame;
        currentFrame = frames[currentIndex];
    }

    public void update(){
        time++;
        if(time >= durationPerFrame){
            time = 0;
            currentIndex++;
            if(currentIndex > frames.length - 1) currentIndex = 0;
        }
        currentFrame = frames[currentIndex];
    }

    public boolean isAnimationComplete(){
        return currentIndex == frames.length - 1;
    }
}
