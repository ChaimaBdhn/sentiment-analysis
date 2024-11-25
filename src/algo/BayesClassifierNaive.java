package algo;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Naive classifier based on Bayes algorithm */
public abstract class BayesClassifierNaive extends ClassifierAlgorithm {

    /** 
     * {@inheritDoc}
     */
    public BayesClassifierNaive(String csvFile) {
        super(csvFile);
    }


    /** Determines the most likely polarity for a given tweet
     * @param tweet the tweet to classify
     * @return the most likely polarity for a given tweet (Positive, Negative or Neutral)
     */
    public Polarity execute(String tweet) {
        Polarity bestPolarity = null;
        double maxProbability = -1;

        // Routes through the different polarities
        for (Polarity polarity : Polarity.values()) {
            // Calculates the probability for an actual polarity
            double probability = this.probabilityByPolarity(tweet, polarity);

            // Updates polarity if the probability is higher
            if (probability > maxProbability) {
                maxProbability = probability;
                bestPolarity = polarity;
            }
        }
        return bestPolarity;
    }
 

    /** Probabilité pour un tweet donné de correspondre à la polarité en paramètre
     * @param tweet 
     * @param polarity 
     * @return 
     */ 
    public abstract double probabilityByPolarity(String tweet, Polarity polarity);


    
    /** Probabilité d'occurrence d'un mot dans un tweet de classe c 
     * @return probability of a given word occurence in a tweet of a specific polarity
     */
    public double occurrenceProbability(String word, Polarity polarity) {
        return (this.nbOccurrenceWordByPolarity(word, polarity) +1) / 
               (double)(this.totalWordsByPolarity(polarity) + this.totalWordsInLearningBase());
    }


    /**
     * @return
     */
    public int totalTweetsByPolarity(Polarity polarity) {
        int nbTweetOfPolarity = 0;
        for(String tweet : this.getLearningBase().keySet()) {
            if(this.getLearningBase().get(tweet).equals(polarity.getValue())) {
                nbTweetOfPolarity++;
            }
        }
        return nbTweetOfPolarity;
    }


    /** Counts words in a sentence 
     * @param sentence the string 
     * @return the number of words contained in a sentence
     */
    public int countWords(String sentence) {
        // Retrieving each words from sentence 
        List<String> words = this.extractWords(sentence);
        // Word is composed of one or several letters
        Pattern wordPattern = Pattern.compile("[.,!?]*\\p{L}+[\\p{L}\\d]*(?:'[\\p{L}\\d]+)?[.,!?]*");
        int count = 0;

        for(String word : words) {
            if(wordPattern.matcher(word).matches()) {
                count++;
            }
        }
        return count;
    }
     


    /** Retrieves total number of words in a learning base
     * @return the total number of words 
     */
    public int totalWordsInLearningBase() {
        int count = 0;
        for(String tweet : this.getLearningBase().keySet()) {
            count += this.countWords(tweet);
        }
        return count;
    }


    /**
     * nombre total de mots des tweets de la classe polarity
     * @return
     */
    public int totalWordsByPolarity(Polarity polarity) {
        int nbWords = 0;
        for(String tweet : this.getLearningBase().keySet()) {
            if(this.getLearningBase().get(tweet).equals(polarity.getValue())) {
                nbWords += this.countWords(tweet);
            }
        }
        System.out.println("Total words of polarity : " + polarity + " is " + nbWords);
        return nbWords;
    }


    /** Gets the number of occurrence of a given word in a sentence
     * @param word the word we search the number of occurrence for 
     * @param sentence the sentence 
     * @return the number of occurrence of a word in a sentence
     */
    public int occurrenceInSentence(String word, String sentence) {
        int cpt = 0;
        String[] words = sentence.trim().toLowerCase().split("[\\s,!.?]+");

        for(String w : words) {
            if(w.equals(word.toLowerCase())) {
                cpt++;
            }
        }
        return cpt;
    }


    /**  le nombre d’occurrence du mot m dans les tweets de la classe c.
     * 
     * @return
     */
    public int nbOccurrenceWordByPolarity(String word, Polarity polarity) {
        int occurrence = 0;
        for(String tweet : this.getLearningBase().keySet()) {
            if(this.getLearningBase().get(tweet).equals(polarity.getValue())) {
                occurrence += this.occurrenceInSentence(word, tweet);
            }
        }
        return occurrence;
    }

    
    /** Extracting each word from a sentence
     * @param sentence the string we extract from 
     * @return a list containing each word
     */
    public List<String> extractWords(String sentence) {
        List<String> words = new ArrayList<>();
        
        // Recognizing alphanumeric words 
        Pattern pattern = Pattern.compile("\\b[A-Za-z]+[A-Za-z0-9]*\\b");
        Matcher matcher = pattern.matcher(sentence);
        
        while (matcher.find()) {
            words.add(matcher.group()); // Ajouter chaque mot trouvé à la liste
        }
        return words;
    }


}
