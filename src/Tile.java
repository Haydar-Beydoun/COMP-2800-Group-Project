import java.awt.*;


/**
 *
 * Class to initialize tiles.
 * @author Rayyan
 * @author Haydar
 */
public class Tile {
    int x;
    int y;
    int width;
    int height;
    Rectangle hitBox;
    Type type;

    public enum Type{
        WALL,
        PLATFORM,
        ROOF,
        PASSABLE;

        public static Type getType(String input){
            switch(input){
                case "wall":
                    return Type.WALL;
                case "platform":
                    return Type.WALL;
                case "roof":
                    return Type.WALL;
                default:
                    return Type.PASSABLE;
            }
        }

    }


    public Tile(int x, int y, int width, int height, Type type){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        //this.sprite = sprite;
        hitBox = new Rectangle(x,y,width,height);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
