package algo;

public class BayesClassifierMain {

    public static void main(String[] args) {
        
        String inputFile = "data/testdata.bayes.csv";

        BayesClassifier bc = new BayesClassifier(inputFile);

        // Tests countWords
        // System.out.println(bc.countWords("Bonjour le monde!")); // 3
        // System.out.println(bc.countWords("1234 Bonjour !"));   // 1 (seulement "Bonjour")
        // System.out.println(bc.countWords("   Java est génial.  ")); // 3
        // System.out.println(bc.countWords("**!!!**"));          // 0

        // String sentence = "Numbers like 123 or 4567 are ignored, but iPhone12 is counted.";
        // System.out.println(bc.countWords(sentence));
        

        // Test occurenceInSentence
        // String str = "salut la compagnie, je suis en bonne compagnie";
        // String str2 = "non non non et non!";
        // System.out.println(str + " : " + bc.occurrenceInSentence("compagnie", str));
        // System.out.println(str2 + " : " + bc.occurrenceInSentence("non", str2));

        // Test learning base
        bc.displayLearningBase();
        // System.out.println(bc.totalWordsInLearningBase());

        // Test total words by polarity
        // System.out.println(bc.totalWordsByPolarity(Polarity.NEGATIVE));

        // System.out.println(bc.nbOccurrenceWordByPolarity("kindle2", Polarity.POSITIVE));
        // System.out.println(bc.nbOccurrenceWordByPolarity("zero", Polarity.POSITIVE));

        // Test totalTweetsByPolarity
        // System.out.println(bc.totalTweetsByPolarity(Polarity.NEGATIVE));

    }   
    
}
