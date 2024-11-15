package algo;

import distance.BasicDistance;
import distance.DistanceCalculation;
// import distance.LevenshteinDistance;

public class KNNClassifierMain {
    public static void main(String[] args) {

        String inputFile = "data/testdata.knn.csv";

        String tweetToTag = "A beautiful day!";


        /* Initializes necessaries objects */
        DictionnaryClassifier dicoClassifier = new DictionnaryClassifier();
        DistanceCalculation basicDistance = new BasicDistance();
        // DistanceCalculation levenshteinDistance = new LevenshteinDistance();

        
        /* Classifying according to a distance method given in parameter */
        KNNClassifier knnClassifier = new KNNClassifier(inputFile, basicDistance);
        knnClassifier.displayLearningBase();

        // Integer value = knnClassifier.classifyTweet(tweetToTag,5);
        // System.out.println(value);
    }
}
