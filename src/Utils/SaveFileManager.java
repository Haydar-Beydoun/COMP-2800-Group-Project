package Utils;

import Game.GameCanvas;

import java.io.*;

public class SaveFileManager {
    private static String saveFilePath = "src/resources/save.txt";



    /*
        --------------------------------------------
                       File Format
        --------------------------------------------
        fullscreen (true/false)
        current level
     */
    public static boolean loadFile(){
        if(!saveExists()){
            return false;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFilePath));

            String line = reader.readLine();
            GameCanvas.isFullScreen = Boolean.parseBoolean(line);

            line = reader.readLine();
            GameCanvas.currentLevel = Integer.parseInt(line);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

    public static void saveFile(boolean isFullScreen, int currentLevel){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath));
            writer.write("" + isFullScreen);
            writer.write("\n" + currentLevel);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean saveExists(){
        File file = new File(saveFilePath);

        return file.exists();
    }
}
