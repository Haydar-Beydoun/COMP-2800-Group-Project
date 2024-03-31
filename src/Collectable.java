import java.awt.*;

public class Collectable extends GameObject{
    Rectangle hitBox;

    public Collectable(double worldX, double worldY, int width, int height) {
        super(worldX, worldY, width, height);
    }



    public Rectangle getHitBox() {
        return hitBox;
    }

}
