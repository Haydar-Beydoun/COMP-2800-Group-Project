import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


/**
 *
 * Class to initialize the player.
 * @author Rayyan
 * @author Haydar
 */
public class Player extends Entity{
    private int[] upBinds = {KeyEvent.VK_UP, KeyEvent.VK_W};
    private int[] downBinds = {KeyEvent.VK_DOWN, KeyEvent.VK_S};
    private int[] leftBinds = {KeyEvent.VK_LEFT, KeyEvent.VK_A};
    private int[] rightBinds = {KeyEvent.VK_RIGHT, KeyEvent.VK_D};
    private Keyboard keyboard = GameCanvas.keyboard;

    /**
     * Constructs a new player.
     * @see Entity
     */
    public Player(int x,int y, int width,int height, int health, int speed){
        super(x, y, 20, 20, health, speed);
        initPlayer();
    }

    private void initPlayer(){
        this.x = GameCanvas.WIDTH / 2;
        this.y = GameCanvas.HEIGHT / 2;
    }

    public void update(){
        collisionChecker.checkCollision();
        move();
    }

    private void move(){
        if(keyboard.isPressed(upBinds) && !collisionChecker.top){
            y -= speed;
        }
        if(keyboard.isPressed(downBinds) && !collisionChecker.bottom){
            y += speed;
        }
        if(keyboard.isPressed(leftBinds) && !collisionChecker.left){
            x -= speed;
        }
        if(keyboard.isPressed(rightBinds) && !collisionChecker.right){
            x += speed;
        }
    }

}
