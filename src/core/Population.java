package core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public abstract class Population<T> {
    protected ArrayList<T> individuals;
    protected double crossoverProbability;
    protected double mutationProbability;
    protected int populationSize;
    protected int numberOfGenes;
    protected boolean maximize;
    protected boolean elitism;

    public Population() {
        this.individuals = null;
        this.populationSize = 0;
        this.numberOfGenes = 0;
        this.elitism = false;
        this.crossoverProbability = 0d;
        this.mutationProbability = 0d;
    }

    public Population(boolean maximize, boolean elitism, int populationSize, int numberOfGenes, double crossoverProbability, double mutationProbability) {
        this.individuals = null;
        this.maximize = maximize;
        this.elitism = elitism;
        this.populationSize = populationSize;
        this.numberOfGenes = numberOfGenes;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public int size(){
        return individuals.size();
    }

    public void setIndividuals(ArrayList<T> individuals) {
        this.individuals = individuals;
    }

    /**
     * Default selection method ( Roulette Wheel Selection )
     * @return
     */
    public ArrayList<T> selection(){
        return selection(cumulativeFitness());
    }

    public void sortByFitness(){
        sortByFitness(individuals);
    }

    public void mergeIndividualsWith(ArrayList<T> otherIndividuals){
        individuals.addAll(otherIndividuals);
        computeFitness();
        sortByFitness();
        individuals = new ArrayList<>(individuals.subList(0, populationSize));
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

    public T getFittest() {
        sortByFitness();
        return individuals.get(0);
    }

    public ArrayList<T> selection(ArrayList<Double> cpop){
        ArrayList<T> selection = new ArrayList<>();
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

    public abstract ArrayList<Double> normalizeFitness();
    public abstract ArrayList<T> sortByFitness(ArrayList<T> individuals);
    public abstract void computeFitness();
}
