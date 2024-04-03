import javax.swing.*;

public class GameWindow {
    public static JFrame gameWindow = new JFrame("INSERT GAME NAME HERE");        // FIX ME: CHANGE NAME
    private GameCanvas canvas = new GameCanvas();

    public GameWindow(){
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameWindow.setResizable(false);

        addElements();

        gameWindow.pack();

        gameWindow.setLocationRelativeTo(null);

        canvas.setFullScreen();
        gameWindow.setVisible(true);

        canvas.start();

    }

    private void addElements(){
        gameWindow.add(canvas);
    }
}
