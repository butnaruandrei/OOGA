package real;

import big.core.BigGeneticAlgorithm;
import core.GeneticAlgorithm;
import rsa.FermatIndividual;
import rsa.RSAIndividual;
import rsa.RSAPopulation;
import utils.RSA;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class RSAGeneticAlgorithm extends BigGeneticAlgorithm<RSAPopulation, RSAIndividual> {
    protected BigInteger low;
    protected BigInteger high;

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
    public RSAGeneticAlgorithm(boolean maximize, boolean elitism, int iterations, int populationSize, int numberOfGenes, BigInteger low, BigInteger high, double crossoverProbability, double mutationProbability, RSA rsa){
        super(maximize, elitism, iterations, populationSize, crossoverProbability, mutationProbability);

        this.low = low;
        this.high = high;
        this.population = new RSAPopulation(maximize, elitism, populationSize, numberOfGenes, low, high, crossoverProbability, mutationProbability, rsa);
    }

    public void runGeneration(){
        population.computeFitness();
        ArrayList<RSAIndividual> selectedIndividuals = population.selection();

        ArrayList<RSAIndividual> newIndividuals = population.crossover(selectedIndividuals);
        newIndividuals.forEach(RSAIndividual::mutation);
        newIndividuals.forEach(RSAIndividual::makePrime);

        newIndividuals.forEach(RSAIndividual::fitness);

        if(elitism){
            population.mergeIndividualsWith(newIndividuals);
        } else {
            population.setIndividuals(newIndividuals);
            population.sortByFitness();
        }
    }
}
