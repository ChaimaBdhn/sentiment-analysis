package data;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


/* Class aiming to clean tweets from specific characters */
public class DataManager {


    private static final String HASHTAG_REGEX = "#\\w+";
    private static final String AT_REGEX = "@\\w+";
    private static final String RT_REGEX = "\\bRT\\b";
    private static final String URL_REGEX = "https?://\\S+\\s+?";
    
    private static final String POSITIVE_EMOTICONS = ":\\)|:-\\)|:D|\\(:|<3|=\\)|\\(=";
    private static final String NEGATIVE_EMOTICONS = ":\\(|:'\\(|:/|\\):|\\)':";

    private String file;

    public DataManager(String filePath) {
        this.file = filePath;
    }

    public String getFile() {
        return this.file;
    }


    /** Cleans a tweet from its url, @, RT, #
     * @param initialTweet
     * @return the tweet cleaned
     */
    private String cleanTweet(String initialTweet) {
        initialTweet = initialTweet.replaceAll(HASHTAG_REGEX, "");
        initialTweet = initialTweet.replaceAll(AT_REGEX, "");
        initialTweet = initialTweet.replaceAll(RT_REGEX, "");
        initialTweet = initialTweet.replaceAll(URL_REGEX, "");

        return initialTweet.trim(); // deletes spaces that would be caused by the replacement
    }
    

    /** Deletes positive AND negative emoticons contained in a given tweet
     * @param initialTweet given 
     * @return the new tweet cleaned from emocticons
     */
    private String removeEmoticons(String initialTweet) {
        if(initialTweet.contains(POSITIVE_EMOTICONS) && initialTweet.contains(NEGATIVE_EMOTICONS)) {
            initialTweet = initialTweet.replaceAll(POSITIVE_EMOTICONS, "");
            initialTweet = initialTweet.replaceAll(NEGATIVE_EMOTICONS, "");
        }
        return initialTweet.trim();
    }

    /**
     * 
     */
    public Set<String> CleanAllTweets(Set<String> uniqueTweets) {
        Set<String> cleanedTweets = new HashSet<>();

        for(String tweet : uniqueTweets) {
            String cleaned = this.cleanTweet(tweet);
            cleaned = this.removeEmoticons(cleaned);
            cleanedTweets.add(cleaned); // adds the cleaned tweet
        }
        return cleanedTweets; // returns the cleaned set
    }
    
    
    /** Remove duplicated tweets by using a hashset : stores each tweet in the hashset so that it contains only unique tweets,
     * ignoring the duplicated ones (property of hashset)
     * @param csvFile the file containing all the tweets
     * 
     */
    public Set<String> removeDuplicateTweets(String csvFile) {
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
    public void writeTweets(Set<String> tweets, String outputFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String tweet : tweets) {
                bw.write(tweet);
                bw.newLine(); // New line after each tweet
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    /** Gets the tweets written in French
     * @param initialTweet
     */
    public String getFrenchTweets(String initialTweet) {
        return "0";
    }


}
