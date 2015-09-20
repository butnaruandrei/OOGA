package binary;

import core.GeneticAlgorithm;
import real.RealIndividual;
import real.RealPopulation;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class BinaryGeneticAlgorithm extends GeneticAlgorithm<BinaryPopulation> {
    public BinaryGeneticAlgorithm(boolean maximize, int iterations, int populationSize, int numberOfGenes, int geneLength, double crossoverProbability, double mutationProbability){
        super(maximize, iterations, populationSize, crossoverProbability, mutationProbability);

        this.population = new BinaryPopulation(maximize, populationSize, numberOfGenes, geneLength, crossoverProbability, mutationProbability);
    }

    public void runGeneration(){
        population.computeFitness();
        ArrayList<BinaryIndividual> selectedIndividuals = population.selection();

        ArrayList<BinaryIndividual> newIndividuals = population.crossover("singlePointCrossover", selectedIndividuals);

        newIndividuals.forEach(BinaryIndividual::mutation);
//
//        if(elitism){
//            population.mergeIndividualsWith(newIndividuals);
//        } else {
//            population.setIndividuals(newIndividuals);
//        }
    }
}
