package core;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin
 */
public abstract class Individual<T> {
    protected ArrayList<T> chromosome;
    protected int genesLength;
    protected double crossoverProbability;
    protected double mutationProbability;
    protected double fitness;

    public Individual() {
        this.chromosome = null;
        this.genesLength = 0;
        this.crossoverProbability = 0d;
        this.mutationProbability = 0d;
    }

    public Individual(ArrayList<T> chromosome, double crossoverProbability, double mutationProbability) {
        this.chromosome = chromosome;
        this.genesLength = chromosome.size();
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public Individual(int genesLength, double crossoverProbability, double mutationProbability) {
        this.chromosome = null;
        this.genesLength = genesLength;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public abstract double fitness();
    public abstract ArrayList<T> crossover();
    public abstract T mutation();
}
