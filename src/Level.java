import java.awt.image.BufferedImage;

public class Level {
    Stage currStage;
    int timeLimit;
    Tile[][] tilemap;
    BufferedImage levelImage;

    public enum Stage{
        Level1,
        Level2,
        Level3
    }

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
