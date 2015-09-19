package real;

import core.Individual;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by butna on 9/17/2015.
 */
public class RealIndividual extends Individual<Integer> {
    public RealIndividual(ArrayList<Integer> genes, double crossoverProbability, double mutationProbability) {
        super(genes, crossoverProbability, mutationProbability);
    }

    /**
     * Random initialize the genes
     * @param genesLength the number of how many genes are in each chromosome
     * @param crossoverProbability the crossover probability of this individual
     * @param mutationProbability the mutation probability of this individual
     */
    public RealIndividual(int genesLength, double crossoverProbability, double mutationProbability) {
        super(genesLength, crossoverProbability, mutationProbability);

        Random rnd = new Random();
        this.chromosome = new ArrayList<>();
        for(int i = 0; i < this.genesLength; i++)
            this.chromosome.add(rnd.nextInt());
    }

    public double fitness() {
        return 0d;
    }

    public ArrayList<Integer> crossover() {
        return null;
    }

    public Integer mutation() {
        return null;
    }
}
