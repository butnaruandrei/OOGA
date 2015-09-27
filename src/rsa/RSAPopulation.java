package rsa;

import big.core.BigPopulation;
import core.Population;
import utils.RSA;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class RSAPopulation extends BigPopulation<RSAIndividual> {
    protected BigInteger low;
    protected BigInteger high;

    private Random rand = new Random();
    private Random crossover_random = new Random();
    private Random selection_random = new Random();

    public RSAPopulation(boolean maximize, boolean elitism, int populationSize, int numberOfGenes, BigInteger low, BigInteger high, double crossoverProbability, double mutationProbability, RSA rsa){
        super(maximize, elitism, populationSize, numberOfGenes, crossoverProbability, mutationProbability);

        this.low = low;
        this.high = high;
        this.individuals = new ArrayList<>();
        for (int i = 0; i < this.populationSize; i++) {
            this.individuals.add(new RSAIndividual(numberOfGenes, low, high, crossoverProbability, mutationProbability, rsa));
        }
    }

    public ArrayList<RSAIndividual> crossover(ArrayList<RSAIndividual> individuals){
        int j;
        RSAIndividual ind1, ind2;
        ArrayList<RSAIndividual> offsprings = new ArrayList<>();

        for (int i = 0; i < populationSize / 2; i++) {
            ind1 = individuals.get(i);
            do { j = rand.nextInt(populationSize); } while (j == i);
            ind2 = individuals.get(j);

            if(crossover_random.nextDouble() <= crossoverProbability) {
                // crossover the parents to generate the offsrpings
                offsprings.addAll(ind1.crossover(ind2));
            } else {
                // keep the parents
                offsprings.add(ind1);
                offsprings.add(ind2);
            }
        }

        return offsprings;
    }

    public void mergeIndividualsWith(ArrayList<RSAIndividual> otherIndividuals){
        computeFitness();
        individuals.addAll(otherIndividuals);
        sortByFitness();
        individuals = new ArrayList<>(individuals.subList(0, populationSize));
    }
}
