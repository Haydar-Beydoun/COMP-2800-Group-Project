package Game.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage button, buttonHovered;

    public Button(int x, int y, int width, int height, BufferedImage button, BufferedImage buttonHovered){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.button = button;
        this.buttonHovered = buttonHovered;

    }

    public boolean isOnButton(Point point){
        return new Rectangle(x, y, width, height).contains(point);
    }

    public void update(){

    }

    public void draw(Graphics2D g2d){
//        if(isOnButton(point)){
//            g2d.drawImage(buttonHovered, x, y, width, height, null);
//        }
//        else{
            g2d.drawImage(button, x, y, width, height, null);
//        }
    }
}
