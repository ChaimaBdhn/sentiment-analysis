package algo;

import java.util.List;

public class BayesClassifierFrequence extends BayesClassifierNaive {

    /**
     * {@inheritDoc}
     */
    public BayesClassifierFrequence(String csvFile) {
        super(csvFile);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public double probabilityByPolarity(String tweet, Polarity polarity) {
        List<String> words = this.extractWords(tweet);
        double probability = 1;

        for(String w : words) {
            double wordProba = this.occurrenceProbability(w, polarity);

            int frequency = this.occurrenceInSentence(w, tweet);

            probability *= Math.pow(wordProba, frequency);
        }
        return probability * this.totalTweetsByPolarity(polarity);
    }
    


}
