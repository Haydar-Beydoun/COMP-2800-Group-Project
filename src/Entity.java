import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * Abstract class to initialize entity objects.
 * @author Rayyan
 * @author Haydar
 */

public abstract class Entity extends GameObject{
    int health;
    double vx = 0;
    double vy = 0;
    int speed;
    int hitBoxLeftOffset = 0;
    int hitBoxRightOffset = 0;
    SpriteSheet spriteSheet;

    /**
     * Constructor of the entity
     * @param worldX X position on the canvas.
     * @param worldY Y position on the canvas.
     * @param width width of the hitBox.
     * @param height height of the hitBox.
     * @param health health of the entity.
     * @param speed speed of the entity.
     *
     */
    public Entity(double worldX, double worldY, int width, int height, int health, int speed){
        super(worldX, worldY, width, height);
        this.health = health;
        this.speed = speed;
    }

    /**
     * Returns the hitbox of the entity
     * @return Rectangle
     */
    public Rectangle2D.Double getHitBox(){
        return new Rectangle2D.Double(worldX + hitBoxLeftOffset, worldY, width - hitBoxLeftOffset - hitBoxRightOffset, height);
    }


    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public int getHitBoxLeftOffset() {
        return hitBoxLeftOffset;
    }

    public void setHitBoxLeftOffset(int hitBoxLeftOffset) {
        this.hitBoxLeftOffset = hitBoxLeftOffset;
    }

    public int getHitBoxRightOffset() {
        return hitBoxRightOffset;
    }

    public void setHitBoxRightOffset(int hitBoxRightOffset) {
        this.hitBoxRightOffset = hitBoxRightOffset;
    }

}
