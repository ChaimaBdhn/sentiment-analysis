package algo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import data.DataManager;

/* A class that represent the dictionnary classifier */
public class DictionnaryClassifier implements ClassifierAlgorithm{

    
    /* The positive sentiments words list*/
    private Set<String> positiveWords;


    /* The negative sentiments words list*/
    private Set<String> negativeWords;


    /*polarity */
    private static final int NEGATIVE = 0, NEUTRAL = 2, POSITIVE = 4 ; 


    private static final String OUTPUTCSVFILE = "./data/tweets_analyse.csv";


    public DictionnaryClassifier(){
        this.positiveWords = new HashSet<>();
        this.negativeWords = new HashSet<>();
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
       
        File positiveWords = new File (fileName);
        Scanner scanner = new Scanner (positiveWords);
        
        scanner.useDelimiter(",");

        while (scanner.hasNext()){
            String word = scanner.next().trim();

            setToSet.add(word);
        }
        scanner.close();
    }



 
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
            return POSITIVE;
        }
        else if (nb_negative_words > nb_positive_words){
            return NEGATIVE;
        }
        return NEUTRAL;

    }


    public void automaticAnnotation (File csvFile){


        // Fichier csvFile à ettoyer 
        DataManager dataManager = new DataManager(csvFile);

        
        File clean_file =   dataManager.cleanTweet();

        File tweets = new File (clean_file);
        Scanner scanner = new Scanner (tweets);

        HashMap <String,Integer> tweets_with_polarity = new HashMap<>();

        while (scanner.hasNext()){
            String tweet = scanner.next().trim();

           tweets_with_polarity.put(tweet, analyseOneTweet(tweet));
        }
        scanner.close();
     
        
       GenerateCSVFile (tweets_with_polarity);

    }


    private void GenerateCSVFile (HashMap<String,Integer> tweets_with_polarity){


       try {

            FileWriter fileWriter = new FileWriter(OUTPUTCSVFILE);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Tweet,polarity");

            for (String tweet : tweets_with_polarity.keySet()){
                
                tweet = tweet.replace (","," ");
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