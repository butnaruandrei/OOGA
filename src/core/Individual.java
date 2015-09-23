package core;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin
 */
public abstract class Individual<T> {
    protected ArrayList<T> chromosome;
    protected int numberOfGenes;
    protected double crossoverProbability;
    protected double mutationProbability;
    protected double fitness;

    public Individual() {
        this.chromosome = null;
        this.numberOfGenes = 0;
        this.crossoverProbability = 0d;
        this.mutationProbability = 0d;
    }

    public Individual(ArrayList<T> chromosome, double crossoverProbability, double mutationProbability) {
        this.chromosome = chromosome;
        this.numberOfGenes = chromosome.size();
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public Individual(int numberOfGenes, double crossoverProbability, double mutationProbability) {
        this.chromosome = null;
        this.numberOfGenes = numberOfGenes;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public double getFitness(){
        return fitness;
    }

    public double normalizeFitness(double fitSum) {
        return fitness / fitSum;
    }


    public int compareTo(Individual<T> other){
        return Double.compare(getFitness(), other.getFitness());
    }

    public abstract double fitness();
    public abstract void mutation();
}
