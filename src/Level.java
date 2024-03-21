

public class Level {
    Stage currStage;
    int timeLimit;
    Tile[][] tilemap;

    public enum Stage{
        Level1,
        Level2,
        Level3
    }

    public Level(Tile[][] tilemap){
        this.tilemap = tilemap;
    }

    public Tile[][] getTilemap() {
        return tilemap;
    }

    public void drawLevel(){

    }

}
