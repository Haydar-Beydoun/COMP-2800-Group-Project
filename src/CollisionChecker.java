import java.awt.geom.Rectangle2D;

public class CollisionChecker {
    private Tile[][] tilemap;
    private Entity entity;

    public CollisionChecker(Entity entity, Level level){
        this.tilemap = level.getTilemap();
        this.entity = entity;
    }

    //FIX ME: CHECK FOR COLLISION WITH EDGE OF THE MAP TO PREVENT OUT OF BOUNDS EXCEPTIONS
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
                    if (passable(bottomLeftTile))
                        return false;

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
        if(vx > 0){ // moving in the right direction
            int rightCol  = (int) ((hitbox.x + hitbox.width) / GameCanvas.TILE_SIZE);
            int topRow    = (int) (hitbox.y / GameCanvas.TILE_SIZE);
            Tile topRightTile    = tilemap[rightCol][topRow];

            return topRightTile.getX() - hitbox.width  + GameCanvas.TILE_SIZE - 1;  // - 1 to prevent from being moved directly into tile
        }
        else{   // Moving in the left direction
            int leftCol   = (int) (hitbox.x / GameCanvas.TILE_SIZE);
            int topRow    = (int) (hitbox.y / GameCanvas.TILE_SIZE);
            Tile topLeftTile  = tilemap[leftCol][topRow];

            return topLeftTile.getX();
        }
    }

    public double getCollidingTileY(Rectangle2D.Double hitbox, double vy){//FIX ME TO ACCOMODATE ALL PLAYER SIZES
        if(vy > 0){ // moving in the downwards direction
            int rightCol  = (int) ((hitbox.x + hitbox.width) / GameCanvas.TILE_SIZE);
            int topRow    = (int) (hitbox.y / GameCanvas.TILE_SIZE);
            Tile topRightTile    = tilemap[rightCol][topRow];

            return topRightTile.getY() + hitbox.height - 1;  // - 1 to prevent from being moved directly into tile
        }
        else{   // Moving in the upwards direction
            int leftCol   = (int) (hitbox.x / GameCanvas.TILE_SIZE);
            int topRow    = (int) (hitbox.y / GameCanvas.TILE_SIZE);
            Tile topLeftTile  = tilemap[leftCol][topRow];

            return topLeftTile.getY();
        }
    }

    private boolean passable(Tile tile){
        return tile.getType() == Tile.Type.PASSABLE;
    }

}
