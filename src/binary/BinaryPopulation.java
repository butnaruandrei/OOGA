package binary;

import core.Individual;
import core.Population;
import real.RealIndividual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class BinaryPopulation extends Population<BinaryIndividual> {
    protected String crossoverType;

    public BinaryPopulation(String crossoverType, boolean maximize, boolean elitism, int populationSize, int numberOfGenes, int geneLength, double crossoverProbability, double mutationProbability){
        super(maximize, elitism, populationSize, numberOfGenes, crossoverProbability, mutationProbability);

        this.crossoverType = crossoverType;
        this.individuals = new ArrayList<>();
        for (int i = 0; i < this.populationSize; i++) {
            this.individuals.add(new BinaryIndividual(numberOfGenes, geneLength, crossoverProbability, mutationProbability));
        }
    }


    public ArrayList<BinaryIndividual> crossover(ArrayList<BinaryIndividual> individuals){
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

    public ArrayList<BinaryIndividual> crossover(ArrayList<BinaryIndividual> individuals, int l){
        int j;
        Random rand = new Random();
        Random crand = new Random();
        BinaryIndividual ind1, ind2;
        ArrayList<BinaryIndividual> offsprings = new ArrayList<>();

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
