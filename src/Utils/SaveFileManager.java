package Utils;

import Game.GameCanvas;

import java.io.*;

public class SaveFileManager {
    private static String saveFilePath = "./save.txt";



    /*
        --------------------------------------------
                       File Format
        --------------------------------------------
        fullscreen (true/false)
        current level
     */
    public boolean loadFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFilePath));

            String line = reader.readLine();
            GameCanvas.isFullScreen = Boolean.parseBoolean(line);

            line = reader.readLine();
            GameCanvas.currentLevelProgress = Integer.parseInt(line);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public  void saveFile(boolean isFullScreen, int currentLevel){
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFilePath)));

            writer.write("" + isFullScreen);
            writer.write("\n" + currentLevel);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
