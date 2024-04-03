package Entities.Collectables;

import Abstracts.Collectable;
import Utils.Animator;
import Utils.SpriteSheet;

import java.awt.*;

/**
 * Gem class.
 * <br>
 * Gems are rare Collectables that the player can collect.
 * @see Collectable
 */
public class Gem extends Collectable {


    /**
     * Constructor of the Gem class.
     * @param worldX X position on the canvas.
     * @param worldY Y position on the canvas.
     */
    public Gem(double worldX, double worldY) {
        super(worldX, worldY, 64, 64);
        initCollectable();
    }

    @Override
    public void initCollectable(){
        spriteSheet = new SpriteSheet("src/resources/entities/spritesheets/gem.png", 1, 5, 15, 13);
        currentAnimator = new Animator(spriteSheet.images, 0, 7);
    }

    @Override
    public void update(){
        currentAnimator.update();

    }

    public void draw(Graphics2D g2d, int offsetX, int offsetY){
        g2d.drawImage(currentAnimator.currentFrame, (int) worldX + offsetX, (int) worldY + offsetY, width, height, null);

    }




}
