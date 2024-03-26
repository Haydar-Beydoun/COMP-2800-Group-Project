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

    public void update(){
        move();
    }

    public void jump(){
        if(!inAir) {
            vy = jumpSpeed;
            inAir = true;
        }
    }

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
