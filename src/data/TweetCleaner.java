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
    "(:\\s*\\)|:-\\s*\\)|:D|:-\\s*D|\\(\\s*:\\s*|<\\s*3|\\^\\_\\^|\\^\\.\\^|:\\s*\\(|:-\\s*\\(|:'\\s*\\(|:O|:o|:p|:P|:/|:-\\s*/|:-\\||=\\s*\\(|=\\s*\\)|\\(\\s*=|>\\s*:\\(|T_T|:-\\s*\\*|;\\s*\\)|;\\s*-\\s*|;\\s*D|;\\s*\\(|;\\s*\\]|:\\s*\\[\\{|:-\\s*[\\[\\{]|:[-]?\\)|:\\s*\\(|:-\\s*\\[\\{|:-\\s*\\]|:-\\s*\\}|:-\\s*3|:\\s*\\d+|:\\s*|:3|:~\\s*\\)|:~\\s*\\(|\\(\\s*:\\s*|\\(\\s*:-\\s*|\\(\\s*:D|\\(\\s*:p|;\\s*\\]|\\|:|:[\\p{Punct}]|:]|=\\s*\\])";

    /**
     * Cleans superflu data on a given tweet
     * @param initialTweet
     * @return
     */
    public static String cleanTweet (String initialTweet){
       
        String tweetWithoutSuperfluData = decodeHtmlEntities(initialTweet); // Décodage des entités HTML 
        tweetWithoutSuperfluData =  removeSuperfluData(tweetWithoutSuperfluData);
        return tweetWithoutSuperfluData;
         
    }


    /** Cleans a tweet from its url, @, RT, #
     * @param initialTweet
     * @return the tweet cleaned
     */
    private static String removeSuperfluData(String initialTweet) {
    
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

    
        initialTweet = removePattern(initialTweet, TEXT_EMOJI_REGEX);


        // Retourner le tweet nettoyé sans espaces superflus
        return initialTweet.trim().replaceAll("\\s+", " ");
    }
    
    private static String removePattern(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        
        while (matcher.find()) {
            matcher.appendReplacement(sb, ""); // Remplace la correspondance par une chaîne vide
        }
        matcher.appendTail(sb); // Ajoute le reste de l'input à sb
        
        return sb.toString(); // Retourne la chaîne finale nettoyée
    }

  // Méthode pour décoder les entités HTML courantes
  private static String decodeHtmlEntities(String input) {
    return input.replace("&amp;", "&")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "\"")
                .replace("&apos;", "'")
                .replace("&#39;", "'");
}

    
}