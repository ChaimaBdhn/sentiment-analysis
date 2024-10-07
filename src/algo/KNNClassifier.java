package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* This class aims to implement KNN algorithm */
public class KNNClassifier {

    private static final String REGEX = "[,\\.\\s]+";

    public KNNClassifier() { }

    /** Counts total word of a tweet ??? todo
     *
     * @param csv
     * @return
     */
    private int countWords(File csv) {
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(",");
                String tweet = words[5]; // Each tweet is in the sixth column (index 5)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  0; // todo
    }


    /** Counts the total number of identical words between 2 tweets
     * @param firstTweet the first tweet
     * @param secondTweet the second tweet
     * @return the total number
     */
    private int nbIdenticalWords(String firstTweet, String secondTweet) {
        int counter = 0;
        String firstTweetWords[] = firstTweet.toLowerCase().split(REGEX);
        String secondTweetWords[] = secondTweet.toLowerCase().split(REGEX);

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




    private int distance(String firstTweet, String secondTweet) {

    }


}
