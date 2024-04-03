package Entities.Collectables;

import Abstracts.Collectable;

import java.awt.*;

public class FinishLevelItem extends Collectable {

    public FinishLevelItem(double worldX, double worldY) {
        super(worldX, worldY, 70, 70);
    }


    @Override
    public void initCollectable() {

    }

    @Override
    public void update() {

    }

    public void draw(Graphics2D g2d, int offsetX, int offsetY){
        //g2d.drawImage(currentAnimator.currentFrame, (int) worldX + offsetX, (int) worldY + offsetY, width, height, null);
        g2d.setColor(Color.black);
        g2d.drawRect((int) worldX + offsetX, (int) worldY + offsetY, width, height);


    }

}
