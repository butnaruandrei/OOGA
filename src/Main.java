import binary.BinaryGeneticAlgorithm;
import real.RealGeneticAlgorithm;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class Main {
    public static void main(String[] args) {
        int geneLength = 8;

        BinaryGeneticAlgorithm ga = new BinaryGeneticAlgorithm(true, true, 1000, 10, 1, geneLength, 0.8, 1d / geneLength);
        ga.run();
        System.out.println(ga.getFittest());

    }
}
