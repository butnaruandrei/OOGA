import real.RealIndividual;
import real.RealPopulation;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> genes = new ArrayList<>();
        genes.add(123);
        genes.add(45);

        RealIndividual mi = new RealIndividual(genes, 0.8, 0.01);

        RealPopulation mp = new RealPopulation(10,2,0.8,0.01);
    }
}
