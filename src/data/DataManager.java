package data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
           // System.out.println("Before : " + tweet);
            String cleaned = TweetCleaner.cleanTweet(tweet);
           // System.out.println(cleaned);
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
               
                String[] values = splitRespectingQuotes(line);
                if (values.length < 5){
                    System.out.println(values[0]);
                }
                String tweet = values[5]; // Each tweet is in the sixth column (index 5)
              
                uniqueTweets.add(tweet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uniqueTweets;
    }

  private String[] splitRespectingQuotes(String line) {
        List<String> columns = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;  // Alterne l'état de inQuotes à chaque guillemet
            } else if (c == ',' && !inQuotes) {
                columns.add(current.toString().trim());  // Ajoute la colonne quand on est en dehors des guillemets
                current = new StringBuilder();  // Réinitialise le StringBuilder
            } else {
                current.append(c);  // Ajoute le caractère à la colonne actuelle
            }
        }
        columns.add(current.toString().trim());  // Ajoute la dernière colonne
        return columns.toArray(new String[0]);
    }



    /** Writes each tweet from the hashset in an output file
     * @param tweets
     * @param outputFile 
     */
    public void writeTweets(Set<String> cleanedtweets, String outputFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String tweet : cleanedtweets) {
                tweet = tweet.replace("\"", ""); // Supprime les guillemets internes
                bw.write("\"" + tweet + "\""); // Encadre chaque tweet avec des guillemets
                bw.newLine(); 
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
