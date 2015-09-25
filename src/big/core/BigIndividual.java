package big.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin
 */
public abstract class BigIndividual<T> {
    protected ArrayList<T> chromosome;
    protected int numberOfGenes;
    protected double crossoverProbability;
    protected double mutationProbability;
    protected BigInteger fitness;

    public BigIndividual() {
        this.chromosome = null;
        this.numberOfGenes = 0;
        this.crossoverProbability = 0d;
        this.mutationProbability = 0d;
    }

    public BigIndividual(ArrayList<T> chromosome, double crossoverProbability, double mutationProbability) {
        this.chromosome = chromosome;
        this.numberOfGenes = chromosome.size();
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public BigIndividual(int numberOfGenes, double crossoverProbability, double mutationProbability) {
        this.chromosome = null;
        this.numberOfGenes = numberOfGenes;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public BigInteger getFitness(){
        return fitness;
    }

    public BigDecimal normalizeFitness(BigInteger fitSum) {
        return new BigDecimal(fitness).divide(new BigDecimal(fitSum), 16, RoundingMode.HALF_UP);
    }


    public int compareTo(BigIndividual<T> other){
        return getFitness().compareTo(other.getFitness());
    }


    public String toString(){
        StringBuilder str = new StringBuilder();
        chromosome.forEach(str::append);
        return str.toString();
    }

    public abstract void showLog();
    public abstract BigInteger fitness();
    public abstract void mutation();
}
