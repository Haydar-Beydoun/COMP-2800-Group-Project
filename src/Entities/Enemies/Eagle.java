package Entities.Enemies;

import Abstracts.Enemy;
import Entities.Player;
import Game.CollisionChecker;
import Game.GameCanvas;
import Game.Level.Tile;
import Utils.Animator;
import Utils.SpriteSheet;

import java.awt.*;
import java.util.Arrays;


public class Eagle extends Enemy {
    public Tile[][] tilemap;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int dirX = 1;
    private int dirY = 1;
    private Animator idleAnimator;
    private CollisionChecker collisionChecker;

    public Eagle(int startX,int startY, int endX, int endY, int width,int height, int health, int speed, Tile[][] tileMap){
        super(startX, startY, width, height, health, speed);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.tilemap = tileMap;
        this.spriteSheet = new SpriteSheet("/resources/entities/spritesheets/eagle-attack.png", 1,4,35, 41);

        if(endX < startX){
            int temp = endX;
            this.endX = startX;
            this.startX = temp;
            worldX = startX;
        }
        if(endY < startY){
            int temp = endY;
            this.endY = startY;
            this.startY = temp;
            worldY = startY;
        }

        collisionChecker = new CollisionChecker(tileMap);

        initEagle();
    }

    public void initEagle(){
        idleAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,0, 4), 0 , 7);
        currentAnimator = idleAnimator;
    }

    @Override
    public void update(){
        if(!currentAnimator.equals(deathAnimator))
            move();
        currentAnimator.update();

    }

    public void move(){
        // Movement in the x-dir
        if(startX != endX) {
            if (worldX > endX || worldX < startX || collisionChecker.isColliding(getHitBox(), vx, 0)) {
                dirX *= -1;
            }

            vx = speed * dirX;
        }

        // Movement in the y-dir
        if(startY != endY){
            if(worldY > endY || worldY < startY || collisionChecker.isColliding(getHitBox(), 0, vy)){
                dirY *= -1;
            }

            vy = speed * dirY;
        }

        worldX += vx;
        worldY += vy;
    }

    public void draw(Graphics2D g2d, int offsetX, int offsetY){
        g2d.drawImage(currentAnimator.currentFrame, (int) worldX + offsetX, (int) worldY + offsetY, width, height, null);
//        g2d.setColor(Color.MAGENTA);
//        g2d.drawRect((int)worldX + offsetX,(int)worldY + offsetY,width,height);
    }




}
