package big.core;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public abstract class BigGeneticAlgorithm<T extends BigPopulation, K extends BigIndividual> {
    protected T population;
    protected int populationSize;
    protected int iterations;
    protected double crossoverProbability;
    protected double mutationProbability;
    protected boolean elitism;
    protected boolean maximize;

    public BigGeneticAlgorithm(){
        this.iterations = 0;
        this.population = null;
        this.elitism = false;
        this.crossoverProbability = 0;
        this.mutationProbability = 0;
    }

    public BigGeneticAlgorithm(boolean maximize, boolean elitism, int iterations, int populationSize, double crossoverProbability, double mutationProbability){
        this.iterations = iterations;
        this.population = null;
        this.maximize = maximize;
        this.elitism = elitism;
        this.populationSize = populationSize;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public BigGeneticAlgorithm(boolean maximize, boolean elitism, int iterations, T population, double crossoverProbability, double mutationProbability){
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
        int i = 0;

        while(true){
            runGeneration();

            if(i % 100 == 0)
                getFittest().showLog();

            i++;
        }
    }

    public void runGeneration(){
        population.computeFitness();
        ArrayList<K> selectedIndividuals = population.selection();

        ArrayList<K> newIndividuals = population.crossover(selectedIndividuals);
        newIndividuals.forEach(K::mutation);

        if(elitism){
            newIndividuals.forEach(K::fitness);
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
