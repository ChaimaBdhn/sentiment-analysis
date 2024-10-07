package data;

import java.io.File;

public class DataManagerMain {
    public static void main(String[] args) {

        DataManager dm = new DataManager(args[0]);

        uniques = removeDuplicateTweets(dm.getFile());
        cleaned = dm.CleanAllTweets(uniques);

        File outputFile = new File("tweets-cleaned.csv")
        dm.writeTweets(cleaned, outputFile);

        readCSV(outputFile);
    }
}
