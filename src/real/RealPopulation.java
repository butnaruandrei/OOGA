package real;

import core.Population;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class RealPopulation extends Population<RealIndividual> {
    public RealPopulation(boolean maximize, int populationSize, int numberOfGenes, double crossoverProbability, double mutationProbability){
        super(maximize, populationSize, numberOfGenes, crossoverProbability, mutationProbability);

        this.individuals = new ArrayList<>();
        for (int i = 0; i < this.populationSize; i++) {
            this.individuals.add(new RealIndividual(numberOfGenes, crossoverProbability, mutationProbability));
        }
    }

    public void computeFitness(){
        individuals.forEach(RealIndividual::fitness);
    }
}
