import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameCanvas extends Canvas implements Runnable {
    // Level and loader
    private static LevelLoader loader = new LevelLoader("src/resources/maps/level1.txt");
    private Level level = loader.getLevel();

    // Helper values and more
    private Thread thread;
    private final int UPS = 60;
    private BufferStrategy bufferStrategy;
    private static final int NUM_COLS = 20;
    private static final int NUM_ROWS = 12;
    public static final int TILE_SIZE = loader.getTileWidth();
    public static  int WIDTH = NUM_COLS * TILE_SIZE; // 1280px
    public static  int HEIGHT = NUM_ROWS * TILE_SIZE; // 768px

    // Full Screen variables
    // Reference for full screen functionality: https://www.youtube.com/watch?v=d5E_O2N73ZU
    private int WIDTH2 = WIDTH;
    private int HEIGHT2 = HEIGHT;
    private BufferedImage tempImage;
    private Graphics2D g2d;

    //Entities
    private Player player = new Player(WIDTH /2, HEIGHT /2, 68, 87, -1, 5, level.getTilemap());
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Collectable> collectables = new ArrayList<>();

    // Utilities
    public static Keyboard keyboard = new Keyboard();
    private Camera camera = new Camera(level, player, enemies, collectables);

    public GameCanvas(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(keyboard);
        this.setFocusable(true);

        enemies.add(new Eagle(150, 2000, 150, 2000, 104, 123, 1, 1, level.getTilemap()));
        enemies.add(new Eagle(300, 2000, 300, 2000, 104, 123, 1, 1, level.getTilemap()));
        enemies.add(new Eagle(500, 1800, 500, 1800, 104, 123, 1, 1, level.getTilemap()));
        enemies.add(new Opossum(1100, 1890, 36 * 3, 28 * 3, 1, 1, level.getTilemap()));

        collectables.add(new Gem(500, 2100));
        collectables.add(new Cherry(300, 2100));

        camera = new Camera(level, player, enemies, collectables);

        initGame();
    }

    private void initGame(){
        tempImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = (Graphics2D) tempImage.getGraphics();
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

            renderTempScreen();
            render();

        }
    }

    public void update(){
        player.update();

        // Updating entities
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            enemy.update();

            if(!enemy.isKilled() &&  player.isKillingEnemy(enemy.getHitBox())){
                enemy.death();
            }
            if(enemy.isDeathComplete()){
                enemies.remove(enemy);
            }

        }
        for(int i=0; i < collectables.size();i++){
            Collectable collectable = collectables.get(i);
            collectable.update();

            if(!collectable.isCollected() && player.isCollectingCollectable(collectable.getHitBox())){
                collectable.collect();
            }
            if(collectable.isCollectComplete()){
                collectables.remove(collectable);
            }

        }

    }

    public void renderTempScreen(){
        // Drawing plain white background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Drawing Level, entities, collectables, and player
        camera.draw(g2d);


    }

    public void render(){
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

        // Drawing temp Image (contains all components)
        g2d.drawImage(tempImage, 0, 0, WIDTH2, HEIGHT2, null);

        g2d.dispose();
        bufferStrategy.show();

    }

    public void setFullScreen(){
        GameWindow.gameWindow.dispose();

        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

        GameWindow.gameWindow.setUndecorated(true);
        gDev.setFullScreenWindow(GameWindow.gameWindow);

        WIDTH2 = GameWindow.gameWindow.getWidth();
        HEIGHT2 = GameWindow.gameWindow.getHeight();
    }

}
