package big.core;

import core.Individual;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public abstract class BigPopulation<T extends BigIndividual> {
    protected ArrayList<T> individuals;
    protected double crossoverProbability;
    protected double mutationProbability;
    protected int populationSize;
    protected int numberOfGenes;
    protected boolean maximize;
    protected boolean elitism;

    public BigPopulation() {
        this.individuals = null;
        this.populationSize = 0;
        this.numberOfGenes = 0;
        this.elitism = false;
        this.crossoverProbability = 0d;
        this.mutationProbability = 0d;
    }

    public BigPopulation(boolean maximize, boolean elitism, int populationSize, int numberOfGenes, double crossoverProbability, double mutationProbability) {
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

    public ArrayList<BigDecimal> cumulativeFitness(){
        ArrayList<BigDecimal> npop = normalizeFitness();
        return cumulativeFitness(npop);
    }

    public ArrayList<BigDecimal> cumulativeFitness(ArrayList<BigDecimal> npop){
        ArrayList<BigDecimal> cpop = new ArrayList<>();
        BigDecimal c = BigDecimal.ZERO;
        for (BigDecimal n : npop) {
            c = c.add(n);
            cpop.add(c);
        }

        return cpop;
    }

    public T getFittest() {
        sortByFitness();
        return individuals.get(0);
    }

    public ArrayList<T> selection(ArrayList<BigDecimal> cpop){
        ArrayList<T> selection = new ArrayList<>();
        Random rnd = new Random();
        BigDecimal u, tmp;
        for (int i = 0; i < populationSize; i++) {
            u = new BigDecimal(rnd.nextDouble());

            for (int j = 0; j < populationSize; j++) {
                tmp = (j == 0 ? BigDecimal.ZERO : cpop.get(j - 1));
                if( (tmp.compareTo(u) == -1 || tmp.compareTo(u) == 0) && u.compareTo(cpop.get(j)) == -1) {
                    selection.add(individuals.get(j));
                    break;
                }

            }
        }

        return selection;
    }

    public void computeFitness(){
        individuals.forEach(T::fitness);
    }

    public ArrayList<T> sortByFitness(ArrayList<T> individuals){
        individuals.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return (maximize ? -1 : 1) * o1.compareTo(o2);
            }
        });

        return individuals;
    }

    public ArrayList<BigDecimal> normalizeFitness(){
        BigInteger fitSum = fitnessSum();
        ArrayList<BigDecimal> npop = new ArrayList<>();

        for(T individual : individuals){
            npop.add(individual.normalizeFitness(fitSum));
        }

        return npop;
    }

    public BigInteger fitnessSum(){
        BigInteger fitSum = BigInteger.ZERO;
        for(T individual : individuals){
            fitSum = fitSum.add(individual.getFitness());
        }

        return fitSum;
    }

    public abstract ArrayList<T> crossover(ArrayList<T> selectedIndividuals);
}
