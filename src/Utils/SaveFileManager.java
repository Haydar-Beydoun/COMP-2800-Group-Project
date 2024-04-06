package Utils;

import Game.GameCanvas;

import java.io.*;


/**
 * SaveFileManager class to save the progress of the User
 */
public class SaveFileManager {
    private static String saveFilePath = "./save.txt";



    /*
        --------------------------------------------
                       File Format
        --------------------------------------------
        fullscreen (true/false)
        current level
     */
    /**
     * Loads the save file.
     * @return boolean to check if the file is loaded successfully.
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

    /**
     * Save the progress of the player and whether the player is in full screen or not in a file.
     * <br>
     * Creates a new file if it doesn't exist.
     * @param isFullScreen boolean to save if the player is in fullscreen.
     * @param currentLevel Integer to save the level progress of the player.
     */
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
