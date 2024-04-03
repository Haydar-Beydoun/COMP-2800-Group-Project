package Game;

import Abstracts.Collectable;
import Entities.Enemies.Eagle;
import Abstracts.Enemy;
import Entities.Player;
import Game.Level.Level;
import Game.UI.PauseMenu;
import Utils.Keyboard;
import Game.Level.LevelLoader;
import Utils.Mouse;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameCanvas extends Canvas implements Runnable {
    // Game.Game.Level.Level and loader
    private static LevelLoader loader = new LevelLoader("src/resources/maps/level1.txt");
    private Level level = loader.getLevel();


    // Game States
    public enum GameState {
        PAUSE_MENU,
        START_MENU,
        LEVEL_SELECT_MENU,
        GAME,
        GAME_OVER,
        WIN
    }

    // Helper values and more
    static GameState gameState = GameState.GAME;
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
    private Player player = new Player(WIDTH /2, HEIGHT /2, 68, 87, 10, 5, level.getTilemap());
    private ArrayList<Enemy> enemies = level.getEnemies();
    private ArrayList<Collectable> collectables = level.getCollectables();

    // Utilities
    public static Keyboard keyboard = new Keyboard();
    public static Mouse mouse = new Mouse();
    private Camera camera = new Camera(level, player, enemies, collectables);

    public GameCanvas(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(keyboard);
        this.addMouseListener(mouse);
        this.setFocusable(true);

        enemies.add(new Eagle(500, 1800, 500, 1900, 104, 123, 1, 1, level.getTilemap()));

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
        System.out.println(player.health);
        if(player.health <= 0){
            player.death();
        }
        updateState();

        switch(gameState){
            case GAME:
                player.update();
                updateCollectables();
                updateEnemies();
                break;
            case PAUSE_MENU:
                pauseMenu.update();
                break;

        }

    }

    private void updateEnemies(){
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            if(Math.abs(enemy.worldX - player.worldX) < WIDTH && Math.abs(enemy.worldY - player.worldY) < HEIGHT) {
                enemy.update();

                if (!enemy.isKilled() && player.isKillingEnemy(enemy.getHitBox())) {
                    enemy.death();
                }
                if (enemy.isDeathComplete()) {
                    enemies.remove(enemy);
                }
            }
        }
    }

    private void updateCollectables(){
        for(int i=0; i < collectables.size();i++){
            Collectable collectable = collectables.get(i);

            if(Math.abs(collectable.worldX - player.worldX) < WIDTH && Math.abs(collectable.worldY - player.worldY) < HEIGHT) {
                collectable.update();

                if (!collectable.isCollected() && player.isCollectingCollectable(collectable.getHitBox())) {
                    collectable.collect();
                }
                if (collectable.isCollectComplete()) {
                    collectables.remove(collectable);
                }
            }

        }
    }

    public void updateState(){
        if(keyboard.isPressed(KeyEvent.VK_1)){
            gameState = GameState.PAUSE_MENU;
        }
        if(keyboard.isPressed(KeyEvent.VK_2)){
            gameState = GameState.GAME;
        }
    }

    public void renderTempScreen(){
        switch(gameState){
            case GAME:
                camera.draw(g2d);
                break;
            case PAUSE_MENU:
                camera.draw(g2d);
                pauseMenu.draw(g2d);
                break;
        }

    }

    public void render(){
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

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
