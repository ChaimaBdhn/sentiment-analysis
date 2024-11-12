package distance;

public interface DistanceCalculation {

    /** Calculates the distance between two tweets
     * @param tweet1 the first tweet
     * @param tweet2 the second tweet
     * @return the optimal distance between two tweets
     */
    public int distance(String tweet1, String tweet2);


}