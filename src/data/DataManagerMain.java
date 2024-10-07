package data;

import java.io.File;
import java.util.Set;

import static data.CSVReader.*;

public class DataManagerMain {
    public static void main(String[] args) {

        DataManager dm = new DataManager(args[0]);

        Set<String> uniques = dm.removeDuplicateTweets(dm.getFile());
        Set<String> cleaned = dm.CleanAllTweets(uniques);

        for(String cleanedTweet : cleaned) {
            System.out.println(cleanedTweet);
        }

        String outputFile = "tweets-cleaned.csv";
        dm.writeTweets(cleaned, outputFile);

//        readCSV(outputFile);
    }
}
