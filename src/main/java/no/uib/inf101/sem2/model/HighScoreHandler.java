package no.uib.inf101.sem2.model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class HighScoreHandler {
    private static final String FILENAME = "src/main/java/no/uib/inf101/sem2/model/highscore.txt";
    private int highscore = 0;

    /**
     * Gets the current high score from the file.
     * 
     * @return the current high score
     */
    public int getHighscore() {
        try {
            Path filePath = Paths.get(FILENAME);
            Scanner scanner = new Scanner(filePath);
            highscore = scanner.nextInt();
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
            e.printStackTrace();
        }
        return highscore;
    }

    /**
     * Sets the high score to the given value and writes it to the file.
     * 
     * @param highscore the new high score value to be set
     */
    public void setHighscore(int highscore) {
        try {
            FileWriter fileWriter = new FileWriter(FILENAME);
            fileWriter.write(Integer.toString(highscore));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
