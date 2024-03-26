import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;


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
    private boolean inAir   = false;
    private boolean moving  = false;
    private boolean left    = false;
    private boolean right   = false;
    private enum State{
        RIGHT,
        LEFT,
        JUMP,
        FALL,
        HURT,
        IDLE
    }
    private State state = State.IDLE;

    // Vertical velocities
    private double gravity = 0.2;
    private double jumpSpeed = -7.5;
    private double topCollisionFallSpeed = 1;

    // Utilities
    private CollisionChecker collisionChecker;
    private Keyboard keyboard = GameCanvas.keyboard;
    private Animator jumpAnimator;
    private Animator fallAnimator;
    private Animator runAnimator;
    private Animator idleAnimator;
    private Animator currentAnimator;


    /**
     * Constructs a new player.
     * @see Entity
     */
    public Player(int x,int y, int width,int height, int health, int speed, Tile[][] tileMap){
        super(x, y, width, height, health, speed);
        this.collisionChecker = new CollisionChecker(this, tileMap);
        this.spriteSheet = new SpriteSheet("src/resources/entities/spritesheets/player.png", 6,6,62);
        initPlayer();
    }

    private void initPlayer(){
        this.worldX = 200;
        this.worldY = GameCanvas.HEIGHT / 2;
        jumpAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,30, 31), 0 , 20);
        fallAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,31, 32), 0 , 20);
        runAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,6, 11), 0 , 20);
        idleAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,0, 4), 0 , 10);

    }

    public void draw(Graphics2D g2d){
//        g2d.setColor(Color.MAGENTA);
        g2d.fillRect(screenX, screenY, width, height);

        switch (state){
            case JUMP:
                currentAnimator = jumpAnimator;
                break;
            case FALL:
                currentAnimator = fallAnimator;
                break;
            case RIGHT:
                currentAnimator = runAnimator;
                break;
            default:
                currentAnimator = idleAnimator;
        }

        if(state == Player.State.LEFT){
            g2d.drawImage(currentAnimator.currentFrame, screenX + width/2, screenY, -width, height, null);
        }
        else{
            g2d.drawImage(currentAnimator.currentFrame, screenX, screenY, width, height, null);
        }
        //currentAnimator.update();


    }

    /**
     * Update play position and state
     */
    public void update(){
        move();
        jumpAnimator.update();
        idleAnimator.update();
        runAnimator.update();
        setState();
    }

    /**
     * Player jump
     */
    public void jump(){
        if(!inAir) {
            vy = jumpSpeed;
            inAir = true;
        }
    }

    /**
     * Player move function
     */
    private void move(){
        vx = 0;

        if(keyboard.isPressed(leftBinds)){
            vx = -speed;
        }
        if(keyboard.isPressed(rightBinds)){
            vx = speed;
        }
        if(keyboard.isPressed(jumpBinds)){
            jump();
        }
        if(keyboard.isPressed(KeyEvent.VK_ESCAPE)){ //TEMP FOR TESTING
            initPlayer();
        }

        updateY();
        updateX();
    }

    /**
     * Player update Y
     */
    private void updateY(){

        if(inAir){
            if(!collisionChecker.isColliding(worldX, worldY + vy, width, height)) {   // Moving in the y direction //FIX ME: take in hitbox dims instead
                vy += gravity;
                worldY += vy;
            }
            else{
                worldY = collisionChecker.getCollidingTileY(getHitBox(), vy);
                if(vy > 0){
                    inAir = false;
                    vy = 0;
                }
                else{
                    vy = topCollisionFallSpeed;
                }
            }
        }

        if(!collisionChecker.isBottomColliding(worldX, worldY + vy, width, height)){
            inAir = true;
        }
        else{
            worldY = collisionChecker.getCollidingTileY(getHitBox(), vy);

            inAir = false;
            vy = 0;
        }
    }

    private void updateX(){
        if(!collisionChecker.isColliding(worldX + vx, worldY, width, height)) {   // Moving in the x direction//FIX ME: take in hitbox dims instead
            worldX += vx;
        }
        else{
           worldX = collisionChecker.getCollidingTileX(getHitBox(), vx);
        }
    }

    public void setState(){
        if(Math.floor(vy) < 0){
            state = State.JUMP;
        }
        else if(Math.floor(vy) > 0){
            state = State.FALL;
        }
        else if(vx > 0){
            state = State.RIGHT;
        }
        else if(vx < 0){
            state = State.LEFT;
        }
        else{
            state = State.IDLE;
        }
        System.out.println(state);
        //System.out.println(vy);
    }

}
