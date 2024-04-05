package Abstracts;

import Utils.Animator;
import Utils.Sound;
import Utils.SpriteSheet;

import java.util.Arrays;

public abstract class Enemy extends Entity {
    public Animator deathAnimator;
    private static int deathCloudWidth;

    private static int deathCloudHeight;
    private boolean isKilled = false;
    private Sound deathSound = new Sound("src/resources/sound_effects/enemyDefeat.wav");

    public Enemy(double worldX, double worldY, int width, int height, int health, int speed){
        super(worldX, worldY,width, height, health, speed);
    }

    public void death(){
        deathSound.play();

        spriteSheet = new SpriteSheet("src/resources/entities/spritesheets/enemy-deadth.png",1,4,40,41);
        deathAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,0, 4), 0 , 8);
        currentAnimator = deathAnimator;


        if(width < height){
            deathCloudWidth = (int) (width * 2);
            deathCloudHeight = (int) (width * 2);
        }
        else{
            deathCloudWidth = (int) (height * 2);
            deathCloudHeight = (int) (height * 2);
        }



        worldX -= Math.abs((width - deathCloudWidth) / 2);
        worldY -= Math.abs((height - deathCloudHeight) / 2);

        width = deathCloudWidth;
        height = deathCloudHeight;

        isKilled = true;

    }

    public boolean isDeathComplete(){
        return currentAnimator.isAnimationComplete() && currentAnimator.equals(deathAnimator);
    }

    public boolean isKilled(){
        return isKilled;
    }
}
