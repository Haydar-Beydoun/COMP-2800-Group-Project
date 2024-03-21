import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * Abstract class to initialize entity objects.
 * @author Rayyan
 * @author Haydar
 */

public abstract class Entity {
    int health;
    double x;
    double y;
    double vx;
    double vy;
    int width;
    int height;
    int speed;
    BufferedImage sprite;
    int leftHitBoxOffset = 0;
    int rightHitBoxOffsert = 0;

    /**
     * Constructor of the entity
     * @param x X position on the canvas.
     * @param y Y position on the canvas.
     * @param width width of the hitBox.
     * @param height height of the hitBox.
     * @param health health of the entity.
     * @param speed speed of the entity.
     *
     */
    public Entity(double x,double y, int width,int height, int health, int speed){
        this.x = x;
        this.y = y;
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
    public Entity(int x,int y, int width,int height, int health, int speed, BufferedImage sprite){
        this(x,y,width,height,health,speed);
        this.sprite = sprite;
    }

    /**
     * Returns a formatted decimal using the specified decimal pattern and argument.
     * @param g2d  draw the image/hitbox.
     */
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.BLUE);
        g2d.fillRect((int) x,(int) y,width,height);

        //Drawing hitbox
        g2d.drawRect((int) x,(int) y, width, height);
    }

    public Rectangle getHitBox(){
        return new Rectangle((int) x, (int) y, width, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
