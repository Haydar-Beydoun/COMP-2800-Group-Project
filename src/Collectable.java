import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Collectable extends GameObject{
    Rectangle hitBox;
    SpriteSheet spriteSheet;
    Animator currentAnimator;

    public Collectable(double worldX, double worldY, int width, int height) {
        super(worldX, worldY, width, height);
    }

    public void initCollectable(){

    }

    public void update(){

    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(worldX, worldY, width, height);
    }

}
