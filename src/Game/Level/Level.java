package Game.Level;

import Abstracts.Collectable;
import Abstracts.Enemy;
import Utils.Sound;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Level class to render level.
 * <br>
 * Retrieve data from level.
 */
public class Level {
    private int timeLimit;
    private Tile[][] tilemap;
    private ArrayList<Enemy> enemies;
    private ArrayList<Collectable> collectables;
    private BufferedImage levelImage;
    private int playerStartingX;
    private int playerStartingY;
    private Sound music;


    /**
     * Constructor of the Level class.
     * @param tilemap The tiles of the level.
     * @param levelImage The background image of the level.
     * @param enemies The enemies in the level.
     * @param collectables The collectables in the level.
     * @param playerStartingX The player starting x position.
     * @param playerStartingY The player starting y position.
     * @param musicPath The path to the music file.
     */
    public Level(Tile[][] tilemap, BufferedImage levelImage, ArrayList<Enemy> enemies, ArrayList<Collectable> collectables, int playerStartingX, int playerStartingY, String musicPath){
        this.tilemap = tilemap;
        this.levelImage = levelImage;
        this.enemies = enemies;
        this.collectables = collectables;
        this.playerStartingX = playerStartingX;
        this.playerStartingY = playerStartingY;
        music = new Sound(musicPath);
    }

    /**
     * Method to play music.
     */
    public void playMusic(){
        music.play();
        music.loop();
    }

    /**
     * Method to stop music.
     */
    public void stopMusic(){
        music.stop();
    }
    /**
     * Method to get the time limit.
     * @return timeLimit
     */
    public Tile[][] getTilemap() {
        return tilemap;
    }
    /**
     * Method to get the background for each level.
     * @return levelImage
     */
    public BufferedImage getLevelImage(){
        return levelImage;
    }

    /**
     * Method to get the enemies for each level.
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Method to get the collectables for each level.
     * @return collectables
     */
    public ArrayList<Collectable> getCollectables() {
        return collectables;
    }

    /**
     * Method to get the player starting x position.
     * @return playerStartingX
     */
    public int getPlayerStartingX() {
        return playerStartingX;
    }

    /**
     * Method to get the player starting y position.
     * @return playerStartingY
     */
    public int getPlayerStartingY() {
        return playerStartingY;
    }
}
