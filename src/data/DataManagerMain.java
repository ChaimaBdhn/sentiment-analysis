package data;

import java.util.Set;


public class DataManagerMain {
    public static void main(String[] args) {

        DataManager dm = new DataManager();
      //  Set<String> cleaned = dm.cleanAllTweets(args[0]);

    //    for(String cleanedTweet : cleaned) {
    //        System.out.println(cleanedTweet);
    //    }

        String outputFile = "./data/tweets-cleaned.csv"; // new File ?
        dm.CleanAllTweetsAndWriteTweets(args[0], outputFile);
    }
}
