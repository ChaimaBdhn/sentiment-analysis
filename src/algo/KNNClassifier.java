package algo;

import java.util.*;

import distance.DistanceCalculation;

/* This class aims to implement KNN algorithm */
public class KNNClassifier extends ClassifierAlgorithm {

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


    /** Determines the majority class
     * @param neighbors list of the nearest neighbors
     * @return the value of majority class
     * todo : should we use List<Integer> only in parameter ?
     */
    public Integer vote(Collection<Integer> neighbors) {
        int cptPositif = 0;
        int cptNegatif = 0;
        int cptNeutre = 0;
    
        // Parcourez la map des voisins pour compter les occurrences des classes
        for (Integer value : neighbors) {
            if (value.equals(Polarity.POSITIVE.getValue())) {
                cptPositif++;
            } else if (value.equals(Polarity.NEGATIVE.getValue())) {
                cptNegatif++;
            } else if (value.equals(Polarity.NEUTRAL.getValue())) {
                cptNeutre++;
            }
        }
    
        if (cptNeutre >= cptPositif && cptNeutre >= cptNegatif) {
            return Polarity.NEUTRAL.getValue();
        } else if (cptPositif > cptNegatif) {
            return Polarity.POSITIVE.getValue();
        } else {
            return Polarity.NEGATIVE.getValue();
        }
    }



    /** Classifies a tweet according to the KNN algorithm 
     * @param x the tweet to tag
     * @param k the number of neighbors
     * @return the majority class of the tweet : positive, negative or neutral
     */
    public Polarity classifyTweet(String x, int k) {
        Map<String, Integer> proches_voisins = new HashMap<>();
        List<String> learningBaseKeys = new ArrayList<>(this.getLearningBase().keySet());
        
        // 1. Adding k firsts nearest neighbors
        int count = 0;
        while (count < k && count < learningBaseKeys.size()) {
            String tweetNeighbor = learningBaseKeys.get(count); // Retrieves the neighbor tweet at index "count"
            proches_voisins.put(tweetNeighbor, this.getLearningBase().get(tweetNeighbor)); // Adds the neighbor tweet and its value to the list containing nearest neighbors
            count++;
        }
        
        // 2. (k+1 to N) : other neighbors
        for (int i = k; i < learningBaseKeys.size(); i++) {
            String tweetNeighbor = learningBaseKeys.get(i);
            int distToNeighbor = this.calculationMethod.distance(tweetNeighbor, x); // Gets distance between tweet x and the neighbor i

            // Retrieves distances of actual k neighbors 
            List<Integer> distXtoProchesVoisins = allDistances(x, new ArrayList<>(proches_voisins.keySet()));
            // Verifies if distance between x and this neighbor is smaller than one of a distance in nearest neighbors
            if (this.isLessThanAny(distToNeighbor, distXtoProchesVoisins)) {
                String furthestTweet = this.getFurthestTweet(x, proches_voisins);

                proches_voisins.remove(furthestTweet); // Removes the furthest tweet from x 
                proches_voisins.put(tweetNeighbor, this.getLearningBase().get(tweetNeighbor)); // Adds new neighbor
            }
        }
        // proches_voisins.forEach((key, value) -> System.out.println(key + " : " + value));

        return Polarity.fromValue(vote(proches_voisins.values()));
    }


}

