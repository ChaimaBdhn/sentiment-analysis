import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/* A class that represent the dictionnary classifier */
public class DictionnaryClassifier implements ClassiferAlgorithm{

    
    /* The positive sentiments words list*/
    private ArrayList< String> positiveWords;


    /* The negative sentiments words list*/
    private ArrayList< String> negativeWords;


    /*polarity */
    private static final int NEGATIVE = 0, NEUTRAL = 2, POSITIVE = 4 ; 


    public DictionnaryClassifier(){
        this.positiveWords = new ArrayList<>();
        this.negativeWords = new ArrayList<>();
        this.loadData();
    }

    /* initialization of lists with positives and negatives words */
    
    private void loadData (){

        try{
            this.loadDataInList("./data/positive.txt",this.positiveWords);
            this.loadDataInList("./data/negative.txt",this.negativeWords);

        }catch (FileNotFoundException e ){
            e.printStackTrace();
        }

    }


    private void loadDataInList(String fileName, ArrayList<String> listToSet) throws FileNotFoundException {
       
        File positiveWords = new File (fileName);
        Scanner scanner = new Scanner (positiveWords);
        
        scanner.useDelimiter(",");

        while (scanner.hasNext()){
            String word = scanner.next().trim();

            listToSet.add(word);
        }
        scanner.close();
    }



 

    // /* train the algorithm for the classification
    //  * @param textFile 
    //  */
    // public void train(File textFile){

    //     // To complete

    // }


    /* Classify data  */

    public File classify(File csvFile){

        //To complete


    }


}