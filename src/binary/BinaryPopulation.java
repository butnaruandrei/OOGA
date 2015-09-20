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
    public BinaryPopulation(boolean maximize, int populationSize, int numberOfGenes, int geneLength, double crossoverProbability, double mutationProbability){
        super(maximize, populationSize, numberOfGenes, crossoverProbability, mutationProbability);

        this.individuals = new ArrayList<>();
        for (int i = 0; i < this.populationSize; i++) {
            this.individuals.add(new BinaryIndividual(numberOfGenes, geneLength, crossoverProbability, mutationProbability));
        }
    }

    public void computeFitness(){
        individuals.forEach(BinaryIndividual::fitness);
    }

    public void sortByFitness(){
        individuals.sort(new Comparator<BinaryIndividual>() {
            @Override
            public int compare(BinaryIndividual o1, BinaryIndividual o2) {
                return (maximize ? -1 : 1) * Double.compare(o1.getFitness(), o2.getFitness());
            }
        });
    }

    public ArrayList<Double> normalizeFitness(){
        double fitSum = fitnessSum();
        ArrayList<Double> npop = new ArrayList<>();

        for(BinaryIndividual individual : individuals){
            npop.add(individual.normalizeFitness(fitSum));
        }

        return npop;
    }

    public ArrayList<Double> cumulativeFitness(){
        ArrayList<Double> npop = normalizeFitness();
        return cumulativeFitness(npop);
    }

    public ArrayList<Double> cumulativeFitness(ArrayList<Double> npop){
        ArrayList<Double> cpop = new ArrayList<>();
        double c = 0;
        for (Double n : npop) {
            cpop.add(c += n);
        }

        return cpop;
    }

    public ArrayList<BinaryIndividual> selection(){
        return selection(cumulativeFitness());
    }

    public ArrayList<BinaryIndividual> selection(ArrayList<Double> cpop){
        ArrayList<BinaryIndividual> selection = new ArrayList<>();
        Random rnd = new Random();
        double u;
        for (int i = 0; i < populationSize; i++) {
            u = rnd.nextDouble();
            for (int j = 0; j < populationSize; j++) {
                if( (j == 0 ? 0d : cpop.get(j - 1)) <= u && u < cpop.get(j)) {
                    selection.add(individuals.get(j));
                    break;
                }

            }
        }

        return selection;
    }

    public double fitnessSum(){
        double fitSum = 0;
        for(BinaryIndividual individual : individuals){
            fitSum += individual.getFitness();
        }

        return fitSum;
    }


    public ArrayList<BinaryIndividual> crossover(String type, ArrayList<BinaryIndividual> individuals){
        switch(type){
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
        ArrayList<BinaryIndividual> offsprings = new ArrayList<>();

        for (int i = 0; i < populationSize / 2; i++) {
            BinaryIndividual ind1 = individuals.get(i);
            do { j = rand.nextInt(populationSize); } while( j == i);
            BinaryIndividual ind2 = individuals.get(j);

            if(crand.nextDouble() <= crossoverProbability)
                offsprings.addAll(ind1.crossover(ind2, l));
        }

        return offsprings;
    }

}