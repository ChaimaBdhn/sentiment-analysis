package algo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import data.TweetCleaner;

/* An abstract class of a classifier algorithm that will be used with a given learning base */
public abstract class ClassifierAlgorithm {

    /* Learning base we are using */
    private Map<String, Integer> learningBase;

    /** A classifier algorithm is defined by its learning base initially cleaned and tagged
     * @param csvFile
     */
    public ClassifierAlgorithm(String csvFile) {
        this.learningBase = this.initLearningBase(csvFile);
    }


    /** Displays the learning base in form string : value 
     * @param learningBase the learning base cleaned and tagged
     */
    public void displayLearningBase() {
        this.learningBase.forEach((key, value) -> System.out.println("\"" + key + "\" : " + value));
    }

    /** Retrieves the learning base
     * @return the learning base
     */
    public Map<String, Integer> getLearningBase() {
        return this.learningBase;
    }

    /** Opens the csv file and creates a map that associates each tweet as a key, with its class (positive, negative or neutral) as a value
     * @param csvFile the csv file we change into a learning base
     * @return the map with each tweet associated with its class
     */
    public Map<String,Integer> initLearningBase(String csvFile) {
        Map<String, Integer> learningBase = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\",\"");
                
                int polarity = Integer.parseInt(columns[0].replace("\"", ""));
                String tweet = columns[columns.length - 1].replace("\"", "");

                // Nettoyage du tweet avec cleanAllTweets
                String cleanedTweet = TweetCleaner.cleanTweet(tweet);

                learningBase.put(cleanedTweet, polarity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return learningBase;
    }


    /** 
     * @return
     */
    // public abstract Integer execute() {
    //     todo 
    // }

    
}