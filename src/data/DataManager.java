package data;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


/* Class aiming to clean tweets from specific characters */
public class DataManager {

    private Set<String> cleanedTweets;


    public DataManager (){
        cleanedTweets = new HashSet<String>();
    }


   /**
    * 
    * @param csvFile
    * @return
    */
    public Set<String> cleanAllTweets(String csvFile) {
        
        Set<String> uniqueTweets = this.removeDuplicateTweets(csvFile);

        for(String tweet : uniqueTweets) {
            String cleaned = TweetCleaner.cleanTweet(tweet);
            this.cleanedTweets.add(cleaned); // adds the cleaned tweet
        }
        return this.cleanedTweets; // returns the cleaned set
    }





    /** Remove duplicated tweets by using a hashset : stores each tweet in the hashset so that it contains only unique tweets,
     * ignoring the duplicated ones (property of hashset)
     * @param csvFile the file containing all the tweets
     * 
     */
    private Set<String> removeDuplicateTweets(String csvFile) {
        Set<String> uniqueTweets = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String tweet = values[5]; // Each tweet is in the sixth column (index 5)
                uniqueTweets.add(tweet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uniqueTweets;
    }



    /** Writes each tweet from the hashset in an output file
     * @param tweets
     * @param outputFile 
     */
    public void writeTweets(Set<String> cleanedtweets, String outputFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String tweet : cleanedtweets) {
                bw.write(tweet);
                bw.newLine(); // New line after each tweet
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * clean given csv file and write all cleaned tweets in a output file (its path is given in parameter)
     * @param csvFileToClean path to the csv file to clean
     * @param outputFile path to the output file
     */
    public void CleanAllTweetsAndWriteTweets (String csvFileToClean, String outputFile){
        
        this.writeTweets(this.cleanAllTweets(csvFileToClean), outputFile);
    }

    




    /** Gets the tweets written in French todo
     * @param initialTweet
     */
    public String getFrenchTweets(String initialTweet) {
        return "0";
    }





}
