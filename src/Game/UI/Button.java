package Game.UI;

import Game.GameCanvas;
import Utils.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Button class to create buttons.
 * <br>
 * Create buttons with images.
 */
public class Button {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private BufferedImage button;
    private BufferedImage buttonHovered;
    private BufferedImage current;
    private Sound clickSound = new Sound("/resources/sound_effects/mouseClick.wav");

    /**
     * Constructor of the Button class.
     * @param x X position on the Menu.
     * @param y Y position on the Menu.
     * @param width Width of the button.
     * @param height Height of the button.
     * @param button Image of the button.
     * @param buttonHovered Image of the button when the mouse is on top of it.
     */
    public Button(int x, int y, int width, int height, BufferedImage button, BufferedImage buttonHovered){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.button = button;
        this.buttonHovered = buttonHovered;
        current = button;

    }

    /**
     * Checks if the mouse is on top of the button.
     * @param point
     * @return boolean
     */
    public boolean isOnButton(Point point){
        return new Rectangle((int)(x * GameCanvas.gameScaleWidth), (int) (y  * GameCanvas.gameScaleHeight), (int) (width * GameCanvas.gameScaleWidth), (int) (height * GameCanvas.gameScaleHeight)).contains(point);
    }

    public void update(){

    }

    /**
     * Places button image
     * @param button
     */
    public void setButton(BufferedImage button) {
        this.button = button;
        current = button;
    }

    /**
     * Changes the image when the mouse is on top of the button.
     * @param buttonHovered
     */
    public void setButtonHovered(BufferedImage buttonHovered) {
        this.buttonHovered = buttonHovered;
        current = button;
    }

    /**
     * Plays sound when the button is clicked.
     */
    public void playClick(){
        clickSound.play();
    }

    /**
     * Updates the sprite of the button.
     * @param point
     */
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
