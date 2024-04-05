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
    private Sound levelLockedSound = new Sound("src/resources/sound_effects/levelLocked.wav");

    public LevelSelectMenu(GameCanvas canvas){
        background = ImageLoader.loadImage("src/resources/UI/levelSelectMenuBackground.png");
        this.canvas = canvas;

        initButtons();
    }

    private void initButtons(){
        levelOneButton = new Button((GameCanvas.WIDTH - 248)/2, GameCanvas.HEIGHT / 2 - 150, 248, 120
                , ImageLoader.loadImage("src/resources/UI/level1.png"), ImageLoader.loadImage("src/resources/UI/level1Hovered.png"));

        levelTwoButton = new Button((GameCanvas.WIDTH - 248)/2, GameCanvas.HEIGHT / 2, 248, 120
                , ImageLoader.loadImage("src/resources/UI/level2.png"), ImageLoader.loadImage("src/resources/UI/level2Hovered.png"));

        levelThreeButton = new Button((GameCanvas.WIDTH - 248)/2, GameCanvas.HEIGHT / 2 + 150, 248, 120
                , ImageLoader.loadImage("src/resources/UI/level3.png"), ImageLoader.loadImage("src/resources/UI/level3Hovered.png"));

        backButton = new Button(20, GameCanvas.HEIGHT - 80, 124, 60
                                , ImageLoader.loadImage("src/resources/UI/backButton.png"), ImageLoader.loadImage("src/resources/UI/backButtonHovered.png"));

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
            levelOneButton.setButton(ImageLoader.loadImage("src/resources/UI/level1.png"));
            levelOneButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level1Hovered.png"));

            // Level 2 locked
            levelTwoButton.setButton(ImageLoader.loadImage("src/resources/UI/level2locked.png"));
            levelTwoButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level2lockedHovered.png"));

            // Level 3 locked
            levelThreeButton.setButton(ImageLoader.loadImage("src/resources/UI/level3locked.png"));
            levelThreeButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level3lockedHovered.png"));
        }
        else if(GameCanvas.currentLevelProgress == 2){
            // Level 1 complete
            levelOneButton.setButton(ImageLoader.loadImage("src/resources/UI/level1complete.png"));
            levelOneButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level1completeHovered.png"));

            // Level 2 incomplete
            levelTwoButton.setButton(ImageLoader.loadImage("src/resources/UI/level2.png"));
            levelTwoButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level2Hovered.png"));

            // Level 3 locked
            levelThreeButton.setButton(ImageLoader.loadImage("src/resources/UI/level3locked.png"));
            levelThreeButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level3lockedHovered.png"));
        }
        else if(GameCanvas.currentLevelProgress == 3){
            // Level 1 complete
            levelOneButton.setButton(ImageLoader.loadImage("src/resources/UI/level1complete.png"));
            levelOneButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level1completeHovered.png"));

            // Level 2 complete
            levelTwoButton.setButton(ImageLoader.loadImage("src/resources/UI/level2complete.png"));
            levelTwoButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level2completeHovered.png"));

            // Level 3 incomplete
            levelThreeButton.setButton(ImageLoader.loadImage("src/resources/UI/level3.png"));
            levelThreeButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level3Hovered.png"));
        }
        else{
            // All levels completed
            levelOneButton.setButton(ImageLoader.loadImage("src/resources/UI/level1complete.png"));
            levelOneButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level1completeHovered.png"));

            levelTwoButton.setButton(ImageLoader.loadImage("src/resources/UI/level2complete.png"));
            levelTwoButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level2completeHovered.png"));

            levelThreeButton.setButton(ImageLoader.loadImage("src/resources/UI/level3complete.png"));
            levelThreeButton.setButtonHovered(ImageLoader.loadImage("src/resources/UI/level3completeHovered.png"));
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

            canvas.loadLevel("src/resources/maps/level1/level1.txt");
            GameCanvas.currentLevel = 1;
            GameCanvas.gameState = GameCanvas.GameState.GAME;
        }
        else if(levelTwoButton.isOnButton(point)){
            if(GameCanvas.currentLevelProgress >= 2){
                levelTwoButton.playClick();
                canvas.stopHomeMusic();

                canvas.loadLevel("src/resources/maps/level2/level2.txt");
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

                canvas.loadLevel("src/resources/maps/level1/level1.txt");
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
