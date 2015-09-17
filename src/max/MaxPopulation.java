package max;

import core.Population;

import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public class MaxPopulation extends Population<MaxIndividual> {
    public MaxPopulation(int populationSize, int genesLength, double crossoverProbability, double mutationProbability){
        super(populationSize, genesLength, crossoverProbability, mutationProbability);

        this.individuals = new ArrayList<>();
        for (int i = 0; i < this.populationSize; i++) {
            this.individuals.add(new MaxIndividual(genesLength, crossoverProbability, mutationProbability));
        }
    }

    public ArrayList<MaxIndividual> selection() {
        return null;
    }
}
