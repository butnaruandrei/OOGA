package core;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public abstract class GeneticAlgorithm<T> {
    protected T population;
    protected int populationSize;
    protected int iterations;
    protected double crossoverProbability;
    protected double mutationProbability;
    protected boolean elitism;

    public GeneticAlgorithm(){
        this.iterations = 0;
        this.population = null;
        this.crossoverProbability = 0;
        this.mutationProbability = 0;
    }

    public GeneticAlgorithm(int iterations, int populationSize, double crossoverProbability, double mutationProbability){
        this.iterations = iterations;
        this.population = null;
        this.populationSize = populationSize;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public GeneticAlgorithm(int iterations, T population, double crossoverProbability, double mutationProbability){
        this.iterations = iterations;
        this.population = population;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public void run(){
        for (int i = 0; i < iterations; i++) {
            runGeneration();
        }
    }

    public abstract void runGeneration();
}
