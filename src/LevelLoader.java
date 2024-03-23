import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * Level loader class to render levels from save.
 * @author Rayyan
 * @author Haydar
 */
public class LevelLoader {
    Tile[][] tileMap;
    private BufferedImage pixelMap, background;
    private int tileHeight, tileWidth;
    private int numTiles;
    private String backgroundPath, pixelMapPath;
    private HashMap<Integer, Image> tileImages = new HashMap<>();       // <hex value pertaining to colour on pixel map, corresponding image>
    private HashMap<Integer, Tile.Type> tileTypes = new HashMap<>();    // <hex value pertaining to colour on pixel map, corresponding tile type>




    public LevelLoader(String filePath){
        loadConfig(filePath);
        fillLevel();
    }



    /**
     * <p><b>Config File Format (to be written in lowercase)</b>
     * <br>            -Tile Width
     * <br>            -Tile Height
     * <br>            -background path
     * <br>            -pixel map path
     * <br>            -Num tiles
     * <br>            -Colour hex value
     * <br>            -corresponding tile
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
            -Tile Width
            -Tile Height
            -background path
            -pixel map path
            -Num tiles
            -Colour hex value
            -corresponding tile path
            -tile type(wall,platform, etc.)
            -...
        */

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            tileWidth  = Integer.parseInt(br.readLine());
            tileHeight = Integer.parseInt(br.readLine());
            background = ImageLoader.loadImage(br.readLine());
            pixelMap = ImageLoader.loadImage(br.readLine());
            numTiles = Integer.parseInt(br.readLine());

            for(int i = 0; i < numTiles; i++){
                int colour =  Integer.parseInt(br.readLine(), 16);
                tileImages.put(colour, ImageLoader.loadImage(br.readLine()));
                tileTypes.put(colour, Tile.Type.getType(br.readLine()));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * <p><b>Fill Level</b>
     * <br>Draws the file image
     * </p>
     *
     */
    private void fillLevel(){
        Graphics buffG = background.getGraphics();

        int width  = pixelMap.getWidth();
        int height = pixelMap.getHeight();

        tileMap = new Tile[width][height];

        //Traversing pixel map
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                int colour = pixelMap.getRGB(x,y);
                colour = colour & 0xffffff; // Removing alpha portion of colour

                if(tileImages.containsKey(colour)){
                    Image tile = tileImages.get(colour);
                    int offset = tileHeight - tile.getHeight(null);
                    buffG.drawImage(tile, x * tileWidth, y * tileHeight + offset,null);

                    tileMap[x][y] = new Tile(x * tileWidth, y * tileHeight, tileWidth, tileHeight, tileTypes.get(colour));
                }
                else{
                    tileMap[x][y] = new Tile(x * tileWidth, y * tileHeight, tileWidth, tileHeight, Tile.Type.PASSABLE);
                }
            }
        }
    }

    public BufferedImage getBackground(){
        return background;
    }

    public Level getLevel(){
        return new Level(tileMap);
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }
}
