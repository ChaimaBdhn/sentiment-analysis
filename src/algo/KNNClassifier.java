package algo;

import java.util.*;

import distance.DistanceCalculation;

/* This class aims to implement KNN algorithm */
public class KNNClassifier extends ClassifierAlgorithm implements Polarity {

    /* Calculation method we chose for KNN classification */
    DistanceCalculation calculationMethod;

    /** KNN Classifier is defined by its learning base initially cleaned and tagged and a specific calculation method
     * @param csvFile the learning base
     * @param calculationMethod the distance calculation method
     */
    public KNNClassifier(String csvFile, DistanceCalculation calculationMethod) {
        super(csvFile);
        this.calculationMethod = calculationMethod;
    }


    /** Calculates all distances between a tweet and each of its neighbor in a list
     * @param x the tweet 
     * @param nearestNeighbors list of neighbors (tweets)
     * @return a list containing all distances between a tweet and each of its neighbor
     */
    public List<Integer> allDistances(String x, List<String> nearestNeighbors) {
        List<Integer> distances = new ArrayList<>();

        for(String neighbor : nearestNeighbors) {
            int dist = this.calculationMethod.distance(neighbor, x);
            distances.add(dist);
        }
        return distances;
    }


    /** Returns the furthest tweet from a map
     * @param x the tweet we want to tag
     * @param tweets the map of tweets // todo
     * @return the furthest
     */
    public String getFurthestTweet(String x, Map<String, Integer> tweets) {
        String furthest = null;
        int maxDistance = -1;
        
        for(String twt : tweets.keySet()) {
            int dist = this.calculationMethod.distance(x, twt);
            
            if(dist > maxDistance) {
                maxDistance = dist;
                furthest = twt;
            }
        }
        return furthest;
    }
    

    /** Checks if an integer is less than any element in a list of integers.
     * @param target the integer we want to verify if it is lesser
     * @param values the list of integers
     * @return true if a given integer is less than at least one of the elements of the list, false if not
     */
    public boolean isLessThanAny(int target, List<Integer> values) {
        return values.stream().anyMatch(value -> target < value);
    }    


    /** Votes for the majority class
     * @param nearestNeighbors list of the nearest neighbors
     * @return the value of majority class
     * todo : should we use List<Integer> only in parameter ?
     */
    public Integer vote(Map<String, Integer> nearestNeighbors) {
        int negative_cpt = 0;
        int neutral_cpt = 0;
        int positive_cpt = 0;

        for(Integer value : nearestNeighbors.values()) {
            if(value.equals(Polarity.NEGATIVE))
                negative_cpt++;
            if(value.equals(Polarity.NEUTRAL))
                neutral_cpt++;
            if(value.equals(Polarity.POSITIVE))
                positive_cpt++;
        }

        if (positive_cpt > negative_cpt && positive_cpt > neutral_cpt) {
            return Polarity.POSITIVE;
        } else if (negative_cpt > positive_cpt && negative_cpt > neutral_cpt) {
            return Polarity.NEGATIVE;
        } else if (neutral_cpt > positive_cpt && neutral_cpt > negative_cpt) {
            return Polarity.NEUTRAL;
        } else {
            return Polarity.NEUTRAL;
        }
    }
    

//     public String vote(Map<String, String> nearestNeighbors) {
//         Map<String, Integer> counts = new HashMap<>();
//         counts.put(NEGATIVE, 0);
//         counts.put(NEUTRAL, 0);
//         counts.put(POSITIVE, 0);
//
//         for (String value : nearestNeighbors.values()) {
//             counts.put(value, counts.get(value) + 1);
//         }
//
//         return counts.entrySet().stream()
//                      .max(Map.Entry.comparingByValue())
//                      .get()
//                      .getKey();
//     }



    /** Classifies a tweet according to the KNN algorithm 
     * @param x the tweet to tag
     * @param k the number of neighbors
     * @return the majority class of the tweet : positive, negative or neutral
     */
    public Integer classifyTweet(String x, int k) {
        Map<String, Integer> proches_voisins = new HashMap<>();

        int count = 0; 
        for(String tweetNeighbor : super.getLearningBase().keySet()) {
            if(count <= k) {
                proches_voisins.put(tweetNeighbor, super.getLearningBase().get(tweetNeighbor)); // ajoute dans la map le tweet voisin (clé) et récupère sa valeur associée selon la clé pour l'ajouter
                count++;                
            } else {
                break;
            }
        }

        for(String tweetNeighbor : super.getLearningBase().keySet()) {
            // on parcourt le reste des tweets de la base à partir de k+1 (on prends donc ceux qui n'ont pas encore été ajoutés)
            if(!proches_voisins.containsKey(tweetNeighbor)) {
                int distToNeighbor = this.calculationMethod.distance(tweetNeighbor, x);
                List<Integer> distXtoProchesVoisins = allDistances(x, new ArrayList<>(proches_voisins.keySet()));

                if(this.isLessThanAny(distToNeighbor, distXtoProchesVoisins)) {
                    String furthestTweet = this.getFurthestTweet(x, proches_voisins);
                    proches_voisins.remove(furthestTweet);
                    proches_voisins.put(tweetNeighbor, super.getLearningBase().get(tweetNeighbor));
                }
            }
        }
        return vote(proches_voisins);
    }


}
