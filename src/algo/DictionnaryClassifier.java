package algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import data.DataManager;

/* A class that represent the dictionnary classifier */
public class DictionnaryClassifier {
    
    /* The positive sentiments words list*/
    private Set<String> positiveWords;

    /* The negative sentiments words list*/
    private Set<String> negativeWords;


    public static final String OUTPUTFILE = "./data/tweets_analyse";
    private static final String FORMAT_OUTPUT_FILE =".csv";
    private int index_file_generate;

    public DictionnaryClassifier(){
        this.positiveWords = new HashSet<>();
        this.negativeWords = new HashSet<>();
        this.index_file_generate = 0;
        this.loadData();
    }

    /* initialization of sets with positives and negatives words */
    private void loadData (){

        try{
            this.loadDataInSet("./data/positive.txt",this.positiveWords);
            this.loadDataInSet("./data/negative.txt",this.negativeWords);

        }catch (FileNotFoundException e ){
            e.printStackTrace();
        }

    }

    /**
     * Load words contening in the file in the given set
     * @param fileName
     * @param setToSet
     * @throws FileNotFoundException
     */
    private void loadDataInSet(String fileName, Set<String> setToSet) throws FileNotFoundException {
       
        File words = new File (fileName);
        Scanner scanner = new Scanner (words);
        
        scanner.useDelimiter(",");

        while (scanner.hasNext()){
            String word = scanner.next().trim();

            setToSet.add(word);
        }
        scanner.close();
    }


    /**
     * 
     * @param tweet
     * @return
     */   
    private int analyseOneTweet (String tweet) {

        int nb_positive_words = 0;
        int nb_negative_words = 0;

        String [] words = tweet.toLowerCase().split("\\s+"); 

        for (String word : words ){
            if (this.positiveWords.contains(word)) {
                nb_positive_words ++;
            }
            else if (this.negativeWords.contains(word)){
                nb_negative_words ++;
            }
        }
        if (nb_negative_words < nb_positive_words) {
            return Polarity.POSITIVE.getValue();
        }
        else if (nb_negative_words > nb_positive_words){
            return Polarity.NEGATIVE.getValue();
        }
        return Polarity.NEUTRAL.getValue();
    }

    /**
     * 
     * @param csvFile
     */
    public void automaticAnnotation (String csvFile) {
        // Fichier csvFile à nettoyer 
        DataManager dataManager = new DataManager();

        Set<String> cleanedTweets = dataManager.cleanAllTweets(csvFile);

        HashMap <String,Integer> tweets_with_polarity = new HashMap<>();

        for (String tweet : cleanedTweets ){
            int polarity = analyseOneTweet(tweet);
           tweets_with_polarity.put(tweet, polarity);
           //System.out.println(tweet + " : " + polarity);
        }
        // for (String key : tweets_with_polarity.keySet()){
        //     System.out.println(key + " :    " + tweets_with_polarity.get(key));
        // }
       GenerateCSVFile (tweets_with_polarity);
    }

    
    /**
     * @param tweets_with_polarity
     */
    private void GenerateCSVFile (HashMap<String,Integer> tweets_with_polarity){
       try {
            index_file_generate ++;
            FileWriter fileWriter = new FileWriter(OUTPUTFILE.concat("-").concat(Integer.toString(index_file_generate).concat(FORMAT_OUTPUT_FILE)));
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // printWriter.println("Tweet,polarity");

            for (String tweet : tweets_with_polarity.keySet()){
                
                // tweet = tweet.replace (","," ");
                // System.out.println(tweet);
                printWriter.println(tweet + "," + tweets_with_polarity.get(tweet));               
            }
            printWriter.close();
        } 
        catch(IOException e){
            e.printStackTrace();
        }
    }
        
       

    // /* train the algorithm for the classification
    //  * @param textFile 
    //  */
    // public void train(File textFile){

    //     // To complete

    // }


    /* Classify data  */

    // public File classify(File csvFile){

    //     //To complete


    // }


}