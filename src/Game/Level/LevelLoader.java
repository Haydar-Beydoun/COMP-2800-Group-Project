package Game.Level;

import Abstracts.Collectable;
import Entities.Collectables.Cherry;
import Entities.Collectables.Gem;
import Entities.Enemies.Eagle;
import Abstracts.Enemy;
import Entities.Enemies.Opossum;
import Entities.Player;
import Utils.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Game.Game.Level.Level loader class to render levels from save.
 * @author Rayyan
 * @author Haydar
 */
public class LevelLoader {

    private Tile[][] tileMap;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Collectable> collectables = new ArrayList<>();
    private BufferedImage levelPixelMap, enemyPixelMap, collectablePixelMap, background, levelImage;
    private String eaglesFilePath;
    private int tileHeight, tileWidth;
    private int numTiles;
    private HashMap<Integer, Image> tileImages = new HashMap<>();       // <hex value pertaining to colour on pixel map, corresponding image>
    private HashMap<Integer, Tile.Type> tileTypes = new HashMap<>();    // <hex value pertaining to colour on pixel map, corresponding tile type>

    public LevelLoader(String filePath){

        loadConfig(filePath);
        fillLevel();
        fillCollectable();
        fillEnemy();
    }

    /**
     * <p><b>Config File Format (to be written in lowercase)</b>
     * <br>            -Game.Level.Tile Width
     * <br>            -Game.Level.Tile Height
     * <br>            -background path
     * <br>            -level pixel map path
     * <br>            -collectable pixel map path
     * <br>            -enemy pixel map path
     * <br>            -eagles txt path
     * <br>            -Num tiles
     * <br>            -corresponding tile
     * <br>            -Colour hex value
     * <br>            -tile type(wall,platform, etc.)
     * <br>            -...
     * </p>
     * @param filePath String of the file path.
     */
    private void loadConfig(String filePath){

        /*
            --------------------------------------------------
                            Config File Format
                     (To be formatted in all lowercase)
            --------------------------------------------------
            -Game.Level.Tile Width
            -Game.Level.Tile Height
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
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            tileWidth  = Integer.parseInt(br.readLine());
            tileHeight = Integer.parseInt(br.readLine());
            background = ImageLoader.loadImage(br.readLine());
            levelPixelMap = ImageLoader.loadImage(br.readLine());
            collectablePixelMap = ImageLoader.loadImage(br.readLine());
            enemyPixelMap = ImageLoader.loadImage(br.readLine());
            eaglesFilePath = br.readLine();
            numTiles = Integer.parseInt(br.readLine());

            for(int i = 0; i < numTiles; i++){
                String tilePath = br.readLine();
                int colour =  Integer.parseInt(br.readLine(), 16);
                String tileType = br.readLine();

                tileImages.put(colour, ImageLoader.loadImage(tilePath));
                tileTypes.put(colour, Tile.Type.getType(tileType));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * <p><b>Fill Game.Game.Level.Level</b>
     * <br>Draws the file image
     * </p>
     *
     */
    private void fillLevel(){
        int width  = levelPixelMap.getWidth();
        int height = levelPixelMap.getHeight();

        levelImage = new BufferedImage(width * tileWidth, height * tileHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics buffG = levelImage.getGraphics();

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
                    tileMap[x][y] = new Tile(x * tileWidth, y * tileHeight, tileWidth, tileHeight, Tile.Type.PASSABLE);
                }
            }
        }
    }

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
            }
        }
    }

    private void fillEnemy(){
        try {
            int width  = enemyPixelMap.getWidth();
            int height = enemyPixelMap.getHeight();
            BufferedReader br = new BufferedReader(new FileReader(eaglesFilePath));

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

    public Level getLevel(){
        return new Level(tileMap, levelImage, enemies, collectables);
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }
}
