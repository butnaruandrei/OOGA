import max.MaxIndividual;
import max.MaxPopulation;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> genes = new ArrayList<>();
        genes.add(123);
        genes.add(45);

        MaxIndividual mi = new MaxIndividual(genes, 0.8, 0.01);

        MaxPopulation mp = new MaxPopulation(10,2,0.8,0.01);
    }
}
