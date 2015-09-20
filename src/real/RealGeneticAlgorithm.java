package real;

import core.GeneticAlgorithm;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class RealGeneticAlgorithm extends GeneticAlgorithm<RealPopulation> {
    public RealGeneticAlgorithm(boolean maximize, int iterations, int populationSize, int numberOfGenes, double crossoverProbability, double mutationProbability){
        super(maximize, iterations, populationSize, crossoverProbability, mutationProbability);

        this.population = new RealPopulation(maximize, populationSize, numberOfGenes, crossoverProbability, mutationProbability);
    }

    public void runGeneration(){
        population.computeFitness();
        // population.sortByFitness();

        ArrayList<RealIndividual> selectedIndividuals = population.selection();

       // ArrayList<RealIndividual> newIndividuals = population.crossover(selectedIndividuals);

//        newIndividuals.forEach(RealIndividual::mutation);
//
//        if(elitism){
//            population.mergeIndividualsWith(newIndividuals);
//        } else {
//            population.setIndividuals(newIndividuals);
//        }
    }
}
