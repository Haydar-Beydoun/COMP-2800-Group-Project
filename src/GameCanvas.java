import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameCanvas extends Canvas implements Runnable {
    private Thread thread;
    private final int UPS = 60;
    private BufferStrategy bufferStrategy;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static Keyboard keyboard = new Keyboard();
    private Player player = new Player(WIDTH /2, HEIGHT /2, 25, 25, -1, 3);
    public static final int TILE_SIZE = 32;        //FIX ME: FIND A PROPER PLACE FOR ME
    LevelLoader loader = new LevelLoader("src/resources/maps/level1.txt");
    Level level = loader.getLevel();


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
        double drawInterval = 1_000_000_000 / UPS;
        double delta = 0;
        long prevTime = System.nanoTime();
        long currentTime;

        while(thread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - prevTime) / drawInterval;
            prevTime = currentTime;

            if(delta >= 1){
                update();
                delta--;
            }

            render();

        }
    }

    public void update(){
        player.update();
    }

    public void render(){
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

        // Drawing plain white background behind
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Drawing Level --> FIX ME: move me into level class???
        BufferedImage image = loader.getLevelImage();
        g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);

        player.draw(g2d);

        g2d.dispose();
        bufferStrategy.show();

    }

}
