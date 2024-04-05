package Game.UI;

import Game.GameCanvas;
import Utils.ImageLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class SettingsMenu {
    private Button fullScreenOnButton;
    private Button fullScreenOffButton;
    private Button newSaveButton;
    private Button backButton;
    private Button controlsButton;
    private BufferedImage background;
    private GameCanvas canvas;

    public SettingsMenu(GameCanvas canvas){
        background = ImageLoader.loadImage("src/resources/UI/optionMenuBackground.png");
        this.canvas = canvas;

        initButtons();
    }

    private void initButtons(){
        fullScreenOnButton = new Button((GameCanvas.WIDTH - 488)/2, GameCanvas.HEIGHT / 2 - 150, 488, 120
                , ImageLoader.loadImage("src/resources/UI/fullScreenButton.png"), ImageLoader.loadImage("src/resources/UI/fullScreenButtonHovered.png"));

        fullScreenOffButton = new Button((GameCanvas.WIDTH - 488)/2, GameCanvas.HEIGHT / 2 - 150, 488, 120
                , ImageLoader.loadImage("src/resources/UI/fullScreenButtonOff.png"), ImageLoader.loadImage("src/resources/UI/fullScreenButtonOffHovered.png"));

        newSaveButton = new Button((GameCanvas.WIDTH - 364)/2, GameCanvas.HEIGHT / 2, 364, 120
                , ImageLoader.loadImage("src/resources/UI/newSave.png"), ImageLoader.loadImage("src/resources/UI/newSaveHovered.png"));

        controlsButton = new Button((GameCanvas.WIDTH - 372)/2, GameCanvas.HEIGHT / 2 + 150, 372, 120
                , ImageLoader.loadImage("src/resources/UI/controls.png"), ImageLoader.loadImage("src/resources/UI/controlsHovered.png"));

        backButton = new Button(20, GameCanvas.HEIGHT - 80, 124, 60
                , ImageLoader.loadImage("src/resources/UI/backButton.png"), ImageLoader.loadImage("src/resources/UI/backButtonHovered.png"));


    }

    public void update(){

    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(background, 0, 0, GameCanvas.WIDTH, GameCanvas.HEIGHT, null);

        if(!GameCanvas.isFullScreen)
            fullScreenOnButton.draw(g2d);
        else
            fullScreenOffButton.draw(g2d);

        newSaveButton.draw(g2d);
        backButton.draw(g2d);
        controlsButton.draw(g2d);
    }

    public void mousePressed(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        if(fullScreenOnButton.isOnButton(point)){
            fullScreenOnButton.playClick();
            if(GameCanvas.isFullScreen) {
                canvas.setMinScreen();
                GameCanvas.isFullScreen = false;
            }
            else {
                canvas.setFullScreen();
                GameCanvas.isFullScreen = true;
            }
        }
        else if(newSaveButton.isOnButton(point)){
            newSaveButton.playClick();
            GameCanvas.currentLevelProgress = 1;
        }
        else if(controlsButton.isOnButton(point)){
            controlsButton.playClick();
            GameCanvas.gameState = GameCanvas.GameState.CONTROLS_MENU;
        }
        else if(backButton.isOnButton(point)){
            backButton.playClick();
            GameCanvas.gameState = GameCanvas.GameState.START_MENU;
        }
    }

    public void mouseMoved(MouseEvent e) {
        fullScreenOnButton.updateSprite(new Point(e.getX(), e.getY()));
        fullScreenOffButton.updateSprite(new Point(e.getX(), e.getY()));
        newSaveButton.updateSprite(new Point(e.getX(), e.getY()));
        controlsButton.updateSprite(new Point(e.getX(), e.getY()));
        backButton.updateSprite(new Point(e.getX(), e.getY()));
    }

}
