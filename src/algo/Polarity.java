package algo;

// public interface Polarity {

//     public static final Integer NEGATIVE = 0;
//     public static final Integer NEUTRAL = 2;
//     public static final Integer POSITIVE = 4;
    
// }

/* Polarity : 0 for negative sentiment, 2 for neutral sentiment and 4 for positive sentiment */
public enum Polarity {
    NEGATIVE(0),
    NEUTRAL(2),
    POSITIVE(4);

    private final Integer value;

    Polarity(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    // Méthode statique pour obtenir l'énumération à partir d'une valeur
    public static Polarity fromValue(int value) {
        for (Polarity p : Polarity.values()) {
            if (p.getValue() == value) {
                return p;
            }
        }
        throw new IllegalArgumentException("Unknown value : " + value);
    }
}
