package Game.UI;

import Game.GameCanvas;
import Utils.ImageLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class StartMenu {
    private Button playButton;
    private Button optionMenu;
    private Button quitButton;
    private BufferedImage background;

    public StartMenu(){
        background = ImageLoader.loadImage("src/resources/UI/startMenuBackground.png");

        initButtons();
    }

    private void initButtons(){
        playButton = new Button((GameCanvas.WIDTH - 248)/2, GameCanvas.HEIGHT / 2 - 150, 248, 120
                , ImageLoader.loadImage("src/resources/UI/playButton.png"), ImageLoader.loadImage("src/resources/UI/playButtonHovered.png"));

        optionMenu = new Button((GameCanvas.WIDTH - 312)/2, GameCanvas.HEIGHT / 2, 312, 120
                , ImageLoader.loadImage("src/resources/UI/optionMenu.png"), ImageLoader.loadImage("src/resources/UI/optionMenuHovered.png"));

        quitButton = new Button((GameCanvas.WIDTH - 244)/2, GameCanvas.HEIGHT / 2 + 150, 244, 120
                , ImageLoader.loadImage("src/resources/UI/quitButton.png"), ImageLoader.loadImage("src/resources/UI/quitButtonHovered.png"));
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
            GameCanvas.save();
            System.exit(0);
        }
    }

    public void mouseMoved(MouseEvent e) {
        playButton.updateSprite(new Point(e.getX(), e.getY()));
        optionMenu.updateSprite(new Point(e.getX(), e.getY()));
        quitButton.updateSprite(new Point(e.getX(), e.getY()));
    }

}
