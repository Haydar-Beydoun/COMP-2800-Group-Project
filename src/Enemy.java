import java.util.Arrays;

public class Enemy extends Entity{
    Animator deathAnimator;
    private static int deathCloudWidth = 250;

    private static int deathCloudHeight = 250;



    public Enemy(double worldX, double worldY, int width, int height, int health, int speed){
        super(worldX, worldY,width, height, health, speed);
    }


    public void death(){
        spriteSheet = new SpriteSheet("src/resources/entities/spritesheets/enemy-deadth.png",1,4,40,41);
        deathAnimator = new Animator(Arrays.copyOfRange(spriteSheet.images ,0, 4), 0 , 8);
        currentAnimator = deathAnimator;

        worldX -= Math.abs((width - deathCloudWidth) / 2);
        worldY -= Math.abs((height - deathCloudHeight) / 2);

        width = deathCloudWidth;
        height = deathCloudHeight;

    }
    public boolean isDeathComplete(){
        return currentAnimator.isAnimationComplete() && currentAnimator.equals(deathAnimator);
    }
}
