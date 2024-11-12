package data;

public class DataManagerMain {
    public static void main(String[] args) {

        DataManager dm = new DataManager();

        String outputFile = "./data/tweets-cleaned.csv";
        dm.CleanAllTweetsAndWriteTweets(args[0], outputFile);
    }
}
