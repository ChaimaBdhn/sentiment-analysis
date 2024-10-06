package data;

import java.util.ArrayList;
import java.util.regex.*;


/* Class aiming to clean tweets from specific characters */
public class DataManager {


    private static final String HASHTAG_REGEX = "#\\w+";
    private static final String AT_REGEX = "@\\w+";
    private static final String RT_REGEX = "\\bRT\\b";
    private static final String URL_REGEX = "https?://\\S+\\s+?";
    
    private static final String POSITIVE_EMOTICONS = ":\\)|:-\\)|:D|\\(:|<3";
    private static final String NEGATIVE_EMOTICONS = ":\\(|:'\\(|:/|\\):|\\)':";

    // private void initMatchersList(String initialTweet) {
    //     // Creating regex patterns to compare with the given tweet
    //     Pattern p_hashtag = Pattern.compile(HASHTAG_REGEX);
    //     Pattern p_at = Pattern.compile(AT_REGEX);
    //     Pattern p_rt = Pattern.compile(RT_REGEX);
    //     Pattern p_url = Pattern.compile(URL_REGEX);

    //     Matcher m_hashtag = p_hashtag.matcher(initialTweet);
    //     Matcher m_at = p_at.matcher(initialTweet);
    //     Matcher m_rt = p_rt.matcher(initialTweet);
    //     Matcher m_url = p_url.matcher(initialTweet);
        
    //     this.matchersList.add(m_hashtag);
    //     this.matchersList.add(m_at);
    //     this.matchersList.add(m_rt);
    //     this.matchersList.add(m_url);
    // }


    /** Cleans a tweet from its url, @, RT, #
     * @param initialTweet
     * @return the tweet cleaned
     */
    public String cleanTweet(String initialTweet) {
        initialTweet = initialTweet.replaceAll(HASHTAG_REGEX, "");
        initialTweet = initialTweet.replaceAll(AT_REGEX, "");
        initialTweet = initialTweet.replaceAll(RT_REGEX, "");
        initialTweet = initialTweet.replaceAll(URL_REGEX, "");

        return initialTweet.trim(); // deletes spaces that would be caused by the replacement
    }
    

    /** Gets the tweets written in French
     * @param initialTweet
     */
    public String getFrenchTweets(String initialTweet) {
        return "";
    }
    

    /** Deletes positive AND negative emoticons contained in a given tweet
     * @param initialTweet given 
     * @return the new tweet cleaned from emocticons
     */
    public String deleteEmoticons(String initialTweet) {
        if(initialTweet.contains(POSITIVE_EMOTICONS) && initialTweet.contains(NEGATIVE_EMOTICONS)) {
            initialTweet = initialTweet.replaceAll(POSITIVE_EMOTICONS, "");
            initialTweet = initialTweet.replaceAll(NEGATIVE_EMOTICONS, "");
        }
        return initialTweet.trim();
    }
    
    
    /** Remove duplicated tweets by using an hashset : stores each tweet in the hashset so that it contains only unique tweets,
     * ignoring the duplicated ones (property of hashset)
     * @param csvFile the file containing all the tweets
     */
    public Set<String> removeDuplicateTweets(File csvFile) {
        Set<String> uniqueTweets = new HashSet<>(); // where we'll store non duplicated tweets

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


    /** 
     * 
     */
    public void writeTweets(Set<String> tweets, File output) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String tweet : uniqueTweets) {
                bw.write(tweet);
                bw.newLine(); // New line after each tweet
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
