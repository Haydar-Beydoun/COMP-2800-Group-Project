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


    public Level(Tile[][] tilemap, BufferedImage levelImage, ArrayList<Enemy> enemies, ArrayList<Collectable> collectables){
        this.tilemap = tilemap;
        this.levelImage = levelImage;
        this.enemies = enemies;
        this.collectables = collectables;
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


}
