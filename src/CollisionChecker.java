import java.awt.geom.Rectangle2D;

public class CollisionChecker {
    private Tile[][] tilemap;

    public CollisionChecker(Tile[][] tilemap){
        this.tilemap = tilemap;
    }


    /**
     * Checks whether the tiles the player is colliding with is passable or not.
     *
     * @param hitbox
     * @param vx
     * @param vy
     * @return Boolean
     */
    public boolean isColliding(Rectangle2D.Double hitbox, double vx, double vy){
        double x = hitbox.x + vx;
        double y = hitbox.y +vy;
        double width = hitbox.width;
        double height = hitbox.height;

        double leftX = x;
        double middleX = x + (width / 2);
        double rightX=  x + width;
        double bottomY =  (y + height);
        double middleY = y + (height / 2);
        double topY = y;

        int leftCol   = (int) (leftX / GameCanvas.TILE_SIZE);
        int middleCol = (int) (middleX / GameCanvas.TILE_SIZE);
        int rightCol  = (int) (rightX / GameCanvas.TILE_SIZE);

        int topRow    = (int) (topY / GameCanvas.TILE_SIZE);
        int middleRow = (int) (middleY / GameCanvas.TILE_SIZE);
        int bottomRow = (int) (bottomY / GameCanvas.TILE_SIZE);

        if(rightCol <= 0 || leftCol >= (tilemap.length - 1) || topRow <= 0 || bottomRow >= (tilemap[0].length)) // Colliding with edges of the level
            return true;

        Tile bottomLeftTile   = tilemap[leftCol][bottomRow];
        Tile bottomMiddleTile = tilemap[middleCol][bottomRow];
        Tile bottomRightTile  = tilemap[rightCol][bottomRow];

        Tile middleLeftTile  = tilemap[leftCol][middleRow];
        Tile middleRightTile = tilemap[rightCol][middleRow];

        Tile topLeftTile     = tilemap[leftCol][topRow];
        Tile topMiddleTile   = tilemap[middleCol][topRow];
        Tile topRightTile    = tilemap[rightCol][topRow];

        //Checks if the tile the player is colliding
        if(passable(topLeftTile))
            if(passable(bottomRightTile))
                if(passable(topRightTile))
                    if (passable(bottomLeftTile))
                        if(passable(bottomMiddleTile))
                            if(passable(middleLeftTile))
                                if(passable(middleRightTile))
                                    if (passable(topMiddleTile))
                                        return false;

        return true;
    }

    public boolean isBottomColliding(Rectangle2D.Double hitbox , double vx, double vy){

        double x = hitbox.x + vx;
        double y = hitbox.y +vy;
        double width = hitbox.width;
        double height = hitbox.height;

        double leftX = x;
        double middleX = x + (width / 2);
        double rightX=  x + width;
        double bottomY =  (y + height);

        int leftCol   = (int) (leftX / GameCanvas.TILE_SIZE);
        int middleCol = (int) (middleX / GameCanvas.TILE_SIZE);
        int rightCol  = (int) (rightX / GameCanvas.TILE_SIZE);
        int bottomRow = (int) (bottomY / GameCanvas.TILE_SIZE);

        if(bottomRow >= tilemap[0].length) // Colliding with edges of the level
            return true;

        Tile bottomMiddleTile = tilemap[middleCol][bottomRow];
        Tile bottomLeftTile  = tilemap[leftCol][bottomRow];
        Tile bottomRightTile = tilemap[rightCol][bottomRow];

        if(passable(bottomRightTile))
            if(passable(bottomLeftTile))
                if(passable(bottomMiddleTile))
                    return false;

        return true;
    }

    public double getCollidingTileX(Rectangle2D.Double hitbox, double hitBoxLeftOffset, double vx){
        double leftX = hitbox.x + vx;
        double rightX=  hitbox.x + hitbox.width + vx;
        double bottomY =  hitbox.y + hitbox.height;

        int leftCol   = (int) (leftX / GameCanvas.TILE_SIZE);
        int rightCol  = (int) (rightX / GameCanvas.TILE_SIZE);
        int bottomRow = (int) (bottomY / GameCanvas.TILE_SIZE);

        if(vx > 0){
            // moving in the right direction
            Tile tile = tilemap[rightCol][bottomRow];

            return tile.getX() - hitbox.width - 1 - hitBoxLeftOffset;  // - 1 to ground entity exactly
        }
        else{
            // Moving in the left direction
            Tile tile  = tilemap[leftCol][bottomRow];

            return tile.getX() + tile.getWidth() - hitBoxLeftOffset;
        }
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

            return tile.getY() - hitbox.height - 1;  // - 1 to ground entity exactly
        }
        else{
            // Moving in the upwards direction
            Tile tile  = tilemap[leftCol][topRow];

            return tile.getY() + tile.getHeight();
        }

    }//FIX ME TO ACCOUNT FOR OUT OF BOUNDS

    private boolean passable(Tile tile){
        return tile.getType() == Tile.Type.PASSABLE;
    }

}