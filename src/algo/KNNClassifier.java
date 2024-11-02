package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* This class aims to implement KNN algorithm */
public class KNNClassifier {

    private static final String REGEX = "[,\\.\\s]+";

    private static final String NEGATIVE = "0", NEUTRAL = "2", POSITIVE = "4" ;


    /** Counts total words of both tweets
     * @param firstTweet the first tweet
     * @param secondTweet the second tweet
     * @return the total number of words 
     */
    public int totalWords(String firstTweet, String secondTweet) {
        String firstTweetWords[] = firstTweet.toLowerCase().split(REGEX);
        String secondTweetWords[] = secondTweet.toLowerCase().split(REGEX);
        return firstTweetWords.length + secondTweetWords.length;
    }


    /** Counts the total number of identical words between 2 tweets
     * @param firstTweet the first tweet
     * @param secondTweet the second tweet
     * @return the total number of identical words of two tweets
     */
    public int nbIdenticalWords(String firstTweet, String secondTweet) {
        int counter = 0;
        String firstTweetWords[] = firstTweet.toLowerCase().split(REGEX);
        String secondTweetWords[] = secondTweet.toLowerCase().split(REGEX);

        // We put words of the second tweet in a hashset
        Set<String> wordsInSecondTweet = new HashSet<>(Arrays.asList(secondTweetWords));
        // Verifying if the list of words of the second tweet contains each word of the first tweet
        for(String word : firstTweetWords) {
            if(wordsInSecondTweet.contains(word)) {
                counter++;
            }
        }
        return counter;
    }



    /**
     * @param firstTweet the first tweet given
     * @param secondTweet the second tweet given 
     * @return the distance of two tweets
     */
    public int distance(String firstTweet, String secondTweet) {
        int dist = (this.totalWords(firstTweet, secondTweet) - this.nbIdenticalWords(firstTweet, secondTweet) /  this. totalWords(firstTweet, secondTweet));

        return dist;
    }


    /** Reads the file and gives a learning base
     * @param csvFile the file containing tweets
     * @return the list containing all tweets of the file (learning base)
     */
    // public List<String> readFile(File csvFile) {
    //     List <String> learningBase = new ArrayList<>();

    //     try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
    //         String line;
    //         while ((line = br.readLine()) != null) {
    //             String[] values = line.split(",");
    //             learningBase.addAll(Arrays.asList(values));
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return learningBase;
    // }



    /** Opens the csv file and create a map that associates each tweet with its class (positive, negative or neutral)
     * @param csvFile the csv file 
     * @return the map with each tweet associated with its class
     */
    public Map<String, String> initLearningBase(File csvFile) {
        Map<String, String> learningBase = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                String key = values[5];
                String value = values[0];
                learningBase.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return learningBase;
    }


    /** Calculates all distances between a tweet and each of its neighbor in a list
     * @param x
     * @param nearestNeighbors
     * @return
     */
    public List<Integer> allDistances(String x, List<String> nearestNeighbors) {
        List<Integer> distances = new ArrayList<>();

        for(String neighbor : nearestNeighbors) {
            int dist = this.distance(neighbor, x);
            distances.add(dist);
        }
        return distances;
    }


    /** Gets the greater distance between a given tweet and each nearest neighbor from a list
     * @param x the given tweet
     * @param nearestNeighbors the list of nearest neighbors
     * @return the greater distance
     */
    // public int getGreaterDistance(String x, List<String> nearestNeighbors) {
    //     List<Integer> distances = new ArrayList<>();

    //     for(String neighbor : nearestNeighbors) {
    //         int dist = this.distance(x, neighbor);
    //         distances.add(dist);
    //     }
    //     Collections.sort(distances);
    //     return distances.get(distances.size() - 1); 
    // }

    /**
     * @param x
     * @param tweets
     * @return
     */
    public String getFurthestTweet(String x, Map<String, String> tweets) {
        String furthest = null;
        int maxDistance = -1;
        
        for(String twt : tweets.keySet()) {
            int dist = this.distance(x, twt);
            
            if(dist > maxDistance) {
                maxDistance = dist;
                furthest = twt;
            }
        }
        return furthest;
    }
    

    /**
     * @param target
     * @param values
     * @return
     */
    public boolean isLessThanAny(int target, List<Integer> values) {
        return values.stream().anyMatch(value -> target < value);
    }    



    /** Votes for the majority class 
     * @param nearestNeighbors list of the nearest neighbors
     * @return the value of majority class
     */
    public String vote(Map<String, String> nearestNeighbors) {
        int negative_cpt = 0;
        int neutral_cpt = 0;
        int positive_cpt = 0;

        for(String value : nearestNeighbors.values()) {
            if(value.equals(NEGATIVE))
                negative_cpt++;
            if(value.equals(NEUTRAL))
                neutral_cpt++;
            if(value.equals(POSITIVE))
                positive_cpt++;
        }

        if (positive_cpt > negative_cpt && positive_cpt > neutral_cpt) {
            return POSITIVE;
        } else if (negative_cpt > positive_cpt && negative_cpt > neutral_cpt) {
            return NEGATIVE;
        } else if (neutral_cpt > positive_cpt && neutral_cpt > negative_cpt) {
            return NEUTRAL;
        } else {
            return NEUTRAL;
        }
    }
    

    // public String vote(Map<String, String> nearestNeighbors) {
    //     Map<String, Integer> counts = new HashMap<>();
    //     counts.put(NEGATIVE, 0);
    //     counts.put(NEUTRAL, 0);
    //     counts.put(POSITIVE, 0);
    
    //     for (String value : nearestNeighbors.values()) {
    //         counts.put(value, counts.get(value) + 1);
    //     }
    
    //     return counts.entrySet().stream()
    //                  .max(Map.Entry.comparingByValue())
    //                  .get()
    //                  .getKey();
    // }
    


    /** Classifies a tweet according to the KNN algorithm 
     * @param tweet the tweet to tag 
     * @param learningBase the cleaned tweets 
     * @param k the number of neighbors 
     * @return ??? 
     * mettre dans une map la classe associée au tweet (en utilisant la base d'apprentissage ??)
     * la base est étiquetée ET nettoyée au préalable !!
     */
    public String classifyTweet(String x, Map<String, String> learningBase, int k) {
        Map<String, String> proches_voisins = new HashMap<>();

        int count = 0; 
        for(String tweetNeighbor : learningBase.keySet()) { 
            if(count <= k) {
                proches_voisins.put(tweetNeighbor, learningBase.get(tweetNeighbor)); // ajoute dans la map le tweet voisin (clé) et récupère sa valeur associée selon la clé pour l'ajouter
                count++;                
            }
        }

        for(String tweetNeighbor : learningBase.keySet()) {
            // on parcourt le reste des tweets de la base à partir de k+1 (on prends donc ceux qui n'ont pas encore été ajoutés)
            if(!proches_voisins.containsKey(tweetNeighbor)) {
                int distToNeighbor = distance(tweetNeighbor, x);
                List<Integer> distXtoProchesVoisins = allDistances(x, proches_voisins);

                if(this.isLessThanAny(distToNeighbor, distXtoProchesVoisins)) {
                    String furthestTweet = this.getFurthestTweet(x, proches_voisins);
                    proches_voisins.remove(furthestTweet);
                    proches_voisins.put(tweetNeighbor, learningBase.get(tweetNeighbor));                    
                }
            }
        }
        vote(proches_voisins);
    }


}
