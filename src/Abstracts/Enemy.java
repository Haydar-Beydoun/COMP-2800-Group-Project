package Abstracts;

import Utils.Animator;
import Utils.Sound;
import Utils.SpriteSheet;

import java.util.Arrays;

/**
 * Abstract class to initialize enemies.
 * Class is used to initialize their death animation/mechanics
 */

public abstract class Enemy extends Entity {
    public Animator deathAnimator;
    private static int deathCloudWidth;

    private static int deathCloudHeight;
    private boolean isKilled = false;
    private Sound deathSound = new Sound("/resources/sound_effects/enemyDefeat.wav");

    /**
     * Constructor of the enemy object.
     * @param worldX
     * @param worldY
     * @param width
     * @param height
     * @param health
     * @param speed
     */
    public Enemy(double worldX, double worldY, int width, int height, int health, int speed){
        super(worldX, worldY,width, height, health, speed);
    }

    /**
     * Initializes the death animation of the enemy.
     * adjusts the death VFX for each enemy's model size
     */
    public void death(){
        deathSound.play();

        spriteSheet = new SpriteSheet("/resources/entities/spritesheets/enemy-deadth.png",1,4,40,41);
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

    /**
     * Check if the death animation is complete to remove the enemy from the game.
     * <br>
     * @return boolean
     */
    public boolean isDeathComplete(){
        return currentAnimator.isAnimationComplete() && currentAnimator.equals(deathAnimator);
    }

    /**
     * Check if the enemy is killed.
     * <br>
     * @return boolean
     */
    public boolean isKilled(){
        return isKilled;
    }
}
