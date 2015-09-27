package rsa;

import big.core.BigGeneticAlgorithm;
import core.GeneticAlgorithm;
import utils.RSA;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class RSABinaryGeneticAlgorithm extends BigGeneticAlgorithm<RSABinaryPopulation, RSABinaryIndividual> {
    public RSABinaryGeneticAlgorithm(String crossoverType, boolean maximize, boolean elitism, int iterations, int populationSize, int numberOfGenes, int geneLength, double crossoverProbability, double mutationProbability, RSA rsa){
        super(maximize, elitism, iterations, populationSize, crossoverProbability, mutationProbability);

        this.population = new RSABinaryPopulation(crossoverType, maximize, elitism, populationSize, numberOfGenes, geneLength, crossoverProbability, mutationProbability, rsa);
    }

    public void runGeneration(){
        ArrayList<RSABinaryIndividual> selectedIndividuals = population.selection();

        ArrayList<RSABinaryIndividual> newIndividuals = population.crossover(selectedIndividuals);
        newIndividuals.forEach(RSABinaryIndividual::mutation);

        newIndividuals.forEach(RSABinaryIndividual::makePrime);

        newIndividuals.forEach(RSABinaryIndividual::fitness);
        if(elitism){
            population.mergeIndividualsWith(newIndividuals);
        } else {
            population.setIndividuals(newIndividuals);
            population.sortByFitness();
        }
    }
}
