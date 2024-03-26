import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

    public int rows;
    public int columns;
    private BufferedImage sheet = null;
    public BufferedImage[] images;
    public ImageIcon[] icons;

    public SpriteSheet(String filePath, int rows, int columns, int cellSize) {
        try {
            this.sheet = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.rows = rows;
        this.columns = columns;
        this.images = new BufferedImage[rows * columns];
        this.icons = new ImageIcon[rows * columns];

        int index = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                images[index] = sheet.getSubimage(j * cellSize, i * cellSize, cellSize, cellSize);
                icons[index] = new ImageIcon(images[index]);
                index++;
            }
        }
    }
}
