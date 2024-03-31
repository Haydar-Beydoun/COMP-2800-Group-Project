import java.awt.image.BufferedImage;

public class Level {
    private int timeLimit;
    private Tile[][] tilemap;

    private BufferedImage levelImage;
    private int playerStartingX;
    private int playerStartingY;


    public Level(Tile[][] tilemap, BufferedImage levelImage){
        this.tilemap = tilemap;
        this.levelImage = levelImage;
    }

    public Tile[][] getTilemap() {
        return tilemap;
    }

    public BufferedImage getLevelImage(){
        return levelImage;
    }

}
