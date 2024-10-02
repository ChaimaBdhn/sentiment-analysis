package data;

import java.util.ArrayList;
import java.util.regex.*;


/* Class aiming to clean tweets */
public class DataManager {


    private static final String HASHTAG_REGEX = "#\\w+";
    private static final String AT_REGEX = "@\\w+";
    private static final String RT_REGEX = "\\bRT\\b";
    private static final String URL_REGEX = "https?://\\S+\\s+?";
    
    private static final String POSITIVE_EMOTICONS = ":\\)|:-\\)|:D|\\(:|<3";
    private static final String NEGATIVE_EMOTICONS = ":\\(|:'\\(|:/|\\):|\\)':";

    private ArrayList<Matcher> matchersList; 


    /**
     *
     */
    public DataManager() {
        this.matchersList = new ArrayList<>();
    }


    /** Initializes a list containing matchers to verify if each corresponds to the different patterns created
     * @param initialTweet
     * @return the matchers list
     */
    private void initMatchersList(String initialTweet) {
        // Creating regex patterns to compare with the given tweet
        Pattern p_hashtag = Pattern.compile(HASHTAG_REGEX);
        Pattern p_at = Pattern.compile(AT_REGEX);
        Pattern p_rt = Pattern.compile(AT_REGEX);
        Pattern p_url = Pattern.compile(AT_REGEX);

        Matcher m_hashtag = p_hashtag.matcher(initialTweet);
        Matcher m_at = p_at.matcher(initialTweet);
        Matcher m_rt = p_rt.matcher(initialTweet);
        Matcher m_url = p_url.matcher(initialTweet);
        
        this.matchersList.add(m_hashtag);
        this.matchersList.add(m_at);
        this.matchersList.add(m_rt);
        this.matchersList.add(m_url);

    }


    /** Cleans a tweet from its url, @, RT, #
     * @param initialTweet
     * @return the tweet cleaned
     */
    private String cleanTweet(String initialTweet) {
        initMatchersList(initialTweet); // initializes a list with all matcher to compare with initial tweet
        for(Matcher m : this.matchersList) {
            if(m.find()) {
                //initialTweet.replaceAll(""); todo
            }
        }
    }


    /** Deletes positive AND negative emoticons contained in a given tweet
     * @param initialTweet
     */
    private String deleteEmoticons(String initialTweet) {
        return "";
    }

    /** Deletes duplicated tweets :
     * @param initialTweet
     */
    private String deleteDuplicatedTweets(String initialTweet) {
        return "";
    }

    /** Gets the tweets written in French
     * @param initialTweet
     */
    private String getFrenchTweets(String initialTweet) {
        return "";
    }



}
