import java.awt.*;
import java.util.Arrays;

public class Eagle extends Entity{
    int topY;
    int bottomY;
    Animator animator;

    public Eagle(int x,int y, int width,int height, int topY, int bottomY, int health, int speed, Tile[][] tileMap){
        super(x, y, width, height, health, speed);
        this.topY = topY;
        this.bottomY = bottomY;
        this.spriteSheet = new SpriteSheet("src/resources/entities/spritesheets/eagle-attack.png", 1,4,32);
        initEagle();
    }

    public void update(){
        move();
        animator.update();
    }

    public void initEagle(){
        animator = new Animator(Arrays.copyOfRange(spriteSheet.images ,0, 4), 0 , 7);
    }

    public void move(){
        if(worldY > bottomY || worldY < topY){
            speed *= -1;
        }

        vy = speed;
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(animator.currentFrame, screenX, screenY, width, height, null);
    }




}
