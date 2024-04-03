package Abstracts;

import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import Utils.Animator;
import Utils.SpriteSheet;


/**
 * Abstract class to initialize collectable objects.
 * <br>
 * Collectable objects are objects that can be collected by the player.
 */
public abstract class Collectable extends GameObject {
    public SpriteSheet spriteSheet;
    public Animator currentAnimator;
    public Animator collectedAnimator;
    private static int collectedCloudWidth;
    private static int collectedCloudHeight;
    private boolean isCollected = false;

    /**
     * Constructor of the collectable object.
     * @param worldX X position on the canvas.
     * @param worldY Y position on the canvas.
     * @param width width of the hitBox.
     * @param height height of the hitBox.
     */
    public Collectable(double worldX, double worldY, int width, int height) {
        super(worldX, worldY, width, height);
    }


    public abstract void initCollectable();

    public abstract void update();

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(worldX, worldY, width, height);
    }

    /**
     * Collects the collectable object.
     * <br>
     *
     */
    public void collect(){
        spriteSheet = new SpriteSheet("src/resources/entities/spritesheets/item-feedback.png",1,5,32,32);
        collectedAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,0, 6), 0 , 5);
        currentAnimator = collectedAnimator;

        if(width < height){
            collectedCloudWidth = (int) (width * 2);
            collectedCloudHeight = (int) (width * 2);
        }
        else{
            collectedCloudWidth = (int) (height * 2);
            collectedCloudHeight = (int) (height * 2);
        }

        worldX -= Math.abs((width - collectedCloudWidth) / 2);
        worldY -= Math.abs((height - collectedCloudHeight) / 2);

        width = collectedCloudWidth;
        height = collectedCloudHeight;

        isCollected = true;
    }

    /**
     * Checks if the collectable object is collected.
     * @return True if the collectable object is collected, false otherwise.
     */
    public boolean isCollected() {
        return isCollected;
    }

    public boolean isCollectComplete(){
        return currentAnimator.isAnimationComplete() && currentAnimator.equals(collectedAnimator);
    }

}
