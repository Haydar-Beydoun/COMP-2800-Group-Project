package Abstracts;

import Utils.Animator;
import Utils.SpriteSheet;

import java.awt.geom.Rectangle2D;

/**
 *
 * Abstract class to initialize entities.
 * <br>
 * Entities are objects that can move and interact with the player.
 */

public abstract class Entity extends GameObject {
    public int health;
    public double vx = 0;
    public double vy = 0;
    public int speed;
    public int hitBoxLeftOffset = 0;
    public int hitBoxRightOffset = 0;
    public SpriteSheet spriteSheet;
    public Animator currentAnimator;

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
