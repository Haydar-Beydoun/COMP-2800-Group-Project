import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * Abstract class to initialize entity objects.
 * @author Rayyan
 * @author Haydar
 */

public abstract class Entity {
    int health;
    double worldX;
    double worldY;
    int screenX;
    int screenY;
    double vx = 0;
    double vy = 0;
    int width;
    int height;
    int speed;
    BufferedImage sprite;
    int leftHitBoxOffset = 0;
    int rightHitBoxOffset = 0;

    /**
     * Constructor of the entity
     * @param worldX X position on the canvas.
     * @param y Y position on the canvas.
     * @param width width of the hitBox.
     * @param height height of the hitBox.
     * @param health health of the entity.
     * @param speed speed of the entity.
     *
     */
    public Entity(double worldX, double y, int width, int height, int health, int speed){
        this.worldX = worldX;
        this.worldY = y;
        this.width = width;
        this.height = height;
        this.health = health;
        this.speed = speed;
    }

    /**
     * Optional constructor for sprite
     * @param sprite add a BufferedImage for the entity.
     *
     */
    public Entity(int worldX, int y, int width, int height, int health, int speed, BufferedImage sprite){
        this(worldX,y,width,height,health,speed);
        this.sprite = sprite;
    }

    /**
     * Returns a formatted decimal using the specified decimal pattern and argument.
     * @param g2d  draw the image/hitbox.
     */
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect(screenX, screenY, width, height);
    }

    public Rectangle2D.Double getHitBox(){
        return new Rectangle2D.Double(worldX, worldY, width, height);
    }

    public double getWorldX() {
        return worldX;
    }

    public double getWorldY() {
        return worldY;
    }

    public int getHealth() {
        return health;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }
}
