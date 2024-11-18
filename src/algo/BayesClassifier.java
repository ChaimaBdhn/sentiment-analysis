package algo;


public class BayesClassifier extends ClassifierAlgorithm implements Polarity {

    /**
     * @param csvFile
     */
    public BayesClassifier(String csvFile) {
        super(csvFile);
    }



    /** Probabilité pour un tweet donné de correspondre à la polarité en paramètre
     * @param tweet
     * @param polarity
     * @return
     */
    // public Integer probabilityTweet(String tweet, Polarity polarity) {

    // }

    
    /** Probabilité d'occurrence d'un mot de classe c 
     * @return
     */
    public int occurrenceProbability(String word, Polarity polarity) {
        return 0;

    }

    /** Counts words in a sentence 
     * @param sentence the string 
     * @return the number of words contained in a sentence
     */
    public int countWords(String sentence) {
        String[] words = sentence.trim().split("\\s");
        return words.length;
    }


    /** Retrieves total number of words in a learning base
     * @return the total number of words 
     */
    public int getTotalWordsOfLearningBase() {
        int count = 0;
        for(String tweet : this.getLearningBase().keySet()) {
            count += this.countWords(tweet);
        }
        return count;
    }


    /**
     * nombre total de mots des tweets de la classe c,
     * @return
     */
    public int getTotalWordsByPolarity(Polarity polarity) {
        for(String tweet : this.getLearningBase().keySet()) {
            // if()
        }

        return 0;

    }

    /**  le nombre d’occurrence du mot m dans les tweets de la classe c.
     * 
     * @return
     */
    public int getNbOccurrenceWordByPolarity(String word, Polarity polarity) {
        return 0;
    }





}
