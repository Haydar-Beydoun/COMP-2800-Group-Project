import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
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
    private double gravity = 0.3;
    private double jumpSpeed = -12;
    private double bounceSpeed = -12;
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
        this.collisionChecker = new CollisionChecker(tileMap);
        this.spriteSheet = new SpriteSheet("src/resources/entities/spritesheets/player.png", 6,6,128, 128);

        setHitBoxLeftOffset(10);
        setHitBoxRightOffset(8);

        initPlayer();
    }

    private void initPlayer(){
        this.worldX = 200;
        this.worldY = GameCanvas.HEIGHT;

        idleAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,0, 4), 0 , 7);
        runAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,6, 12), 0 , 5);
        jumpAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,30, 31), 0 , 20);
        fallAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,31, 32), 0 , 20);

    }

    public void draw(Graphics2D g2d){
        switch (state){
            case JUMP:
                currentAnimator = jumpAnimator;
                break;
            case FALL:
                currentAnimator = fallAnimator;
                break;
            case RIGHT, LEFT:
                currentAnimator = runAnimator;
                break;
            default:
                currentAnimator = idleAnimator;
        }

        if(left){
            g2d.drawImage(currentAnimator.currentFrame, screenX + width, screenY, -currentAnimator.currentFrame.getWidth(), currentAnimator.currentFrame.getHeight(), null);
        }
        else{
            g2d.drawImage(currentAnimator.currentFrame, screenX, screenY, null);
        }

        g2d.setColor(Color.MAGENTA);
      // g2d.fillRect((int) screenX + hitBoxLeftOffset, (int) screenY + 32, (int) getHitBox().width, (int) getHitBox().height);

    }

    /**
     * Update play position and state
     */
    public void update(){
        currentAnimator.update();
        move();
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

    public void bounce(){
        vy = bounceSpeed;
        inAir = true;
    }

    /**
     * Player move function
     */
    private void move(){
        vx = 0;

        if(keyboard.isPressed(leftBinds)){
            vx = -speed;
            left = true;
        }
        if(keyboard.isPressed(rightBinds)){
            vx = speed;
            left = false;
        }
        if(keyboard.isPressed(jumpBinds)){
            jump();
        }
        if(keyboard.isPressed(KeyEvent.VK_ESCAPE)){ //TEMP FOR TESTING
            initPlayer();
        }
        if(keyboard.isPressed(KeyEvent.VK_I)){ //TEMP FOR TESTING
            iframe = false;
        }

        updateY();
        updateX();
    }

    public boolean isKillingEnemy(Rectangle2D.Double enemyHitBox){
//        if(iframe)
//            return false;

        if(getHitBox().intersects(enemyHitBox)){
            if(state == State.FALL && getHitBox().intersects(enemyHitBox.x, enemyHitBox.y, enemyHitBox.width, 1)){
                bounce();
                return true;
            }
            else{
                if(state == State.JUMP && worldY >= enemyHitBox.y + enemyHitBox.height) {
                    vy = 0;
                }
//                if(getWorldX() + width - hitBoxLeftOffset <= enemyHitBox.x || getWorldX() >=  enemyHitBox.x + enemyHitBox.width){
//                    vx *=-1;
//                }

                //player gets hurt
                health -=10;
               // iframe = true;
                return false;
            }
        }

        return false;
    }

    /**
     * Player update Y
     */
    private void updateY(){

        if(inAir){
            if(!collisionChecker.isColliding(getHitBox(), 0, vy)) {   // Moving in the y direction //FIX ME: take in hitbox dims instead
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

        if(!collisionChecker.isBottomColliding(getHitBox(), 0, vy)){
            inAir = true;
        }
        else{
            worldY = collisionChecker.getCollidingTileY(getHitBox(), vy);

            inAir = false;
            vy = 0;
        }
    }

    /**
     * Player Update X
     */
    private void updateX(){
        if(!collisionChecker.isColliding(getHitBox(), vx, 0)) {   // Moving in the x direction
            worldX += vx;
        }
        else{
            worldX = collisionChecker.getCollidingTileX(getHitBox(), hitBoxLeftOffset, vx);
        }
    }

    /**
     * Set the state the player is in during movement
     */
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
    }

}
