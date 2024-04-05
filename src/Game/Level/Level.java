package Game.Level;

import Abstracts.Collectable;
import Abstracts.Enemy;
import Utils.Sound;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level {
    private int timeLimit;
    private Tile[][] tilemap;
    private ArrayList<Enemy> enemies;
    private ArrayList<Collectable> collectables;
    private BufferedImage levelImage;
    private int playerStartingX;
    private int playerStartingY;
    private Sound music;


    public Level(Tile[][] tilemap, BufferedImage levelImage, ArrayList<Enemy> enemies, ArrayList<Collectable> collectables, int playerStartingX, int playerStartingY, String musicPath){
        this.tilemap = tilemap;
        this.levelImage = levelImage;
        this.enemies = enemies;
        this.collectables = collectables;
        this.playerStartingX = playerStartingX;
        this.playerStartingY = playerStartingY;
        music = new Sound(musicPath);
    }


    public void playMusic(){
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public Tile[][] getTilemap() {
        return tilemap;
    }

    public BufferedImage getLevelImage(){
        return levelImage;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Collectable> getCollectables() {
        return collectables;
    }

    public int getPlayerStartingX() {
        return playerStartingX;
    }

    public int getPlayerStartingY() {
        return playerStartingY;
    }
}
