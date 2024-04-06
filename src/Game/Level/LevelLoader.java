package Game.Level;

import Abstracts.Collectable;
import Entities.Collectables.Cherry;
import Entities.Collectables.Gem;
import Entities.Collectables.GoldenGem;
import Entities.Enemies.Eagle;
import Abstracts.Enemy;
import Entities.Enemies.Opossum;
import Entities.Player;
import Utils.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Level loader class to build levels from configuration file.
 */
public class LevelLoader {
    private int playerX;
    private int playerY;
    private Tile[][] tileMap;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Collectable> collectables = new ArrayList<>();
    private BufferedImage levelPixelMap, enemyPixelMap, collectablePixelMap, background, levelImage;
    private String eaglesFilePath;
    private int tileHeight, tileWidth;
    private int numTiles;
    private HashMap<Integer, Image> tileImages = new HashMap<>();       // <hex value pertaining to colour on pixel map, corresponding image>
    private HashMap<Integer, Tile.Type> tileTypes = new HashMap<>();    // <hex value pertaining to colour on pixel map, corresponding tile type>
    private String musicPath;
    private ImageLoader imageLoader = new ImageLoader();

    /**
     * Constructor of the LevelLoader class.
     *
     * @param filePath The path to the configuration file.
     */
    public LevelLoader(String filePath){

        loadConfig(filePath);
        fillLevel();
        fillCollectable();
        fillEnemy();
    }

    /**
     * <p><b>Load Config</b>
     * <br>Loads the configuration file
     * </p>
     *
     * @param filePath
     */
    private void loadConfig(String filePath){

        /*
            --------------------------------------------------
                            Config File Format
                     (To be formatted in all lowercase)
            --------------------------------------------------
            -Game.Level.Tile Width
            -Game.Level.Tile Height
            -Music path
            -background path
            -level pixel map path
            -collectable pixel map path
            -enemies pixel map path
            -eagles txt path
            -Num tiles
            -corresponding tile path
            -Colour hex value
            -tile type(wall,platform, etc.)
            -...
        */

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)));

            tileWidth  = Integer.parseInt(br.readLine());
            tileHeight = Integer.parseInt(br.readLine());
            musicPath = br.readLine();
            background = imageLoader.loadImage(br.readLine());
            levelPixelMap = imageLoader.loadImage(br.readLine());
            collectablePixelMap = imageLoader.loadImage(br.readLine());
            enemyPixelMap = imageLoader.loadImage(br.readLine());
            eaglesFilePath = br.readLine();
            numTiles = Integer.parseInt(br.readLine());

            for(int i = 0; i < numTiles; i++){
                String tilePath = br.readLine();
                int colour =  Integer.parseInt(br.readLine(), 16);
                String tileType = br.readLine();

                tileImages.put(colour, imageLoader.loadImage(tilePath));
                tileTypes.put(colour, Tile.Type.getType(tileType));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Fill Level
     * <br>
     * Fills the level with tiles
     * <br>
     * Uses colour hex decimals to determine tile type
     *
     */
    private void fillLevel(){
        int width  = levelPixelMap.getWidth();
        int height = levelPixelMap.getHeight();

        levelImage = new BufferedImage(width * tileWidth, height * tileHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics buffG = levelImage.getGraphics();

        buffG.fillRect(0, 0, width * tileWidth, height * tileHeight);

        buffG.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);

        tileMap = new Tile[width][height];

        //Traversing pixel map
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                int colour = levelPixelMap.getRGB(x,y);
                colour = colour & 0xffffff; // Removing alpha portion of colour

                // Checking if tile
                if(tileImages.containsKey(colour)){
                    Image tile = tileImages.get(colour);
                    buffG.drawImage(tile, x * tileWidth, y * tileHeight, tileWidth, tileHeight, null);

                    tileMap[x][y] = new Tile(x * tileWidth, y * tileHeight, tileWidth, tileHeight, tileTypes.get(colour));
                }
                else{
                    // Checking if player
                    if(colour == 0xb5422d){
                        playerX = x * tileWidth;
                        playerY = y * tileHeight;
                    }

                    // Blank tile
                    tileMap[x][y] = new Tile(x * tileWidth, y * tileHeight, tileWidth, tileHeight, Tile.Type.PASSABLE);
                }
            }
        }
    }

    /**
     * Fill Collectable
     * <br>
     * Fills the level with collectables
     * <br>
     * Uses colour hex decimals to determine collectable type
     */
    private void fillCollectable(){
        int width  = collectablePixelMap.getWidth();
        int height = collectablePixelMap.getHeight();

        //Traversing pixel map
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                int colour = collectablePixelMap.getRGB(x,y);
                colour = colour & 0xffffff; // Removing alpha portion of colour

                if(colour == 0xba3655){
                    collectables.add(new Cherry(x * tileWidth, y * tileHeight));
                }
                else if(colour == 0xbb58cc){
                    collectables.add(new Gem(x * tileWidth, y * tileHeight));
                }
                else if(colour == 0x913e0b){
                    collectables.add(new GoldenGem(x * tileWidth, y * tileHeight));
                }
            }
        }
    }

    /**
     * Fill Enemy
     * <br>
     * Fills the level with enemies
     * <br>
     * Uses colour hex decimals to determine enemy type
     */
    private void fillEnemy(){
        try {
            int width  = enemyPixelMap.getWidth();
            int height = enemyPixelMap.getHeight();
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(eaglesFilePath)));

            //Traversing pixel map
            for(int x=0; x<width; x++){
                for(int y=0; y<height; y++){
                    int colour = enemyPixelMap.getRGB(x,y);
                    colour = colour & 0xffffff; // Removing alpha portion of colour

                    if(colour == 0x345487){
                        enemies.add(new Opossum(x * tileWidth, y * tileHeight, 36 * 3, 28 * 3, 1, 1, tileMap));
                    }
                    else if(colour == 0xedac51){
                        String[] endCoords = br.readLine().split(",");
                        int endX = Integer.parseInt(endCoords[0].trim());
                        int endY = Integer.parseInt(endCoords[1].trim());
                        enemies.add(new Eagle(x * tileWidth, y * tileHeight, endX, endY, 104, 123, 1, 1, tileMap));
                    }
                }
            }

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get Level
     * <br>
     * Returns the level
     * @return Level
     */
    public Level getLevel(){
        return new Level(tileMap, levelImage, enemies, collectables, playerX, playerY, musicPath);
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }
}
