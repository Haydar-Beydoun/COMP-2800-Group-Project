import java.awt.*;

public class Cherry extends Collectable{

    public Cherry(double worldX, double worldY) {
        super(worldX, worldY, 70, 70);
        initCollectable();
    }

    @Override
    public void initCollectable(){
        spriteSheet = new SpriteSheet("src/resources/entities/spritesheets/cherry.png", 1, 8, 21, 21);
        currentAnimator = new Animator(spriteSheet.images, 0, 9);
    }

    @Override
    public void update(){
        currentAnimator.update();

    }

    public void draw(Graphics2D g2d, int offsetX, int offsetY){
        g2d.drawImage(currentAnimator.currentFrame, (int) worldX + offsetX, (int) worldY + offsetY, width, height, null);

    }
}
