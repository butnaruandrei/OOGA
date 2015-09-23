package real;

import core.GeneticAlgorithm;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class RealGeneticAlgorithm extends GeneticAlgorithm<RealPopulation, RealIndividual> {
    protected double low;
    protected double high;

    /**
     *
     * @param maximize
     * @param elitism
     * @param iterations
     * @param populationSize
     * @param numberOfGenes
     * @param crossoverProbability
     * @param mutationProbability
     */
    public RealGeneticAlgorithm(boolean maximize, boolean elitism, int iterations, int populationSize, int numberOfGenes, double low, double high, double crossoverProbability, double mutationProbability){
        super(maximize, elitism, iterations, populationSize, crossoverProbability, mutationProbability);

        this.low = low;
        this.high = high;
        this.population = new RealPopulation(maximize, elitism, populationSize, numberOfGenes, low, high, crossoverProbability, mutationProbability);
    }
}
