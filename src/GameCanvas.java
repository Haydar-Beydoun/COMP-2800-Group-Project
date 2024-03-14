import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameCanvas extends Canvas implements Runnable {
    private Thread thread;
    private final int UPS = 60;
    private BufferStrategy bufferStrategy;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static Keyboard keyboard = new Keyboard();
    private Player player = new Player(WIDTH /2, HEIGHT /2, 20, 20,-1, 5);


    public GameCanvas(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(keyboard);
    }

    public void start(){
        if(thread == null){
            this.requestFocus();
            this.createBufferStrategy(3);
            bufferStrategy = this.getBufferStrategy();

            thread = new Thread(this, "canvas thread");
            thread.start();
        }
    }

    @Override
    public void run() {

    }

    public void update(){
        player.update();
    }

    LevelLoader loader = new LevelLoader("src/resources/maps/level1.txt");
    public void render(){
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        BufferedImage image = loader.getBackground();
        g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);

        player.draw(g2d);

        g2d.dispose();
        bufferStrategy.show();

    }

}
