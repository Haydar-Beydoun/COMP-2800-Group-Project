package Game.UI;

import Utils.Animator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu {
    JButton resumeButton;
    JButton restartButton;
    JButton exitButton;

    public Menu(){
        resumeButton = new JButton("Resume");
        restartButton = new JButton("Restart");
        exitButton = new JButton("Exit");


    }

    public void draw(Graphics2D g2d){
        g2d.fillRect(Canvas.WIDTH / 2 , Canvas.HEIGHT / 2, 300, 300);

    }

}
