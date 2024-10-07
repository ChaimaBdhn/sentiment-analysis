package data;

import java.util.Set;

public class DataManagerMain {
    public static void main(String[] args) {

        DataManager dm = new DataManager(args[0]);

        Set<String> uniques = dm.removeDuplicateTweets(dm.getFile());
        Set<String> cleaned = dm.CleanAllTweets(uniques);

        for(String cleanedTweet : cleaned) {
            System.out.println(cleanedTweet);
        }

        String outputFile = "tweets-cleaned.csv"; // new File ?
        dm.writeTweets(cleaned, outputFile);

//        readCSV(outputFile);
    }
}
