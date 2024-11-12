package distance;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BasicDistance implements DistanceCalculation {

    private static final String REGEX = "[,\\.\\s]+";

    
    /** Counts total words of both tweets
     * @param tweet1 the first tweet
     * @param tweet2 the second tweet
     * @return the total number of words 
     */
    public int totalWords(String tweet1, String tweet2) {
        String[] firstTweetWords = tweet1.toLowerCase().split(REGEX);
        String[] secondTweetWords = tweet2.toLowerCase().split(REGEX);

        return firstTweetWords.length + secondTweetWords.length;
    }


    /** Counts the total number of identical words between 2 tweets
     * @param tweet1 the first tweet
     * @param tweet2 the second tweet
     * @return the total number of identical words of two tweets
     */
    public int nbIdenticalWords(String tweet1, String tweet2) {
        int counter = 0;
        String[] firstTweetWords = tweet1.toLowerCase().split(REGEX);
        String[] secondTweetWords = tweet2.toLowerCase().split(REGEX);

        // We put words of the second tweet in a hashset
        Set<String> wordsInSecondTweet = new HashSet<>(Arrays.asList(secondTweetWords));
        // Verifying if the list of words of the second tweet contains each word of the first tweet
        for(String word : firstTweetWords) {
            if(wordsInSecondTweet.contains(word)) {
                counter++;
            }
        }
        return counter;
    }



    @Override
    /**
     * {@inheritDoc}
     */
    public int distance(String tweet1, String tweet2) {
        int totalWords = this.totalWords(tweet1, tweet2);
        int identicalWords = this.nbIdenticalWords(tweet1, tweet2);

        int distance = (totalWords - identicalWords) / totalWords;
        return distance;
    }



}
