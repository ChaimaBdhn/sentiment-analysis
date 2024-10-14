package data;

import java.util.Set;

public class DataManagerMain {
    public static void main(String[] args) {

        DataManager dm = new DataManager(args[0]);
        Set<String> cleaned = dm.CleanAllTweets();

//        for(String cleanedTweet : cleaned) {
//            System.out.println(cleanedTweet);
//        }

        String outputFile = "tweets-cleaned.csv"; // new File ?
        dm.writeTweets(cleaned, outputFile);

//        readCSV(outputFile);
    }
}
