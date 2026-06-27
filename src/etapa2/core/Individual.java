package etapa2.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Individual {
    private int[] chromosome;
    private int fitness;

    public Individual() {
        this.chromosome = new int[10];
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);
        for (int i = 0; i < 10; i++) {
            this.chromosome[i] = digits.get(i);
        }
    }

    public Individual(int[] chromosome) {
        this.chromosome = chromosome.clone();
    }


    public int[] getChromosome() {
        return chromosome;
    }
    public void setChromosome(int[] chromosome) {
        this.chromosome = chromosome.clone();
    }
    public int getFitness() {
        return fitness;
    }
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < chromosome.length; i++) {
            sb.append(chromosome[i]);
            if (i < chromosome.length - 1) sb.append(", ");
        }
        sb.append("] | Fitness: ").append(fitness);
        return sb.toString();
    }
}