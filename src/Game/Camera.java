package Game;

import Abstracts.Collectable;
import Abstracts.Enemy;
import Entities.Player;
import Game.Level.Level;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Camera class to create camera to follow player.
 */
public class Camera {
    private int offsetx = 0;
    private int offsety = 0;
    Level level;
    private BufferedImage levelImage;
    Player player;
    ArrayList<Enemy> enemies;
    ArrayList<Collectable> collectables;

    /**
     * Constructor of the Camera class.
     * @param level Used for getting the image background.
     * @param player Used for getting the player's position.
     * @param enemies Used for getting the enemies position.
     * @param collectables Used for getting the collectable positions.
     */
    public Camera(Level level, Player player, ArrayList<Enemy> enemies, ArrayList<Collectable> collectables){
        this.level = level;
        this.player = player;
        this.enemies = enemies;
        this.collectables = collectables;
        this.levelImage = level.getLevelImage();
    }

    /**
     * Moves the camera on the X-axis.
     */
    public void cameraX(){
        if(player.getWorldX() <= (GameCanvas.WIDTH / 2)){
            offsetx = 0;
            player.screenX = (int) player.worldX;
        }
        else if(player.getWorldX() >= (levelImage.getWidth() -  GameCanvas.WIDTH / 2)){
            player.screenX = (int) player.worldX + (GameCanvas.WIDTH) - levelImage.getWidth();
        }
        else{
            offsetx = (int) ((GameCanvas.WIDTH / 2) -(player.getWorldX()));
            player.screenX = GameCanvas.WIDTH / 2;
        }
    }

    /**
     * Moves the camera on the Y-axis.
     */
    public void cameraY(){
        if(player.getWorldY() <= (GameCanvas.HEIGHT / 2)){
            offsety = 0;
            player.screenY = (int) player.worldY;
        }
        else if(player.getWorldY() >= (levelImage.getHeight() -  GameCanvas.HEIGHT / 2)){
            player.screenY = (int) player.worldY + (GameCanvas.HEIGHT) - levelImage.getHeight();
        }
        else{
            offsety = (int) ((GameCanvas.HEIGHT / 2) -(player.getWorldY()));
            player.screenY = GameCanvas.HEIGHT / 2;
        }
    }

    public void draw(Graphics2D g2d){
        // Determining offset
        cameraX();
        cameraY();

        // Drawing level
        g2d.drawImage(levelImage,  offsetx, offsety, levelImage.getWidth(), levelImage.getHeight(), null);

        // Drawing collectables
        for(int i=0; i < collectables.size();i++){
            Collectable collectable = collectables.get(i);

            if(Math.abs(collectable.worldX - player.worldX) < GameCanvas.WIDTH && Math.abs(collectable.worldY - player.worldY) < GameCanvas.HEIGHT)
                collectable.draw(g2d, offsetx, offsety);
        }

        // Drawing entities
        for(int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);

            if(Math.abs(enemy.worldX - player.worldX) < GameCanvas.WIDTH && Math.abs(enemy.worldY - player.worldY) < GameCanvas.HEIGHT)
                enemy.draw(g2d, offsetx, offsety);

        }

        // Drawing player
        player.draw(g2d);
    }

}
