import java.awt.geom.Rectangle2D;

public class CollisionChecker {
    private Tile[][] tilemap;
    private Entity entity;

    public CollisionChecker(Entity entity, Level level){
        this.tilemap = level.getTilemap();
        this.entity = entity;
    }

    public boolean isColliding(double x, double y, double width, double height){
        double leftX = x;
        double rightX=  x + width;
        double bottomY =  (y + height);
        double topY = y;

        int leftCol   = (int) (leftX / GameCanvas.TILE_SIZE);
        int rightCol  = (int) (rightX / GameCanvas.TILE_SIZE);
        int topRow    = (int) (topY / GameCanvas.TILE_SIZE);
        int bottomRow = (int) (bottomY / GameCanvas.TILE_SIZE);


        if(rightCol <= 0 || leftCol >= (tilemap.length - 1) || bottomRow <= 0 || topRow >= (tilemap[0].length -1)) // Colliding with edges of the level
            return true;

        Tile bottomLeftTile  = tilemap[leftCol][bottomRow];
        Tile bottomRightTile = tilemap[rightCol][bottomRow];
        Tile topLeftTile     = tilemap[leftCol][topRow];
        Tile topRightTile    = tilemap[rightCol][topRow];

        if(passable(topLeftTile))
            if(passable(bottomRightTile))
                if(passable(topRightTile))
                    if (passable(bottomLeftTile)){
                        return false;
                    }


        return true;
    }

    public boolean isBottomColliding(double x, double y, double width, double height){
        double leftX = x;
        double rightX=  x + width;
        double bottomY =  (y + height);

        int leftCol   = (int) (leftX / GameCanvas.TILE_SIZE);
        int rightCol  = (int) (rightX / GameCanvas.TILE_SIZE);
        int bottomRow = (int) (bottomY / GameCanvas.TILE_SIZE);

        Tile bottomLeftTile  = tilemap[leftCol][bottomRow];
        Tile bottomRightTile = tilemap[rightCol][bottomRow];

        if(passable(bottomRightTile))
            if(passable(bottomLeftTile))
                return false;

        return true;
    }

    public double getCollidingTileX(Rectangle2D.Double hitbox, double vx){//FIX ME TO ACCOMODATE ALL PLAYER SIZES
        double leftX = hitbox.x + vx;
        double rightX=  hitbox.x + hitbox.width + vx;
        double bottomY =  hitbox.y + hitbox.height;

        int leftCol   = (int) (leftX / GameCanvas.TILE_SIZE);
        int rightCol  = (int) (rightX / GameCanvas.TILE_SIZE);
        int bottomRow = (int) (bottomY / GameCanvas.TILE_SIZE);

        if(vx != 0){
            return 0;
        }
        return vx;
    }

    public double getCollidingTileY(Rectangle2D.Double hitbox, double vy){
        double leftX = hitbox.x;
        double bottomY =  hitbox.y + hitbox.height + vy;
        double topY = hitbox.y + vy;

        int leftCol   = (int) (leftX / GameCanvas.TILE_SIZE);
        int topRow    = (int) (topY / GameCanvas.TILE_SIZE);
        int bottomRow = (int) (bottomY / GameCanvas.TILE_SIZE);

        if(vy >= 0){
            // moving in the downwards direction
            Tile tile = tilemap[leftCol][bottomRow];

            return tile.getY() - hitbox.height - 0.99;  // - 1 to ground entity exactly
        }
        else{

            // Moving in the upwards direction
            Tile tile  = tilemap[leftCol][topRow];

            return tile.getY() + tile.getHeight();
        }

    }

    private boolean passable(Tile tile){
        return tile.getType() == Tile.Type.PASSABLE;
    }

}
