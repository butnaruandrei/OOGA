package rsa;

import core.Individual;
import utils.Factoring;
import utils.RSA;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by butna on 9/17/2015.
 */
public class FermatIndividual extends Individual<BigInteger> {
    protected BigInteger low;
    protected BigInteger high;
    protected BigInteger range;

    private Random init_random = new Random();
    private Random crossover_random = new Random();
    private Random bool_random = new Random();
    private Random double_random = new Random();
    double k = 4; // precizia operatorului de mutatie; k apartine [4, 20]

    BigInteger r = new BigInteger("100"); // r apartile [10^1, 10^6]; cu cat % din range sa se faca mutatia
    BigDecimal ri;

    RSA rsa;

    public FermatIndividual(ArrayList<BigInteger> genes, BigInteger low, BigInteger high, double crossoverProbability, double mutationProbability, RSA rsa) {
        super(genes, crossoverProbability, mutationProbability);

        this.low = low;
        this.high = high;
        this.range = high.subtract(low);
        this.rsa = rsa;

        ri = new BigDecimal(range.divide(r));
    }

    /**
     * Random initialize the genes
     * @param numberOfGenes the number of how many genes are in each chromosome
     * @param crossoverProbability the crossover probability of this individual
     * @param mutationProbability the mutation probability of this individual
     */
    public FermatIndividual(int numberOfGenes, BigInteger low, BigInteger high, double crossoverProbability, double mutationProbability, RSA rsa) {
        super(numberOfGenes, crossoverProbability, mutationProbability);

        this.low = low;
        this.high = high;
        this.range = high.subtract(low);
        this.rsa = rsa;

        ri = new BigDecimal(range.divide(r));

        BigDecimal decimalRange = new BigDecimal(range);
        BigDecimal rnd = null;

        this.chromosome = new ArrayList<>();
        for(int i = 0; i < this.numberOfGenes; i++) {
            rnd = new BigDecimal(init_random.nextDouble());
            this.chromosome.add(decimalRange.multiply(rnd).toBigInteger().add(low));
        }
    }

    public double fitness() {
        BigInteger a = chromosome.get(0);
        BigInteger b, sqrtb,xp, xq;
        double distance;

        BigInteger a2 = a.multiply(a);

        if(a2.compareTo(rsa.n) == -1) {
            this.fitness = -Double.MAX_VALUE;
            return this.fitness;
        }

        BigInteger sqrt = Factoring.sqrt(a2.subtract(rsa.n));
        BigInteger sqrt2 = sqrt.multiply(sqrt);
        distance = rsa.n.subtract(a2.subtract(sqrt2)).doubleValue();

        // DEMO FOR TESTING PURPOSE!!! ------->>>>>>>>>>>>>>>>>>>>
        b = a2.subtract(rsa.n);
        sqrtb = Factoring.sqrt(b);

        xp = a.subtract(sqrtb);
        xq = a.add(sqrtb);

        System.out.println("a = " + a + " distance = " + distance + " xp = " + xp + " xq = " + xq + "real p = " + rsa.p + "real q =" + rsa.q);

        if(xp.multiply(xq).compareTo(rsa.n) == 0){
            System.out.println("GATA!!!!!!!!!!!!!!!!");
            System.out.println("a = " + a + " distance = " + distance + " xp = " + xp + " xq = " + xq + "real p = " + rsa.p + "real q =" + rsa.q);
            System.exit(0);
        }

        // <<<<<<<<<<<<<<<-------------------

        this.fitness = distance;
        return distance;
    }

    public ArrayList<FermatIndividual> crossover(FermatIndividual other) {
        double d = 0.25, a_low = -d, a_high = d + 1, a;
        BigDecimal p1, p2, t;
        double a_range = a_high - a_low;
        ArrayList<FermatIndividual> offspings = new ArrayList<>();

        ArrayList<BigInteger> off1 = new ArrayList<>();
        ArrayList<BigInteger> off2 = new ArrayList<>();

        for (int i = 0; i <  numberOfGenes; i++) {
            p1 = new BigDecimal(chromosome.get(i));
            p2 = new BigDecimal(other.chromosome.get(i));

            // create 2 offspings
            for (int j = 0; j < 2; j++) {
                a = a_range * crossover_random.nextDouble() - d;

                t = p1.multiply(new BigDecimal(1 - a )).add(p2.multiply(new BigDecimal(a)));
                if(j == 0)
                    off1.add(t.toBigInteger());
                else
                    off2.add(t.toBigInteger());
            }
        }

        offspings.add(new FermatIndividual(off1, this.low, this.high, this.crossoverProbability, this.mutationProbability, rsa));
        offspings.add(new FermatIndividual(off2, this.low, this.high, this.crossoverProbability, this.mutationProbability, rsa));

        return offspings;
    }

    public void mutation() {
        BigInteger gene;
        int s;
        double a,u;

        for (int i = 0; i < numberOfGenes; i++) {
            gene = chromosome.get(i);
            s = bool_random.nextBoolean() ? 1 : -1;
            u = double_random.nextDouble();
            a = Math.pow(2, -u * k);

            if(s > 0)
                gene = gene.add(ri.multiply(new BigDecimal(a)).toBigInteger());
            else
                gene = gene.subtract(ri.multiply(new BigDecimal(a)).toBigInteger());

            if(gene.compareTo(low) == -1)
                gene = low;

            if(gene.compareTo(high) == 1)
                gene = high;

            chromosome.set(i, gene);
        }
    }

    public BigDecimal normalizeFitness(BigDecimal fitSum) {
        return new BigDecimal(fitness).divide(fitSum);
    }
}
