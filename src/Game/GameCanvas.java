package Game;

import Abstracts.Collectable;
import Abstracts.Enemy;
import Entities.Collectables.Cherry;
import Entities.Collectables.Gem;
import Entities.Collectables.GoldenGem;
import Entities.Player;
import Game.Level.Level;
import Game.UI.*;
import Utils.Keyboard;
import Game.Level.LevelLoader;
import Utils.SaveFileManager;
import Utils.Sound;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameCanvas extends Canvas implements Runnable, MouseListener, MouseMotionListener {
    // Level and loader
    private static LevelLoader loader;
    private Level level;
    private String currentLevelPath;
    public static int currentLevelProgress;
    public static int currentLevel;
    public static boolean isFullScreen;
    static{
        if(!SaveFileManager.loadFile()){ // No save file
            currentLevelProgress = 1;
            isFullScreen = true;
        }
    }

    // Game States
    public enum GameState {
        PAUSE_MENU,
        START_MENU,
        LEVEL_SELECT_MENU,
        GAME,
        SETTINGS_MENU,
        CONTROLS_MENU,
        GAME_OVER,
        WIN
    }

    // Helper values and more
    public static GameState gameState = GameState.START_MENU;
    private Thread thread;
    private final int UPS = 60;
    private BufferStrategy bufferStrategy;
    private static final int NUM_COLS = 20;
    private static final int NUM_ROWS = 12;
    public static final int TILE_SIZE = 64;
    public static  int WIDTH = NUM_COLS * TILE_SIZE; // 1280px
    public static  int HEIGHT = NUM_ROWS * TILE_SIZE; // 768px

    // Screen Variables
    private int WIDTH2 = WIDTH;
    private int HEIGHT2 = HEIGHT;
    public static double gameScaleWidth = 1;
    public static double gameScaleHeight = 1;
    private BufferedImage tempImage;
    private Graphics2D g2d;

    //Entities
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Collectable> collectables;

    // Utilities
    public static Keyboard keyboard = new Keyboard();
    private Camera camera;
    private final PauseMenu pauseMenu = new PauseMenu(this);
    private final StartMenu startMenu = new StartMenu();
    private final LevelSelectMenu levelSelectMenu = new LevelSelectMenu(this);
    private final SettingsMenu settingsMenu = new SettingsMenu(this);
    private final ControlsMenu controlsMenu = new ControlsMenu();
    private HUD hud;

    //Sounds
    Sound homeMusic = new Sound("src/resources/music/summer nights.wav");


    public GameCanvas(){

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(keyboard);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setFocusable(true);

        initGame();
        playHomeMusic();
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
        long lastTime = System.nanoTime();
        double delta = 0;

        int updates = 0;
        int frames = 0;
        long timerForTracking = System.currentTimeMillis();

        while (thread != null) {
            long now = System.nanoTime();
            delta += (now - lastTime) / (double) (1000000000 / UPS);
            lastTime = now;

            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }

            renderToImage();
            renderToScreen();
            frames++;


            // Print UPS and FPS every second
            if (System.currentTimeMillis() - timerForTracking > 1000) {
//                System.out.print("Updates per second (UPS): " + updates);
//                System.out.println("   Frames per second (FPS): " + frames);
                updates = 0; // Reset the counters
                frames = 0;
                timerForTracking += 1000;
            }

        }

    }

    public void update(){
        updateState();

        switch(gameState){
            case GAME:
                player.update();
                updateCollectables();
                updateEnemies();
                if(player.health <= 0){
                    level.stopMusic();
                    player.death();
                    if(player.isOffScreen()){
                        loadLevel(currentLevelPath);
                    }
                }
                break;
            case PAUSE_MENU:
                pauseMenu.update();
                break;
            case START_MENU:
                startMenu.update();
                break;
            case LEVEL_SELECT_MENU:
                levelSelectMenu.update();
                break;
            case SETTINGS_MENU:
                settingsMenu.update();
                break;
            case CONTROLS_MENU:
                controlsMenu.update();
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
                    if(collectable instanceof Cherry) hud.increaseCherryCount();
                    if(collectable instanceof Gem) hud.increaseGemCount();
                    if(collectable instanceof GoldenGem){
                        level.stopMusic();
                        changeCurrentLevelProgress();
                        playHomeMusic();
                        gameState = GameState.LEVEL_SELECT_MENU;
                    }


                    collectable.collect();

                }
                if (collectable.isCollectComplete()) {


                    collectables.remove(collectable);
                }
            }

        }
    }

    public void updateState(){
        if(gameState == GameState.GAME &&  keyboard.isPressed(KeyEvent.VK_ESCAPE)){
            gameState = GameState.PAUSE_MENU;
        }
    }

    public void renderToImage(){
        switch(gameState){
            case GAME:
                camera.draw(g2d);
                hud.draw(g2d);
                break;
            case PAUSE_MENU:
                camera.draw(g2d);
                pauseMenu.draw(g2d);
                break;
            case START_MENU:
                startMenu.draw(g2d);
                break;
            case LEVEL_SELECT_MENU:
                levelSelectMenu.draw(g2d);
                break;
            case SETTINGS_MENU:
                settingsMenu.draw(g2d);
                break;
            case CONTROLS_MENU:
                controlsMenu.draw(g2d);
                break;
        }

    }

    public void renderToScreen(){
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

        g2d.drawImage(tempImage, 0, 0, WIDTH2, HEIGHT2, null);

        g2d.dispose();
        bufferStrategy.show();
    }

    public void setFullScreen(){
        // Reference for full screen functionality: https://www.youtube.com/watch?v=d5E_O2N73ZU
        GameWindow.gameWindow.dispose();

        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

        GameWindow.gameWindow.setUndecorated(true);
        gDev.setFullScreenWindow(GameWindow.gameWindow);

        WIDTH2 = GameWindow.gameWindow.getWidth();
        HEIGHT2 = GameWindow.gameWindow.getHeight();

        gameScaleWidth = (double) WIDTH2 / WIDTH;
        gameScaleHeight = (double) HEIGHT2 / HEIGHT;

    }

    public void setMinScreen(){
        GameWindow.gameWindow.dispose();

        WIDTH2 = WIDTH;
        HEIGHT2 = HEIGHT;

        GameWindow.gameWindow.setUndecorated(false);
        GameWindow.gameWindow.pack();

        GameWindow.gameWindow.setVisible(true);

        gameScaleWidth = 1;
        gameScaleHeight = 1;

    }

    public void loadLevel(String levelPath){
        if(level != null)
            level.stopMusic();

        loader = new LevelLoader(levelPath);
        level = loader.getLevel();
        enemies = level.getEnemies();
        collectables = level.getCollectables();
        player = new Player(level.getPlayerStartingX(), level.getPlayerStartingY(), 68, 87, 5, 5, level.getTilemap()); // FIX ME TO LATER PASS IN LEVEL COORD TO START AND HEALTH BACK WHEN CHANGING LEVELS
        camera = new Camera(level, player, enemies, collectables);
        currentLevelPath = levelPath;
        hud = new HUD(level,player,collectables);
        level.playMusic();
    }

    public void playHomeMusic(){
        homeMusic = new Sound("src/resources/music/summer nights.wav");
        homeMusic.play();
        homeMusic.loop();
    }

    public void stopHomeMusic(){
        homeMusic.stop();
    }

    private void changeCurrentLevelProgress(){
        if(currentLevelProgress == 1 && currentLevel == 1){
            currentLevelProgress = 2;
        }
        else if(currentLevelProgress == 2  && currentLevel == 2){
            currentLevelProgress = 3;
        }
        else if(currentLevelProgress == 3  && currentLevel == 3){
            currentLevelProgress = 4;
        }
    }

    public String getCurrentLevelPath() {
        return currentLevelPath;
    }

    public Level getLevel(){
        return level;
    }

    public static void save(){
        SaveFileManager.saveFile(isFullScreen, currentLevelProgress);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(gameState == GameState.PAUSE_MENU)
            pauseMenu.mousePressed(e);
        else if(gameState == GameState.START_MENU)
            startMenu.mousePressed(e);
        else if(gameState == GameState.LEVEL_SELECT_MENU)
            levelSelectMenu.mousePressed(e);
        else if(gameState == GameState.SETTINGS_MENU)
            settingsMenu.mousePressed(e);
        else if(gameState == GameState.CONTROLS_MENU)
            controlsMenu.mousePressed(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(gameState == GameState.PAUSE_MENU)
            pauseMenu.mouseMoved(e);
        else if(gameState == GameState.START_MENU)
            startMenu.mouseMoved(e);
        else if(gameState == GameState.LEVEL_SELECT_MENU)
            levelSelectMenu.mouseMoved(e);
        else if(gameState == GameState.SETTINGS_MENU)
            settingsMenu.mouseMoved(e);
        else if(gameState == GameState.CONTROLS_MENU)
            controlsMenu.mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

}
