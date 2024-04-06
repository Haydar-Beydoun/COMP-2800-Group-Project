package Game;

import Game.UI.PauseMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow {
    public static JFrame gameWindow = new JFrame("FLASH FOX");
    private GameCanvas canvas = new GameCanvas();

    public GameWindow(){
        gameWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                canvas.save();
            }
        });

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameWindow.setResizable(false);

        addElements();

        gameWindow.pack();

        gameWindow.setLocationRelativeTo(null);

       if(GameCanvas.isFullScreen){
           canvas.setFullScreen();
       }

        gameWindow.setVisible(true);

        canvas.start();

    }

    private void addElements(){
        gameWindow.add(canvas);
    }
}
