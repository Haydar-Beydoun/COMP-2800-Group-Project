import java.awt.event.KeyEvent;


/**
 *
 * Class to initialize the player.
 * @author Rayyan
 * @author Haydar
 */
public class Player extends Entity{
    // Binds
    private int[] jumpBinds = {KeyEvent.VK_SPACE, KeyEvent.VK_W, KeyEvent.VK_UP};
    private int[] leftBinds = {KeyEvent.VK_LEFT, KeyEvent.VK_A};
    private int[] rightBinds = {KeyEvent.VK_RIGHT, KeyEvent.VK_D};

    // States
    private boolean falling = false;
    private boolean jumping = false;
    private boolean inAir   = true;
    private boolean moving  = false;
    private boolean left    = false;
    private boolean right   = false;

    // Vertical velocities
    private double gravity = 0.2;
    private double jumpSpeed = -7.5;
    private double topCollisionFallSpeed = 1;

    // Utilities
    LevelLoader loader = new LevelLoader("src/resources/maps/level1.txt"); // FIX ME: move me somewhere better
    Level level = loader.getLevel();
    private CollisionChecker collisionChecker = new CollisionChecker(this, level);
    private Keyboard keyboard = GameCanvas.keyboard;

    /**
     * Constructs a new player.
     * @see Entity
     */
    public Player(int x,int y, int width,int height, int health, int speed){
        super(x, y, width, height, health, speed);
        initPlayer();
    }

    private void initPlayer(){//FIX MOI
        this.x = 200;
        this.y = GameCanvas.HEIGHT / 2;
    }

    public void update(){
        move();
    }

    public void jump(){
        if(inAir) {
            return;
        }
        else{
            vy = jumpSpeed;
            inAir = true;
        }
    }

    private void move(){
        vx = 0;

        System.out.println(inAir);
        if(keyboard.isPressed(leftBinds)){
            vx = -speed;
        }
        if(keyboard.isPressed(rightBinds)){
            vx = speed;
        }
        if(keyboard.isPressed(jumpBinds)){
            jump();
        }

        // Update x is placed within to prevent both x and y correction at the same time
        if(inAir){
            if(!collisionChecker.isColliding(x, y + vy, width, height)) {   // Moving in the y direction //FIX ME: take in hitbox dims instead
                vy += gravity;
                y += vy;
                updateX();
            }
            else{
                y = collisionChecker.getCollidingTileY(getHitBox(), vy);
                if(vy > 0){
                    inAir = false;
                    vy = 0;
                }
                else{
                    vy = topCollisionFallSpeed;
                }
                updateX();
            }
        }
        else{
            if(!collisionChecker.isBottomColliding(x, y, width, height)){
                inAir = true;
            }
            updateX();
        }


    }

    private void updateX(){
        if(!collisionChecker.isColliding(x + vx, y , width, height)) {   // Moving in the x direction//FIX ME: take in hitbox dims instead
            x += vx;
        }
        else{
            x = collisionChecker.getCollidingTileX(getHitBox(), vx);
        }
    }

}
