package etapa1.core;

import etapa1.core.*;

public class GeneticAlgorithm {

  private SelectionStrategy selection;
  private CrossoverStrategy crossover;
  private ReinsertionStrategy reinsertion;
  private double mutationRate;
  private double crossoverRate;
  private double elitismRate = 0.0;
  private final int POP_SIZE = 100;
  private final int MAX_GENERATION = 50;

  public GeneticAlgorithm(
      SelectionStrategy selection,
      CrossoverStrategy crossover,
      ReinsertionStrategy reinsertion,
      double mutationRate,
      double crossoverRate,
      double elitismRate) {

    this.selection = selection;
    this.crossover = crossover;
    this.reinsertion = reinsertion;
    this.mutationRate = mutationRate;
    this.crossoverRate = crossoverRate;
    this.elitismRate = elitismRate;
  }

  public Individual run() {
    Population pop = new Population(this.POP_SIZE);

    for (Individual indiv : pop.getIndividuals()) {
      FitnessEvaluator.evaluate(indiv);
    }

    int generation = 0;
    Individual best = null;

    while (generation < this.MAX_GENERATION) {
      this.selection.updateProb(pop);
      best = pop.getFittest();

      // System.out.println("\nGeneration: " + generation + "\nBest Individual: " +
      // best.toString());

      if (best.getFitness() == 0) {
        // System.out.println("Solution Found!");
        // System.out.println(best.toString());
        return best;
      }

      int targetChildren = (int) (this.POP_SIZE * this.crossoverRate);
      Population childrenPop = new Population();

      while (childrenPop.getSize() < targetChildren) {
        Individual parent1 = this.selection.select(pop);
        Individual parent2 = this.selection.select(pop);

        Individual[] children = this.crossover.crossover(parent1, parent2);

        if (Math.random() < this.mutationRate)
          Mutation.mutate(children[0]);

        if (Math.random() < this.mutationRate)
          Mutation.mutate(children[1]);

        FitnessEvaluator.evaluate(children[0]);
        FitnessEvaluator.evaluate(children[1]);

        childrenPop.addIndividual(children[0]);
        if (childrenPop.getSize() < targetChildren) {
          childrenPop.addIndividual(children[1]);
        }
      }

      pop = this.reinsertion.reinsert(pop, childrenPop, this.elitismRate);
      generation++;
    }

    return best;
  }
}
