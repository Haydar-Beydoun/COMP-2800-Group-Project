package Game.UI;

import Game.GameCanvas;
import Utils.ImageLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Controls Menu class to create controls menu.
 * <br>
 * Create controls menu with images.
 */
public class ControlsMenu {

    private Button backButton;
    private BufferedImage background;
    private ImageLoader imageLoader = new ImageLoader();

    /**
     * Constructor of the Controls Menu class.
     */
    public ControlsMenu(){
        background = imageLoader.loadImage("/resources/UI/controlMenuBackground.png");

        initButtons();
    }

    private void initButtons(){
        backButton = new Button(20, GameCanvas.HEIGHT - 80, 124, 60
                , imageLoader.loadImage("/resources/UI/backButton.png"), imageLoader.loadImage("/resources/UI/backButtonHovered.png"));


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
