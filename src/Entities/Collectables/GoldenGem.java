package Entities.Collectables;

import Abstracts.Collectable;
import Utils.Animator;
import Utils.SpriteSheet;

import java.awt.*;

public class GoldenGem extends Collectable {


    public GoldenGem(double worldX, double worldY) {
        super(worldX, worldY, 70, 70);
        initCollectable();
    }


    @Override
    public void initCollectable() {
        spriteSheet = new SpriteSheet("src/resources/entities/spritesheets/golden-gem.png", 1, 5, 15, 13);
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
