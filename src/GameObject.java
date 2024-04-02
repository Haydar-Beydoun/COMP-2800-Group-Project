import java.awt.*;

public abstract class GameObject {
    double worldX;
    double worldY;
    int screenX;
    int screenY;
    int width;
    int height;

    public GameObject(double worldX, double worldY, int width, int height){
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = width;
        this.height = height;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWorldX(double worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(double worldY) {
        this.worldY = worldY;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getWorldX() {
        return worldX;
    }

    public double getWorldY() {
        return worldY;
    }

    public void draw(Graphics2D g2d){}

    public void draw(Graphics2D g2d, int offsetX, int offsetY){}

    public void update(){}

}
