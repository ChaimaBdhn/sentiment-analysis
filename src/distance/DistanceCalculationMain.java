package distance;

public class DistanceCalculationMain {
    public static void main(String[] args) {

        String str = "Bonjour le monde";
        String str2 = "Bonjour han";

        BasicDistance calc = new BasicDistance();

        int totalWords = calc.totalWords(str, str2);
        int identicalWords = calc.nbIdenticalWords(str, str2);

        System.out.println(totalWords);
        System.out.println(identicalWords);

    }
}
