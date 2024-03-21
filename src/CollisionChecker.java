import java.awt.*;
import java.awt.geom.Point2D;

public class CollisionChecker {
    Tile[][] tilemap;
    Entity[] enitities;
    Player player;
    public boolean bottom = false, top = false, right = false, left = false;



    public CollisionChecker(Level level, Player player, Entity[] entity){
        this.tilemap = level.getTilemap();
        this.player = player;
    }

    public boolean checkCollision(){
        for(int i=0; i < tilemap.length;i++){
            for(int j=0;j < tilemap[i].length;j++){
                Tile tile = tilemap[i][j];
                bottom = false;
                top = false;
                right = false;
                left = false;

                if(!passable(tile) && player.getHitBox().intersects(tile.getHitBox())) {
                    //System.out.println("General Collision");
                    bottom = checkBottomCollision(tilemap[i][j]);
//                    top    = checkTopCollision(tilemap[i][j]);
//                    left   = checkLeftCollision(tilemap[i][j]);
//                    right  = checkRightCollision(tilemap[i][j]);
                    //System.out.println(bottom);
                    //System.out.println(player.getX()+","+player.getY()+" == "+tilemap[i][j].getX()+","+tilemap[i][j].getX());

                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkBottomCollision(Tile tile){
        double leftX = player.getX();
        double  rightX=  player.getX() + player.getWidth();
        double bottomY =  (player.getY() + player.getHeight());
        double topY = player.getY();
        Point2D.Double bottomLeft = new Point2D.Double(leftX, bottomY);
        Point2D.Double rightLeft = new Point2D.Double(rightX, bottomY);

        if(tile.getHitBox().contains(bottomLeft) || tile.getHitBox().contains(rightLeft)) {
            System.out.println("Bottom Collision");
            return true;
        }
        return false;
    }

    public boolean checkTopCollision(Tile tile){
        if(!passable(tile) && (player.getY() + player.getHeight()) >= tile.getY()) {
            System.out.println("Top Collision");
            return true;
        }
        return false;
    }

    public boolean checkLeftCollision(Tile tile) {
        if(!passable(tile) && (player.getX() + player.getWidth() >= (tile.getX()))) {
            System.out.println("Left Collision");
            //player.setX(tile.getX());
            return true;
        }
        return false;
    }

    public boolean checkRightCollision(Tile tile){
        if(!passable(tile) && (player.getX() + player.getWidth()) < (tile.getX() + tile.getWidth())) {
            System.out.println("Right Collision");
            //player.setX(tile.getX() + tile.getWidth());
            return true;
        }
        return false;
    }

    private boolean passable(Tile tile){
        return tile.getType() == Tile.Type.PASSABLE;
    }

}
