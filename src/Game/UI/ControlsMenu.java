package Game.UI;

import Game.GameCanvas;
import Utils.ImageLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ControlsMenu {

    private Button backButton;
    private BufferedImage background;


    public ControlsMenu(){
        background = ImageLoader.loadImage("src/resources/UI/controlMenuBackground.png");

        initButtons();
    }

    private void initButtons(){
        backButton = new Button(20, GameCanvas.HEIGHT - 80, 124, 60
                , ImageLoader.loadImage("src/resources/UI/backButton.png"), ImageLoader.loadImage("src/resources/UI/backButtonHovered.png"));


    }

    public void update(){

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(background, 0, 0, GameCanvas.WIDTH, GameCanvas.HEIGHT, null);

        backButton.draw(g2d);
    }

    public void mousePressed(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        if(backButton.isOnButton(point)){
            backButton.playClick();
            GameCanvas.gameState = GameCanvas.GameState.SETTINGS_MENU;
        }
    }

    public void mouseMoved(MouseEvent e) {
        backButton.updateSprite(new Point(e.getX(), e.getY()));
    }

}
