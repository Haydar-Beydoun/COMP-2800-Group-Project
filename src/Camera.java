import javax.crypto.EncryptedPrivateKeyInfo;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera {
    private int offsetx = 0, offsety = 0;
    Level level;
    private BufferedImage levelImage;
    Player player;
    Entity[] objectList;


    public Camera(Level level, Player player){
        this.level = level;
        this.player = player;
        this.levelImage = level.getLevelImage();
    }


    public void cameraX(){
        if(player.getWorldX() <= (GameCanvas.WIDTH / 2)){
            offsetx = 0;
            player.screenX = (int) player.worldX;
            //player.screenY = (int) player.worldY;
        }
        else if(player.getWorldX() >= (levelImage.getWidth() -  GameCanvas.WIDTH / 2)){
            player.screenX = (int) player.worldX + (GameCanvas.WIDTH) - levelImage.getWidth();
            //player.screenY = (int) player.worldY;
        }
        else{
            offsetx = (int) ((GameCanvas.WIDTH / 2) -(player.getWorldX()));
            player.screenX = GameCanvas.WIDTH / 2;
            //player.screenY = (int) player.worldY;
        }
    }

    public void cameraY(){
        System.out.println(player.screenY);
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
        cameraX();
        cameraY();

        g2d.drawImage(levelImage,  offsetx, offsety, levelImage.getWidth(), levelImage.getHeight(), null);

        player.draw(g2d);

    }
}
