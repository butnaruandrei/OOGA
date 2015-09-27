package rsa;

import big.core.BigIndividual;
import core.Individual;
import utils.RSA;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by butna on 9/17/2015.
 */
public class RSABinaryIndividual extends BigIndividual<BigInteger> {
    private int geneLength;
    private RSA rsa;
    Random geneCrossover = new Random();

    public RSABinaryIndividual(ArrayList<BigInteger> genes, int geneLength, double crossoverProbability, double mutationProbability, RSA rsa) {
        super(genes, crossoverProbability, mutationProbability);

        this.geneLength = geneLength;
        this.rsa = rsa;
    }

    /**
     * Random initialize the genes
     * @param numberOfGenes the number of how many genes are in each chromosome
     * @param geneLength the length size of a gene
     * @param crossoverProbability the crossover probability of this individual
     * @param mutationProbability the mutation probability of this individual
     */
    public RSABinaryIndividual(int numberOfGenes, int geneLength, double crossoverProbability, double mutationProbability, RSA rsa) {
        super(numberOfGenes, crossoverProbability, mutationProbability);
        this.geneLength = geneLength;
        this.rsa = rsa;

        this.chromosome = new ArrayList<>();
        for(int i = 0; i < this.numberOfGenes; i++)
            this.chromosome.add(new BigInteger(generateRandomGene(), 2));
    }

    public BigInteger fitness() {
        BigInteger p = chromosome.get(0);
        BigInteger q = chromosome.get(1);
        BigInteger prod = p.multiply(q);

        this.fitness = prod.multiply(BigInteger.valueOf(-1)).add(rsa.n).abs();

        if(prod.equals(rsa.n)) {
            System.out.println("p=" + p + " q=" + q + " rsa.p=" + rsa.p + " rsa.q=" + rsa.q);
            System.out.println("REVOLUTIEEEEEEEEEEEEEEEE");
            System.exit(0);
        }

        return this.fitness;
    }

    public void showLog(){
        BigInteger p = chromosome.get(0);
        BigInteger q = chromosome.get(1);
        BigInteger prod = p.multiply(q);

        this.fitness = prod.multiply(BigInteger.valueOf(-1)).add(rsa.n).abs();
        System.out.println("fitness=" + fitness + " p=" + p + " q=" + q + " rsa.p=" + rsa.p + " rsa.q=" + rsa.q);
    }

    public ArrayList<RSABinaryIndividual> crossover(RSABinaryIndividual other, int l){
        ArrayList<RSABinaryIndividual> offspings = new ArrayList<>();
        String p1, p2;
        StringBuilder tmp1;
        StringBuilder tmp2;
        int positions[] = generateRandomPartitions(l, geneLength);

        ArrayList<BigInteger> off1 = new ArrayList<>();
        ArrayList<BigInteger> off2 = new ArrayList<>();

        for (int i = 0; i <  numberOfGenes; i++) {
            p1 = appendZeros(chromosome.get(i).toString(2));
            p2 = appendZeros(other.chromosome.get(i).toString(2));

            if(geneCrossover.nextDouble() <= 0.5) {
                tmp1 = new StringBuilder();
                tmp2 = new StringBuilder();

                for (int j = 0; j <= l; j++) {
                    if (j % 2 == 0) {
                        tmp1.append(p1.substring(j == 0 ? 0 : positions[j], positions[j + 1]));
                        tmp2.append(p2.substring(j == 0 ? 0 : positions[j], positions[j + 1]));
                    } else {
                        tmp1.append(p2.substring(positions[j], positions[j + 1]));
                        tmp2.append(p1.substring(positions[j], positions[j + 1]));
                    }
                }
            } else {
                tmp1 = new StringBuilder(p1);
                tmp2 = new StringBuilder(p2);
            }

            off1.add(new BigInteger(tmp1.toString(), 2));
            off2.add(new BigInteger(tmp2.toString(), 2));
        }

        offspings.add(new RSABinaryIndividual(off1, geneLength, crossoverProbability, mutationProbability, rsa));
        offspings.add(new RSABinaryIndividual(off2, geneLength, crossoverProbability, mutationProbability, rsa));

        return offspings;
    }

    public void mutation() {
        StringBuilder tempGene;
        String gene;
        Random rand = new Random();
        for (int i = 0; i < numberOfGenes; i++) {
            gene = appendZeros(chromosome.get(i).toString(2));
            tempGene = new StringBuilder(gene);

            for (int j = 0; j < geneLength; j++) {
                if(rand.nextDouble() <= mutationProbability){
                    tempGene.setCharAt(j, tempGene.charAt(j) == '1' ? '0' : '1');
                }
            }

            chromosome.set(i, new BigInteger(tempGene.toString(), 2));
        }
    }

    private String generateRandomGene(){
        Random rnd = new Random();
        StringBuilder gene = new StringBuilder();
        for (int i = 0; i < this.geneLength; i++) {
            gene.append(rnd.nextBoolean() ? "1" : "0");
        }

        return gene.toString();
    }

    private static int[] generateRandomPartitions(int l, int m) {
        Random rand = new Random();
        int positions[] = new int[l + 2];
        positions[0] = 0;
        int i = 1;
        boolean g;
        while (i < l + 1) {
            g = false;
            positions[i] = rand.nextInt(m - 1) + 1;
            for (int j = 0; j < i; j++)
                if (positions[i] == positions[j]) {
                    g = true;
                    break;
                }
            if (!g)
                i++;
        }
        positions[i] = m;
        Arrays.sort(positions);
        return positions;
    }

    public int getGeneLength(){
        return geneLength;
    }

    private String appendZeros(String str){
        int zeros = geneLength - str.length();
        String tmp = "";
        for (int i = 0; i < zeros; i++) {
            tmp += "0";
        }

        return tmp + str;
    }

    public void makePrime(){
        BigInteger gene;
        for (int i = 0; i < numberOfGenes; i++) {
            gene = chromosome.get(i);
            chromosome.set(i, gene.nextProbablePrime());
        }

        this.fitness = fitness();
    }
}