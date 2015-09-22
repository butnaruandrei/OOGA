package real;

import core.Individual;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by butna on 9/17/2015.
 */
public class RealIndividual extends Individual<Double> {
    protected double low;
    protected double high;
    protected double range;

    private Random init_random = new Random();
    private Random crossover_random = new Random();
    private Random bool_random = new Random();
    private Random double_random = new Random();
    double k = 8; // precizia operatorului de mutatie; k apartine [4, 20]
    double r = 0.01; // r apartile [10^-6, 10^-1]; cu cat % din range sa se faca mutatia
    double ri = r * range;

    public RealIndividual(ArrayList<Double> genes, double low, double high, double crossoverProbability, double mutationProbability) {
        super(genes, crossoverProbability, mutationProbability);

        this.low = low;
        this.high = high;
    }

    /**
     * Random initialize the genes
     * @param numberOfGenes the number of how many genes are in each chromosome
     * @param crossoverProbability the crossover probability of this individual
     * @param mutationProbability the mutation probability of this individual
     */
    public RealIndividual(int numberOfGenes, double low, double high, double crossoverProbability, double mutationProbability) {
        super(numberOfGenes, crossoverProbability, mutationProbability);

        this.low = low;
        this.high = high;
        this.range = high - low;

        this.chromosome = new ArrayList<>();
        for(int i = 0; i < this.numberOfGenes; i++)
            this.chromosome.add(range * init_random.nextDouble() + low);
    }

    public double fitness() {
        Double x = chromosome.get(0);
        this.fitness = Math.sin(Math.PI * x / 256d);
        return this.fitness;
    }

    public ArrayList<RealIndividual> crossover(RealIndividual other) {
        double d = 0.25, a_low = -d, a_high = d + 1, p1, p2, t, a;
        double a_range = a_high - a_low;
        ArrayList<RealIndividual> offspings = new ArrayList<>();

        ArrayList<Double> off1 = new ArrayList<>();
        ArrayList<Double> off2 = new ArrayList<>();

        for (int i = 0; i <  numberOfGenes; i++) {
            p1 = chromosome.get(i);
            p2 = other.chromosome.get(i);

            // create 2 offspings
            for (int j = 0; j < 2; j++) {
                a = a_range * crossover_random.nextDouble() - d;

                t = p1 * ( 1 - a ) + p2 * a;
                if(j == 0)
                    off1.add(t);
                else
                    off2.add(t);
            }
        }

        offspings.add(new RealIndividual(off1, this.low, this.high, this.crossoverProbability, this.mutationProbability));
        offspings.add(new RealIndividual(off2, this.low, this.high, this.crossoverProbability, this.mutationProbability));

        return offspings;
    }

    public void mutation() {
        Double gene;
        int s;
        double a,u;

        for (int i = 0; i < numberOfGenes; i++) {
            gene = chromosome.get(i);
            s = bool_random.nextBoolean() ? 1 : -1;
            u = double_random.nextDouble();
            a = Math.pow(2, -u * k);

            gene += s * ri * a;

            chromosome.set(i, gene);
        }
    }

    public int compareTo(RealIndividual other){
        return Double.compare(getFitness(), other.getFitness());
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        chromosome.forEach(str::append);
        return str.toString();
    }

}
