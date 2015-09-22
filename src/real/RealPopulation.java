package real;

import core.Population;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class RealPopulation extends Population<RealIndividual> {
    protected double low;
    protected double high;

    private Random rand = new Random();
    private Random crossover_random = new Random();
    private Random selection_random = new Random();

    public RealPopulation(boolean maximize, boolean elitism, int populationSize, int numberOfGenes, double low, double high, double crossoverProbability, double mutationProbability){
        super(maximize, elitism, populationSize, numberOfGenes, crossoverProbability, mutationProbability);

        this.low = low;
        this.high = high;
        this.individuals = new ArrayList<>();
        for (int i = 0; i < this.populationSize; i++) {
            this.individuals.add(new RealIndividual(numberOfGenes, low, high, crossoverProbability, mutationProbability));
        }
    }

    public ArrayList<RealIndividual> crossover(ArrayList<RealIndividual> individuals){
        int j;
        RealIndividual ind1, ind2;
        ArrayList<RealIndividual> offsprings = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
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

    public void computeFitness(){
        individuals.forEach(RealIndividual::fitness);
    }

    public ArrayList<RealIndividual> sortByFitness(ArrayList<RealIndividual> individuals){
        individuals.sort(new Comparator<RealIndividual>() {
            @Override
            public int compare(RealIndividual o1, RealIndividual o2) {
                return (maximize ? -1 : 1) * o1.compareTo(o2);
            }
        });

        return individuals;
    }

    public ArrayList<Double> normalizeFitness(){
        double fitSum = fitnessSum();
        ArrayList<Double> npop = new ArrayList<>();

        for(RealIndividual individual : individuals){
            npop.add(individual.normalizeFitness(fitSum));
        }

        return npop;
    }

    public ArrayList<RealIndividual> selection(ArrayList<Double> cpop){
        ArrayList<RealIndividual> selection = new ArrayList<>();
        double u;
        for (int i = 0; i < populationSize; i++) {
            u = selection_random.nextDouble();
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
        for(RealIndividual individual : individuals){
            fitSum += individual.getFitness();
        }

        return fitSum;
    }
}
