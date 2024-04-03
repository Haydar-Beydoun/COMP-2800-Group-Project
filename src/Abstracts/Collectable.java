package Abstracts;

import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import Utils.Animator;
import Utils.SpriteSheet;

public abstract class Collectable extends GameObject {
    public SpriteSheet spriteSheet;
    public Animator currentAnimator;
    public Animator collectedAnimator;
    private static int collectedCloudWidth;
    private static int collectedCloudHeight;
    private boolean isCollected = false;

    public Collectable(double worldX, double worldY, int width, int height) {
        super(worldX, worldY, width, height);
    }

    public abstract void initCollectable();

    public abstract void update();

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(worldX, worldY, width, height);
    }

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

    public boolean isCollected() {
        return isCollected;
    }

    public boolean isCollectComplete(){
        return currentAnimator.isAnimationComplete() && currentAnimator.equals(collectedAnimator);
    }

}
