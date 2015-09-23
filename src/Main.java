import binary.BinaryGeneticAlgorithm;
import real.RealGeneticAlgorithm;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class Main {
    public static void main(String[] args) {
        RealGA();
    }

    public static void BinaryGA(){
        int geneLength = 8;

        BinaryGeneticAlgorithm ga = new BinaryGeneticAlgorithm("singlePointCrossover", true, true, 100, 10, 1, geneLength, 0.8, 1d / geneLength);
        ga.run();
        System.out.println(ga.getFittest());
    }

    public static void RealGA(){
        RealGeneticAlgorithm ga = new RealGeneticAlgorithm(true, true, 100, 10, 1, 0, 256, 0.75, 0.01);
        ga.run();
        System.out.println(ga.getFittest());
    }
}
