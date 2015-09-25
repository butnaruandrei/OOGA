package rsa;

import big.core.BigGeneticAlgorithm;
import core.GeneticAlgorithm;
import utils.RSA;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class FermatGeneticAlgorithm extends BigGeneticAlgorithm<FermatPopulation, FermatIndividual> {
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
    public FermatGeneticAlgorithm(boolean maximize, boolean elitism, int iterations, int populationSize, int numberOfGenes, BigInteger low, BigInteger high, double crossoverProbability, double mutationProbability, RSA rsa){
        super(maximize, elitism, iterations, populationSize, crossoverProbability, mutationProbability);

        this.low = low;
        this.high = high;
        this.population = new FermatPopulation(maximize, elitism, populationSize, numberOfGenes, low, high, crossoverProbability, mutationProbability, rsa);
    }
}
