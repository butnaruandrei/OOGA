package rsa;

import big.core.BigPopulation;
import core.Population;
import utils.RSA;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class RSABinaryPopulation extends BigPopulation<RSABinaryIndividual> {
    protected String crossoverType;

    public RSABinaryPopulation(String crossoverType, boolean maximize, boolean elitism, int populationSize, int numberOfGenes, int geneLength, double crossoverProbability, double mutationProbability, RSA rsa){
        super(maximize, elitism, populationSize, numberOfGenes, crossoverProbability, mutationProbability);

        this.crossoverType = crossoverType;
        this.individuals = new ArrayList<>();
        for (int i = 0; i < this.populationSize; i++) {
            this.individuals.add(new RSABinaryIndividual(numberOfGenes, geneLength, crossoverProbability, mutationProbability, rsa));
        }
    }


    public ArrayList<RSABinaryIndividual> crossover(ArrayList<RSABinaryIndividual> individuals){
        switch(this.crossoverType){
            case "singlePointCrossover":
                return crossover(individuals, 1);
            case "doublePointCrossover":
                return crossover(individuals, 2);
            case "multiPointCrossover":
                Random rand = new Random();
                int l = rand.nextInt(individuals.get(0).getGeneLength() - 3) + 3;

                return crossover(individuals, l);
        }

        return null;
    }

    public ArrayList<RSABinaryIndividual> crossover(ArrayList<RSABinaryIndividual> individuals, int l){
        int j;
        Random rand = new Random();
        Random crand = new Random();
        RSABinaryIndividual ind1, ind2;
        ArrayList<RSABinaryIndividual> offsprings = new ArrayList<>();

        for (int i = 0; i < populationSize / 2; i++) {
            ind1 = individuals.get(i);
            do { j = rand.nextInt(populationSize); } while (j == i);
            ind2 = individuals.get(j);

            if(crand.nextDouble() <= crossoverProbability) {
                // crossover the parents to generate the offsrpings
                offsprings.addAll(ind1.crossover(ind2, l));
            } else {
                // keep the parents
                offsprings.add(ind1);
                offsprings.add(ind2);
            }
        }

        return offsprings;
    }
}
