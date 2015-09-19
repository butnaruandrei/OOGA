package core;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public abstract class Population<T> {
    protected ArrayList<T> individuals;
    protected double crossoverProbability;
    protected double mutationProbability;
    protected int populationSize;
    protected int numberOfGenes;

    public Population() {
        this.individuals = null;
        this.populationSize = 0;
        this.numberOfGenes = 0;
        this.crossoverProbability = 0d;
        this.mutationProbability = 0d;
    }

    public Population(int populationSize, int numberOfGenes, double crossoverProbability, double mutationProbability) {
        this.individuals = null;
        this.populationSize = populationSize;
        this.numberOfGenes = numberOfGenes;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public int size(){
        return individuals.size();
    }

    public void setIndividuals(ArrayList<T> individuals){
        this.individuals = individuals;
    }


    public void mergeIndividualsWith(ArrayList<T> otherIndividuals){

    }

    public ArrayList<T> crossover(ArrayList<T> individuals){
        return null;
    }

    public void sortByFitness(){

    }

    /**
     * Default selection method ( Roulette Wheel Selection )
     * @return
     */
    public ArrayList<T> selection() {
        return null;
    }

    public abstract void computeFitness();
}
