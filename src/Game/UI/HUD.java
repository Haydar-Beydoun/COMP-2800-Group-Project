package Game.UI;

import Abstracts.Collectable;
import Entities.Collectables.Cherry;
import Entities.Collectables.Gem;
import Entities.Player;
import Game.GameCanvas;
import Game.Level.Level;
import Utils.ImageLoader;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.*;


public class HUD {
    private final Level level;
    private Player player;
    private int cherryCount = 0;
    private int gemCount = 0;
    private int cherryMax = 0;
    private int gemMax = 0;
    private int maxHealth;
    BufferedImage healthBar;
    BufferedImage cherry;
    BufferedImage gem;


    File fontFile;
    FileInputStream fontStream;
    Font customFont;
    GraphicsEnvironment ge;
    private int fontSize = 30;


    public HUD(Level level, Player player, ArrayList<Collectable> collectableArrayList){
        this.level = level;
        this.player = player;
        this.maxHealth = player.getHealth();

        //1445 x 490
        this.healthBar = ImageLoader.loadImage("src/resources/UI/healthbar.png");
        this.healthBar = ImageLoader.scaleImage(healthBar,1445/5,490/5);


        this.cherry = new SpriteSheet("src/resources/entities/spritesheets/cherry.png", 1, 8, 21, 21).images[0];
        this.cherry = ImageLoader.scaleImage(cherry,75,75);


        this.gem = new SpriteSheet("src/resources/entities/spritesheets/gem.png", 1, 5, 15, 13).images[0];
        this.gem = ImageLoader.scaleImage(gem, 60,60);


        for(Collectable collectable: collectableArrayList){
            if(collectable instanceof Cherry) cherryMax++;
            else if(collectable instanceof Gem) gemMax++;
        }

        initResources();
    }

    private void initResources(){
        try {
            fontFile = new File("src/resources/font/PixeloidSansBold-PKnYd.ttf");
            fontStream = new FileInputStream(fontFile);
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        }
        catch(Exception e){
            e.printStackTrace();
            customFont = new Font("Arial", Font.BOLD, fontSize);
        }


    }

    public void increaseCherryCount(){
        cherryCount++;
    }
    public void increaseGemCount(){
        gemCount++;
    }

    public void draw(Graphics2D g2d){
        // Drawing health bar
        Color colour;
        switch (player.health){
            case 5:
                colour = new Color(0, 255, 33);
                break;
            case 4:
                colour = new Color(191, 255, 0);
                break;
            case 3:
                colour = new Color(210, 171, 17);
                break;
            case 2:
                colour = new Color(255, 0, 0);
                break;
            case 1:
                colour = new Color(89, 12, 12);
                break;
            default:
                colour = new Color(0, 0, 0);
        }

        g2d.setColor(colour);
        g2d.fillRect(80,35,(int) (189 * ((double) player.getHealth() / maxHealth)),25);

        g2d.drawImage(healthBar, 0, 0, null);

        // Drawing gem count and Cheery Count


        try {
            g2d.setFont(customFont.deriveFont(24F));
        }
        catch(Exception e){
            e.printStackTrace();
            //g2d.setFont(new Font("Arial", Font.BOLD));

        }

        //g2d.setFont(new Font("Arial", Font.BOLD, customFont));

        g2d.setColor(new Color(0, 0, 0));
        g2d.drawString("X " + gemCount + "/" + gemMax,GameCanvas.WIDTH - 120,60);
        g2d.drawString("X "+cherryCount + "/" + cherryMax,GameCanvas.WIDTH - 320,60);
        g2d.drawImage(gem, GameCanvas.WIDTH - 200, 19, null);
        g2d.drawImage(cherry, GameCanvas.WIDTH - 400, 14, null);





    }

    @Override
    public String toString() {

        return "Gem Count: "+ gemCount + "\nCherryCount: " + cherryCount+ "\n";
    }
}
