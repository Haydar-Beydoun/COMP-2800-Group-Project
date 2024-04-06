package Game;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Game Window class to create game window.
 * <br>
 * Create game window with canvas.
 * <br>
 * Includes window listener to save the game when the window is closing.
 */
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
