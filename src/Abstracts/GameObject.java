package Abstracts;

import java.awt.*;


/**
 * GameObject class is the base class for all objects in the game.
 * <br>
 * It contains the basic properties of an object in the game.
 */
public abstract class GameObject {
    public double worldX;
    public double worldY;
    public int screenX;
    public int screenY;
    public int width;
    public int height;

    /**
     * Constructor of the GameObject
     * @param worldX X position on the canvas.
     * @param worldY Y position on the canvas.
     * @param width width of the hitBox.
     * @param height height of the hitBox.
     */
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
