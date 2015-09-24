package binary;

import core.Individual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by butna on 9/17/2015.
 */
public class BinaryIndividual extends Individual<String> {
    private int geneLength;

    public BinaryIndividual(ArrayList<String> genes, double crossoverProbability, double mutationProbability) {
        super(genes, crossoverProbability, mutationProbability);
        this.geneLength = genes.get(0).length();
    }

    /**
     * Random initialize the genes
     * @param numberOfGenes the number of how many genes are in each chromosome
     * @param geneLength the length size of a gene
     * @param crossoverProbability the crossover probability of this individual
     * @param mutationProbability the mutation probability of this individual
     */
    public BinaryIndividual(int numberOfGenes, int geneLength, double crossoverProbability, double mutationProbability) {
        super(numberOfGenes, crossoverProbability, mutationProbability);
        this.geneLength = geneLength;

        this.chromosome = new ArrayList<>();
        for(int i = 0; i < this.numberOfGenes; i++)
            this.chromosome.add(generateRandomGene());
    }

    public double fitness() {
        String g = chromosome.get(0);
        int x = Integer.parseInt(g, 2);
        this.fitness = Math.sin(Math.PI * x / 256d);

        return this.fitness;
    }

    public ArrayList<BinaryIndividual> crossover(BinaryIndividual other, int l){
        ArrayList<BinaryIndividual> offspings = new ArrayList<>();
        String p1, p2;
        StringBuilder tmp1 = new StringBuilder();
        StringBuilder tmp2 = new StringBuilder();
        int positions[] = generateRandomPartitions(l, geneLength);

        ArrayList<String> off1 = new ArrayList<>();
        ArrayList<String> off2 = new ArrayList<>();

        for (int i = 0; i <  numberOfGenes; i++) {
            p1 = chromosome.get(i);
            p2 = other.chromosome.get(i);

            for (int j = 0; j <= l; j++) {
                if( j % 2 == 0) {
                    tmp1.append(p1.substring(j == 0 ? 0 : positions[j], positions[j + 1]));
                    tmp2.append(p2.substring(j == 0 ? 0 : positions[j], positions[j + 1]));
                } else {
                    tmp1.append(p2.substring(positions[j], positions[j + 1]));
                    tmp2.append(p1.substring(positions[j], positions[j + 1]));
                }
            }

            off1.add(tmp1.toString());
            off2.add(tmp2.toString());
        }

        offspings.add(new BinaryIndividual(off1, crossoverProbability, mutationProbability));
        offspings.add(new BinaryIndividual(off2, crossoverProbability, mutationProbability));

        return offspings;
    }

    public void mutation() {
        StringBuilder tempGene;
        String gene;
        Random rand = new Random();
        for (int i = 0; i < numberOfGenes; i++) {
            gene = chromosome.get(i);
            tempGene = new StringBuilder(gene);

            for (int j = 0; j < geneLength; j++) {
                if(rand.nextDouble() <= mutationProbability){
                    tempGene.setCharAt(j, tempGene.charAt(j) == '1' ? '0' : '1');
                }
            }

            chromosome.set(i, tempGene.toString());
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
}
