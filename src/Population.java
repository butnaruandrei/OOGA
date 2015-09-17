import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Butnaru Andrei-Madalin.
 */
public abstract class Population<T> {
    protected ArrayList<T> individuals;
    protected double crossoverProbability;
    protected double mutationProbability;
    protected int populationSize;
    protected int genesLength;

    public Population() {
        this.individuals = null;
        this.populationSize = 0;
        this.genesLength = 0;
        this.crossoverProbability = 0d;
        this.mutationProbability = 0d;
    }

    public Population(int populationSize, int genesLength, double crossoverProbability, double mutationProbability) {
        this.individuals = null;
        this.populationSize = populationSize;
        this.genesLength = genesLength;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
    }

    public abstract ArrayList<T> selection();
}
