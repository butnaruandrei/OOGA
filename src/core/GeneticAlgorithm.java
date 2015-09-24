package core;

import com.sun.org.apache.bcel.internal.generic.POP;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public abstract class GeneticAlgorithm<T extends Population, K extends Individual> {
    protected T population;
    protected int populationSize;
    protected int iterations;
    protected double crossoverProbability;
    protected double mutationProbability;
    protected boolean elitism;
    protected boolean maximize;

    public GeneticAlgorithm(){
        this.iterations = 0;
        this.population = null;
        this.elitism = false;
        this.crossoverProbability = 0;
        this.mutationProbability = 0;
    }

    public GeneticAlgorithm(boolean maximize, boolean elitism, int iterations, int populationSize, double crossoverProbability, double mutationProbability){
        this.iterations = iterations;
        this.population = null;
        this.maximize = maximize;
        this.elitism = elitism;
        this.populationSize = populationSize;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public GeneticAlgorithm(boolean maximize, boolean elitism, int iterations, T population, double crossoverProbability, double mutationProbability){
        this.iterations = iterations;
        this.population = population;
        this.maximize = maximize;
        this.elitism = elitism;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public void run(){
//        for (int i = 0; i < iterations; i++) {
//            runGeneration();
//        }
        while(true){
            runGeneration();
        }
    }

    public void runGeneration(){
        population.computeFitness();
        population.sortByFitness();
        ArrayList<K> selectedIndividuals = population.selection();

        ArrayList<K> newIndividuals = population.crossover(selectedIndividuals);
        newIndividuals.forEach(K::mutation);

        if(elitism){
            population.mergeIndividualsWith(newIndividuals);
        } else {
            population.setIndividuals(newIndividuals);
            population.sortByFitness();
        }
    }

    public K getFittest(){
        return (K)population.getFittest();
    }
}
