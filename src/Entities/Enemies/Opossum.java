package Entities.Enemies;

import Game.CollisionChecker;
import Game.Level.Tile;
import Utils.Animator;
import Utils.SpriteSheet;

import java.awt.*;
import java.util.Arrays;


public class Opossum extends Enemy {
    public Tile[][] tilemap;
    private Animator animator;
    private int dirX = 1;
    private CollisionChecker collisionChecker;
    private boolean inAir = false;
    private double gravity = 0.3;


    public Opossum(double worldX, double worldY, int width, int height, int health, int speed, Tile[][] tileMap) {
        super(worldX, worldY, width, height, health, speed);
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = width;
        this.height = height;
        this.health = health;
        this.speed = speed;
        this.tilemap = tileMap;

        setHitBoxLeftOffset(10);
        setHitBoxRightOffset(10);

        this.spriteSheet = new SpriteSheet("src/resources/entities/spritesheets/oposum.png", 1,6,36, 28);

        collisionChecker = new CollisionChecker(tileMap);
        initOpossum();
    }

    public void initOpossum(){
        animator = new Animator(Arrays.copyOfRange(spriteSheet.images ,0, 5), 0 , 7);
        currentAnimator = animator;
    }

    @Override
    public void update(){
        if(!currentAnimator.equals(deathAnimator))
            move();
        currentAnimator.update();

    }

    public void move(){
        vx = speed * dirX;

        updateX();
        updateY();
    }
    private void updateY(){

        if(inAir){
            if(!collisionChecker.isColliding(getHitBox(), 0, vy)) {   // Moving in the y direction //FIX ME: take in hitbox dims instead
                vy += gravity;
                worldY += vy;
            }
            else{
                worldY = collisionChecker.getCollidingTileY(getHitBox(), vy);
                inAir = false;
                vy = 0;
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
     * Opposum Update X
     */
    private void updateX(){
        if(!collisionChecker.isColliding(getHitBox(), vx, 0)) {   // Moving in the x direction
            worldX += vx;
        }
        else{
            worldX = collisionChecker.getCollidingTileX(getHitBox(), hitBoxLeftOffset, vx);
            dirX *= - 1;
        }
        if(!collisionChecker.isPathWalkable(getHitBox(), vx, 0)){
            dirX *= - 1;
        }
    }

    public void draw(Graphics2D g2d, int offsetX, int offsetY){
//        g2d.setColor(Color.MAGENTA);
//        g2d.drawRect((int)worldX + offsetX + hitBoxLeftOffset,(int) worldY + offsetY,width - hitBoxLeftOffset - hitBoxRightOffset,height);

        if(vx > 0)
            g2d.drawImage(currentAnimator.currentFrame, (int) worldX + offsetX + width, (int) worldY + offsetY, -width, height, null);
        else
            g2d.drawImage(currentAnimator.currentFrame, (int) worldX + offsetX , (int) worldY + offsetY, width, height, null);
//
//        Rectangle2D.Double hitbox = getHitBox();
//        double x = hitbox.x + vx;
//        double y = hitbox.y +vy;
//        double width = hitbox.width;
//        double height = hitbox.height;
//
//        double leftX = x;
//        double rightX=  x + width;
//        double bottomY =  (y + height);
//
//        int leftCol   = (int) (leftX / Game.GameCanvas.TILE_SIZE);
//        int rightCol  = (int) (rightX / Game.GameCanvas.TILE_SIZE);
//        int bottomRow = (int) (bottomY / Game.GameCanvas.TILE_SIZE) + 1;
//
//        Game.Level.Tile bottomLeftTile  = tilemap[leftCol][bottomRow];
//        Game.Level.Tile bottomRightTile = tilemap[rightCol][bottomRow];
//
//        g2d.setColor(Color.RED);
//        g2d.drawRect(bottomLeftTile.getX() + offsetX, bottomLeftTile.getY() + offsetY, 64,64);
//        g2d.setColor(Color.orange);
//        g2d.drawRect(bottomRightTile.getX() + offsetX,bottomRightTile.getY() + offsetY,64,64);
    }


}
