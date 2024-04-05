package Game.UI;

import Game.GameCanvas;
import Utils.ImageLoader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class PauseMenu {
    private Button continueButton;
    private Button restartButton;
    private Button quitButton;
    private GameCanvas canvas;

    public PauseMenu(GameCanvas canvas){
        this.canvas = canvas;

        initButtons();
        addGUIElements();
    }

    private void initButtons(){
        continueButton = new Button((GameCanvas.WIDTH - 376)/2, GameCanvas.HEIGHT / 2 - 150, 376, 120
                                    , ImageLoader.loadImage("src/resources/UI/continueButton.png"), ImageLoader.loadImage("src/resources/UI/continueButtonHovered.png"));

        restartButton = new Button((GameCanvas.WIDTH - 312)/2, GameCanvas.HEIGHT / 2, 312, 120
                , ImageLoader.loadImage("src/resources/UI/restartButton.png"), ImageLoader.loadImage("src/resources/UI/restartButtonHovered.png"));

        quitButton = new Button((GameCanvas.WIDTH - 244)/2, GameCanvas.HEIGHT / 2 + 150, 244, 120
                , ImageLoader.loadImage("src/resources/UI/quitButton.png"), ImageLoader.loadImage("src/resources/UI/quitButtonHovered.png"));
    }

    private void addGUIElements(){

    }

    public void update(){

    }

    public void draw(Graphics2D g2d) {
        // Drawing translucent background
        Composite temp = g2d.getComposite();
        AlphaComposite alphaComposite;
        alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6F);
        g2d.setComposite(alphaComposite);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, GameCanvas.WIDTH, GameCanvas.HEIGHT);

        g2d.setComposite(temp);

        continueButton.draw(g2d);
        restartButton.draw(g2d);
        quitButton.draw(g2d);
    }

    public void mousePressed(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        if(continueButton.isOnButton(point)){
            continueButton.playClick();
            GameCanvas.gameState = GameCanvas.GameState.GAME;
        }
        else if(restartButton.isOnButton(point)){
            restartButton.playClick();
            canvas.loadLevel(canvas.getCurrentLevelPath());
            GameCanvas.gameState = GameCanvas.GameState.GAME;
        }
        else if(quitButton.isOnButton(point)){
            quitButton.playClick();
            canvas.getLevel().stopMusic();
            canvas.playHomeMusic();
            GameCanvas.gameState = GameCanvas.GameState.START_MENU;
        }
    }

    public void mouseMoved(MouseEvent e) {
        continueButton.updateSprite(new Point(e.getX(), e.getY()));
        restartButton.updateSprite(new Point(e.getX(), e.getY()));
        quitButton.updateSprite(new Point(e.getX(), e.getY()));
    }

}
