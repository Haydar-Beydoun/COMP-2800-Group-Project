package Game.UI;

import Game.GameCanvas;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage button, buttonHovered, current;

    public Button(int x, int y, int width, int height, BufferedImage button, BufferedImage buttonHovered){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.button = button;
        this.buttonHovered = buttonHovered;
        current = button;

    }

    public boolean isOnButton(Point point){
        return new Rectangle((int)(x * GameCanvas.gameScaleWidth), (int) (y  * GameCanvas.gameScaleHeight), (int) (width * GameCanvas.gameScaleWidth), (int) (height * GameCanvas.gameScaleHeight)).contains(point);
    }

    public void update(){

    }

    public void updateSprite(Point point){
        if(isOnButton(point)){
            current = buttonHovered;
        }
        else{
            current = button;
        }
    }

    public void draw(Graphics2D g2d){
            g2d.drawImage(current, x, y, width, height, null);
    }
}
