package Game.UI;

import Game.GameCanvas;
import Utils.ImageLoader;

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

    }

    public void update(){

    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.drawImage(background, 0, 0, GameCanvas.WIDTH, GameCanvas.HEIGHT, null);

        levelOneButton.draw(g2d);
        levelTwoButton.draw(g2d);
        levelThreeButton.draw(g2d);
        backButton.draw(g2d);
    }

    public void mousePressed(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        if(levelOneButton.isOnButton(point)){
            System.out.println("clicked");
                canvas.loadLevel("src/resources/maps/level1.txt");
                GameCanvas.gameState = GameCanvas.GameState.GAME;
        }
        else if(levelTwoButton.isOnButton(point)){

        }
        else if(levelThreeButton.isOnButton(point)){

        }
        else if(backButton.isOnButton(point)){
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
