package algo;

import java.util.List;

/** A class that aims to represent the probability by presence */
public class BayesClassifierPresence extends BayesClassifierNaive {

    /**
     * {@inheritDoc}
     */
    public BayesClassifierPresence(String csvFile) {
        super(csvFile);
    }


    // public boolean isPresent(String word, String tweet) {
    //     String[] words = this.getWordsFromSentence(tweet);
    //     for(int i = 0; i < words.length; i++) {
    //         if(words[i].equals(word)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }


    /**
     * {@inheritDoc}
     */
    @Override
    public double probabilityByPolarity(String tweet, Polarity polarity) {
        List<String> words = this.extractWords(tweet);
        double occProbaForEachWord = 1;

        for(String w : words) {
            occProbaForEachWord *= this.occurrenceProbability(w, polarity);
        }
        return (occProbaForEachWord * this.totalTweetsByPolarity(polarity));
    }

    
}
