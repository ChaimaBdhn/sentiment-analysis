package distance;

/* Levenshtein distance calculates the optimal number of operations (replacement, addition or deletion) */ 
public class LevenshteinDistance implements DistanceCalculation {
    
    @Override
    /**
     * {@inheritDoc}
     */
    public int distance(String tweet1, String tweet2) {
        int len1 = tweet1.length();
        int len2 = tweet2.length();
        
        //  2 dimensional distances table
        int[][] tab_distances = new int[len1 + 1][len2 + 1];
        
        // Initialization of table edges
        for (int i = 0; i <= len1; i++) {
            tab_distances[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            tab_distances[0][j] = j;
        }
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (tweet1.charAt(i - 1) == tweet2.charAt(j - 1)) {
                    tab_distances[i][j] = tab_distances[i - 1][j - 1];
                } else {
                    int insert = tab_distances[i][j - 1] + 1;
                    int delete = tab_distances[i - 1][j] + 1;
                    int replace = tab_distances[i - 1][j - 1] + 1;
                    tab_distances[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        
        // last contains the levenshtein distance 
        return tab_distances[len1][len2];
    }
}
