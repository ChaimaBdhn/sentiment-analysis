package algo;

import distance.BasicDistance;
import distance.DistanceCalculation;
import distance.LevenshteinDistance;

public class KNNClassifierMain {
    public static void main(String[] args) {

        // String inputFile = "data/testdata.knn.csv";
        String inputFile = "data/testdata.manual.2009.06.14.csv";

        String tweetToTag = "I really hate that !";

        /* Initializes necessaries objects */
        DistanceCalculation basicDistance = new BasicDistance();
        DistanceCalculation levenshteinDistance = new LevenshteinDistance();
        
        /* Classifying according to a given distance method */
        KNNClassifier knnClassifierB = new KNNClassifier(inputFile, basicDistance);
        KNNClassifier knnClassifierL = new KNNClassifier(inputFile, levenshteinDistance);
        
        Polarity valueB = knnClassifierB.classifyTweet(tweetToTag,15); 
        System.out.println("Basic distance : " + valueB);

        Polarity valueL = knnClassifierL.classifyTweet(tweetToTag, 15);
        System.out.println("Levenshtein distance : " + valueL);

    }
}
