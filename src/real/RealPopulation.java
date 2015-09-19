package real;

import core.Population;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class RealPopulation extends Population<RealIndividual> {
    public RealPopulation(int populationSize, int genesLength, double crossoverProbability, double mutationProbability){
        super(populationSize, genesLength, crossoverProbability, mutationProbability);

        this.individuals = new ArrayList<>();
        for (int i = 0; i < this.populationSize; i++) {
            this.individuals.add(new RealIndividual(genesLength, crossoverProbability, mutationProbability));
        }
    }
}
