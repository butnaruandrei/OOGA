package binary;

import core.GeneticAlgorithm;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class BinaryGeneticAlgorithm extends GeneticAlgorithm<BinaryPopulation, BinaryIndividual> {
    public BinaryGeneticAlgorithm(String crossoverType, boolean maximize, boolean elitism, int iterations, int populationSize, int numberOfGenes, int geneLength, double crossoverProbability, double mutationProbability){
        super(maximize, elitism, iterations, populationSize, crossoverProbability, mutationProbability);

        this.population = new BinaryPopulation(crossoverType, maximize, elitism, populationSize, numberOfGenes, geneLength, crossoverProbability, mutationProbability);
    }
}
