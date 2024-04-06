package Game.UI;

import Game.GameCanvas;
import Utils.ImageLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Start Menu class to create start menu.
 * <br>
 * Create start menu with images.
 * <br>
 * Includes buttons to play, go to options menu, and quit.
 */
public class StartMenu {
    private Button playButton;
    private Button optionMenu;
    private Button quitButton;
    private BufferedImage background;
    private ImageLoader imageLoader = new ImageLoader();
    private GameCanvas canvas;

    /**
     * Constructor of the Start Menu class.
     * @param canvas The canvas of the game (Used for saving the game).
     */
    public StartMenu(GameCanvas canvas){
        background = imageLoader.loadImage("/resources/UI/startMenuBackground.png");
        this.canvas = canvas;

        initButtons();
    }

    private void initButtons(){
        playButton = new Button((GameCanvas.WIDTH - 248)/2, GameCanvas.HEIGHT / 2 - 150, 248, 120
                , imageLoader.loadImage("/resources/UI/playButton.png"), imageLoader.loadImage("/resources/UI/playButtonHovered.png"));

        optionMenu = new Button((GameCanvas.WIDTH - 312)/2, GameCanvas.HEIGHT / 2, 312, 120
                , imageLoader.loadImage("/resources/UI/optionMenu.png"), imageLoader.loadImage("/resources/UI/optionMenuHovered.png"));

        quitButton = new Button((GameCanvas.WIDTH - 244)/2, GameCanvas.HEIGHT / 2 + 150, 244, 120
                , imageLoader.loadImage("/resources/UI/quitButton.png"), imageLoader.loadImage("/resources/UI/quitButtonHovered.png"));
    }

    public void update(){

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(background, 0, 0, GameCanvas.WIDTH, GameCanvas.HEIGHT, null);

        playButton.draw(g2d);
        optionMenu.draw(g2d);
        quitButton.draw(g2d);
    }

    public void mousePressed(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        if(playButton.isOnButton(point)){
            GameCanvas.gameState = GameCanvas.GameState.LEVEL_SELECT_MENU;
            playButton.playClick();
        }
        else if(optionMenu.isOnButton(point)){
            GameCanvas.gameState = GameCanvas.GameState.SETTINGS_MENU;
            playButton.playClick();
        }
        else if(quitButton.isOnButton(point)){
            playButton.playClick();
            canvas.save();
            System.exit(0);
        }
    }

    public void mouseMoved(MouseEvent e) {
        playButton.updateSprite(new Point(e.getX(), e.getY()));
        optionMenu.updateSprite(new Point(e.getX(), e.getY()));
        quitButton.updateSprite(new Point(e.getX(), e.getY()));
    }

}
