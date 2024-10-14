package data;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TweetCleaner {



    private static final String HASHTAG_REGEX = "#\\w+";
    private static final String AT_REGEX = "\\s*@\\s*([\\w\\.-]+)\\s*";
    private static final String RT_REGEX = "\\bRT\\b";
    private static final String URL_REGEX = "https?://[\\w\\.-]+(:\\d+)?(/\\S*)?";
    
    
    private static final String EMOJI_REGEX = "[\\ud83c\\udf00-\\ud83d\\ude4f]|[\\ud83d\\ude80-\\ud83d\\udeff] ";
    
    private static final String TEXT_EMOJI_REGEX = 
    "(:\\)|:-\\)|:D|:-D|\\(:|<3|\\^\\_\\^|\\^\\.\\^|:\\(|:-\\(|:'\\(|:O|:o|:p|:P|:/|:-/|:-\\||=\\(|=\\)|\\(=|>:\\(|T_T|:-\\*|;\\)|;\\-|;D|;\\(|;\\]|:\\[\\{]|:-[\\[\\{]|:[-]?\\)|:\\s*\\(|:-\\[\\{|:-\\]|:-\\}|:-3|:\\s*\\d+|:\\s*|:3|:~\\)|:~\\(|\\(\\s*:|\\(\\s*:-|\\(\\s*:D|\\(\\s*:p|;\\s*\\]|\\|:|:[\\p{Punct}])";

    /**
     * Cleans superflu data on a given tweet
     * @param initialTweet
     * @return
     */
    public static String cleanTweet (String initialTweet){

        String tweetWithoutData =  removeData(initialTweet);
        return tweetWithoutData;
         
    }


    /** Cleans a tweet from its url, @, RT, #
     * @param initialTweet
     * @return the tweet cleaned
     */
    private static String removeData(String initialTweet) {
    
        // Suppression des hashtags
        initialTweet = removePattern(initialTweet, HASHTAG_REGEX);
    
        // Suppression des mentions
        initialTweet = removePattern(initialTweet, AT_REGEX);
        
        // Suppression des retweets
        initialTweet = removePattern(initialTweet, RT_REGEX);
        
        // Suppression des URLs
        initialTweet = removePattern(initialTweet, URL_REGEX);
        
        // Suppression des émojis
        initialTweet = removePattern(initialTweet, EMOJI_REGEX);

        // 
        initialTweet = removePattern(initialTweet, TEXT_EMOJI_REGEX);


        // Retourner le tweet nettoyé sans espaces superflus
        return initialTweet.trim().replaceAll("\\s+", " ");
    }
    
    private static String removePattern(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(""); // Remplace tous les éléments trouvés par une chaîne vide
    }



    
}