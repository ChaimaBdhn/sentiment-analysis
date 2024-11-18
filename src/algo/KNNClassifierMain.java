package algo;

import distance.BasicDistance;
import distance.DistanceCalculation;
import distance.LevenshteinDistance;

public class KNNClassifierMain {
    public static void main(String[] args) {

        String inputFile = "data/testdata.knn.csv";

        String tweetToTag = "A beautiful day!";


        /* Initializes necessaries objects */
        DistanceCalculation basicDistance = new BasicDistance();
        DistanceCalculation levenshteinDistance = new LevenshteinDistance();

        
        /* Classifying according to a distance method given in parameter */
        KNNClassifier knnClassifierB = new KNNClassifier(inputFile, basicDistance);
        KNNClassifier knnClassifierL = new KNNClassifier(inputFile, levenshteinDistance);

        // knnClassifier.displayLearningBase();

        Integer valueB = knnClassifierB.classifyTweet(tweetToTag,5);
        System.out.println("basic distance : " + valueB);

        Integer valueL = knnClassifierL.classifyTweet(tweetToTag,5);
        System.out.println("levenshtein distance : " + valueL);
    }
}
