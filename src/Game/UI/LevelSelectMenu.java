package Game.UI;

import Game.GameCanvas;
import Utils.ImageLoader;
import Utils.Sound;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class LevelSelectMenu {
    private Button levelOneButton;
    private Button levelTwoButton;
    private Button levelThreeButton;
    private Button backButton;
    private BufferedImage background;
    private GameCanvas canvas;
    private int currLevel = GameCanvas.currentLevelProgress;
    private Sound levelLockedSound = new Sound("/resources/sound_effects/levelLocked.wav");
    private ImageLoader imageLoader = new ImageLoader();

    public LevelSelectMenu(GameCanvas canvas){
        background = imageLoader.loadImage("/resources/UI/levelSelectMenuBackground.png");
        this.canvas = canvas;

        initButtons();
    }

    private void initButtons(){
        levelOneButton = new Button((GameCanvas.WIDTH - 248)/2, GameCanvas.HEIGHT / 2 - 150, 248, 120
                , imageLoader.loadImage("/resources/UI/level1.png"), imageLoader.loadImage("/resources/UI/level1Hovered.png"));

        levelTwoButton = new Button((GameCanvas.WIDTH - 248)/2, GameCanvas.HEIGHT / 2, 248, 120
                , imageLoader.loadImage("/resources/UI/level2.png"), imageLoader.loadImage("/resources/UI/level2Hovered.png"));

        levelThreeButton = new Button((GameCanvas.WIDTH - 248)/2, GameCanvas.HEIGHT / 2 + 150, 248, 120
                , imageLoader.loadImage("/resources/UI/level3.png"), imageLoader.loadImage("/resources/UI/level3Hovered.png"));

        backButton = new Button(20, GameCanvas.HEIGHT - 80, 124, 60
                                , imageLoader.loadImage("/resources/UI/backButton.png"), imageLoader.loadImage("/resources/UI/backButtonHovered.png"));

        updateButtons();

    }

    public void update(){
        if(currLevel != GameCanvas.currentLevelProgress){
            currLevel = GameCanvas.currentLevelProgress;
            updateButtons();
        }
    }

    public void updateButtons(){
        if(GameCanvas.currentLevelProgress == 1){
            // Level 1 incomplete
            levelOneButton.setButton(imageLoader.loadImage("/resources/UI/level1.png"));
            levelOneButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level1Hovered.png"));

            // Level 2 locked
            levelTwoButton.setButton(imageLoader.loadImage("/resources/UI/level2locked.png"));
            levelTwoButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level2lockedHovered.png"));

            // Level 3 locked
            levelThreeButton.setButton(imageLoader.loadImage("/resources/UI/level3locked.png"));
            levelThreeButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level3lockedHovered.png"));
        }
        else if(GameCanvas.currentLevelProgress == 2){
            // Level 1 complete
            levelOneButton.setButton(imageLoader.loadImage("/resources/UI/level1complete.png"));
            levelOneButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level1completeHovered.png"));

            // Level 2 incomplete
            levelTwoButton.setButton(imageLoader.loadImage("/resources/UI/level2.png"));
            levelTwoButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level2Hovered.png"));

            // Level 3 locked
            levelThreeButton.setButton(imageLoader.loadImage("/resources/UI/level3locked.png"));
            levelThreeButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level3lockedHovered.png"));
        }
        else if(GameCanvas.currentLevelProgress == 3){
            // Level 1 complete
            levelOneButton.setButton(imageLoader.loadImage("/resources/UI/level1complete.png"));
            levelOneButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level1completeHovered.png"));

            // Level 2 complete
            levelTwoButton.setButton(imageLoader.loadImage("/resources/UI/level2complete.png"));
            levelTwoButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level2completeHovered.png"));

            // Level 3 incomplete
            levelThreeButton.setButton(imageLoader.loadImage("/resources/UI/level3.png"));
            levelThreeButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level3Hovered.png"));
        }
        else{
            // All levels completed
            levelOneButton.setButton(imageLoader.loadImage("/resources/UI/level1complete.png"));
            levelOneButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level1completeHovered.png"));

            levelTwoButton.setButton(imageLoader.loadImage("/resources/UI/level2complete.png"));
            levelTwoButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level2completeHovered.png"));

            levelThreeButton.setButton(imageLoader.loadImage("/resources/UI/level3complete.png"));
            levelThreeButton.setButtonHovered(imageLoader.loadImage("/resources/UI/level3completeHovered.png"));
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(background, 0, 0, GameCanvas.WIDTH, GameCanvas.HEIGHT, null);

        levelOneButton.draw(g2d);
        levelTwoButton.draw(g2d);
        levelThreeButton.draw(g2d);
        backButton.draw(g2d);
    }

    public void mousePressed(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        if(levelOneButton.isOnButton(point)){
            levelOneButton.playClick();
            canvas.stopHomeMusic();

            canvas.loadLevel("/resources/maps/level1/level1.txt");
            GameCanvas.currentLevel = 1;
            GameCanvas.gameState = GameCanvas.GameState.GAME;
        }
        else if(levelTwoButton.isOnButton(point)){
            if(GameCanvas.currentLevelProgress >= 2){
                levelTwoButton.playClick();
                canvas.stopHomeMusic();

                canvas.loadLevel("/resources/maps/level2/level2.txt");
                GameCanvas.currentLevel = 2;
                GameCanvas.gameState = GameCanvas.GameState.GAME;
            }
            else{
                levelLockedSound.play();
            }

        }
        else if(levelThreeButton.isOnButton(point)){
            if(GameCanvas.currentLevelProgress >= 3){
                levelThreeButton.playClick();
                canvas.stopHomeMusic();

                canvas.loadLevel("/resources/maps/level3/level3.txt");
                GameCanvas.currentLevel = 3;
                GameCanvas.gameState = GameCanvas.GameState.GAME;
            }
            else{
                levelLockedSound.play();
            }
        }
        else if(backButton.isOnButton(point)){
            backButton.playClick();
            GameCanvas.gameState = GameCanvas.GameState.START_MENU;
        }
    }

    public void mouseMoved(MouseEvent e) {
        levelOneButton.updateSprite(new Point(e.getX(), e.getY()));
        levelTwoButton.updateSprite(new Point(e.getX(), e.getY()));
        levelThreeButton.updateSprite(new Point(e.getX(), e.getY()));
        backButton.updateSprite(new Point(e.getX(), e.getY()));
    }

}
