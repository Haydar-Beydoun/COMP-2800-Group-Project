package Game.Level;

import java.awt.*;


/**
 *
 * Class to initialize tiles.
 */
public class Tile {
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle hitBox;
    private Type type;

    public enum Type{
        WALL,
        PLATFORM,
        ROOF,
        PASSABLE;

        public static Type getType(String input){
            return switch (input) {
                case "wall" -> Type.WALL;
                case "platform" -> Type.WALL;
                case "roof" -> Type.WALL;
                default -> Type.PASSABLE;
            };
        }

    }


    /**
     * Constructor of the Tile class.
     * @param x X position on the canvas.
     * @param y Y position on the canvas.
     * @param width width of the tile.
     * @param height height of the tile.
     * @param type The type of the tile.
     */
    public Tile(int x, int y, int width, int height, Type type){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        //this.sprite = sprite;
        hitBox = new Rectangle(x,y,width,height);
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.fillRect((int) x,(int) y,width,height);

        //Drawing hitbox
        g2d.drawRect((int) x,(int) y, width, height);
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Type getType() {
        return type;
    }
}
