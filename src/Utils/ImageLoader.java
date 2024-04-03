package Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String filePath){
        BufferedImage image = null;

        try{
            image = ImageIO.read(new File(filePath));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return image;
    }

    public static BufferedImage loadSubImage(String filePath, int tileSize, int row, int column){
        BufferedImage image = loadImage(filePath);
        image = image.getSubimage(column * tileSize, row * tileSize, tileSize, tileSize);

        return image;
    }

    public static BufferedImage scaleImage(BufferedImage imageInput, int width, int height){
        Image scaledImage = imageInput;
        scaledImage = scaledImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        finalImage.getGraphics().drawImage(scaledImage, 0, 0, null);

        return finalImage;
    }

}
