package Game;

import Abstracts.Collectable;
import Abstracts.Enemy;
import Entities.Player;
import Game.Level.Level;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Camera {
    private int offsetx = 0;
    private int offsety = 0;
    Level level;
    private BufferedImage levelImage;
    Player player;
    ArrayList<Enemy> enemies;
    ArrayList<Collectable> collectables;

    public Camera(Level level, Player player, ArrayList<Enemy> enemies, ArrayList<Collectable> collectables){
        this.level = level;
        this.player = player;
        this.enemies = enemies;
        this.collectables = collectables;
        this.levelImage = level.getLevelImage();
    }


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
