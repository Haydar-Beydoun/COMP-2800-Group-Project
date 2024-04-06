package Entities.Collectables;

import Abstracts.Collectable;
import Utils.Animator;
import Utils.Sound;
import Utils.SpriteSheet;

import java.awt.*;

/**
 * Cherry class to initialize the cherry collectable.
 * <br>
 * The cherry is a collectable that the player can collect to increase their score.
 * The cherry is a subclass of the Collectable class.
 * @see Collectable
 */
public class Cherry extends Collectable {

    /**
     * Constructor of the Cherry class.
     * @param worldX X position on the canvas.
     * @param worldY Y position on the canvas.
     */
    public Cherry(double worldX, double worldY) {
        super(worldX, worldY, 70, 70);

        collectSound = new Sound("/resources/sound_effects/cherryCollection.wav");

        initCollectable();
    }

    @Override
    public void initCollectable(){
        spriteSheet = new SpriteSheet("/resources/entities/spritesheets/cherry.png", 1, 8, 21, 21);
        currentAnimator = new Animator(spriteSheet.images, 0, 9);
    }

    @Override
    public void update(){
        currentAnimator.update();

    }

    public void draw(Graphics2D g2d, int offsetX, int offsetY){
        g2d.drawImage(currentAnimator.currentFrame, (int) worldX + offsetX, (int) worldY + offsetY, width, height, null);

    }
}
