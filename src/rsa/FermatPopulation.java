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
public class FermatPopulation extends BigPopulation<FermatIndividual> {
    protected BigInteger low;
    protected BigInteger high;

    private Random rand = new Random();
    private Random crossover_random = new Random();

    public FermatPopulation(boolean maximize, boolean elitism, int populationSize, int numberOfGenes, BigInteger low, BigInteger high, double crossoverProbability, double mutationProbability, RSA rsa){
        super(maximize, elitism, populationSize, numberOfGenes, crossoverProbability, mutationProbability);

        this.low = low;
        this.high = high;
        this.individuals = new ArrayList<>();
        for (int i = 0; i < this.populationSize; i++) {
            this.individuals.add(new FermatIndividual(numberOfGenes, low, high, crossoverProbability, mutationProbability, rsa));
        }
    }

    public ArrayList<FermatIndividual> crossover(ArrayList<FermatIndividual> individuals){
        int j;
        FermatIndividual ind1, ind2;
        ArrayList<FermatIndividual> offsprings = new ArrayList<>();

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
}
