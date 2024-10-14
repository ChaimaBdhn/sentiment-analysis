package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* This class aims to implement KNN algorithm */
public class KNNClassifier {

    private static final String REGEX = "[,\\.\\s]+";

    private static final int NEGATIVE = 0, NEUTRAL = 2, POSITIVE = 4 ; 


    public KNNClassifier() { }

    /** Counts total word of both tweets ???
     *
     * @param 
     * @return
     */
    public int totalWords(String firstTweet, String secondTweet) {
        String firstTweetWords[] = firstTweet.toLowerCase().split(REGEX);
        String secondTweetWords[] = secondTweet.toLowerCase().split(REGEX);
        return firstTweetWords.length + secondTweetWords.length;
    }


    /** Counts the total number of identical words between 2 tweets
     * @param firstTweet the first tweet
     * @param secondTweet the second tweet
     * @return the total number
     */
    public int nbIdenticalWords(String firstTweet, String secondTweet) {
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



    /**
     * @param firstTweet
     * @param secondTweet
     * @return
     */
    public int distance(String firstTweet, String secondTweet) {
        int dist = (this.totalWords(firstTweet, secondTweet) - this.nbIdenticalWords(firstTweet, secondTweet) /  this. totalWords(firstTweet, secondTweet));

        return dist;
    }


    /** Reads the file and gives a learning base
     * @param csvFile the file containing tweets
     * @return the list containing all tweets of the file (learning base)
     */
    public List<String> readFile(File csvFile) {
        List <String> learningBase = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                learningBase.addAll(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return learningBase;
    }


    /**
     *
     * @param tweet
     * @param learningBase
     * @param k the number of neighbors
     * @return
     */
    public String classifyTweet(String tweet, List<String> learningBase, int k) {

        for(String tweet : learningBase) {

        }
    }


}
