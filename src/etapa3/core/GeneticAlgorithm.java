package etapa3.core;

import etapa3.core.*;

public class GeneticAlgorithm {

  private SelectionStrategy selection;
  private CrossoverStrategy crossover;
  private ReinsertionStrategy reinsertion;
  private FitnessEvaluatorStrategy fitnessEvaluator;
  private double mutationRate;
  private double crossoverRate;
  private double elitismRate;
  private int pop_size;
  private int max_generation;
  private Problem problem;

  public GeneticAlgorithm(

      SelectionStrategy selection,
      CrossoverStrategy crossover,
      ReinsertionStrategy reinsertion,
      FitnessEvaluatorStrategy fitnessEvaluator,
      double mutationRate,
      double crossoverRate,
      double elitismRate,
      int pop_size,
      int max_generation,
      Problem problem) {

    this.selection = selection;
    this.crossover = crossover;
    this.reinsertion = reinsertion;
    this.fitnessEvaluator = fitnessEvaluator;
    this.mutationRate = mutationRate;
    this.crossoverRate = crossoverRate;
    this.elitismRate = elitismRate;
    this.pop_size = pop_size;
    this.max_generation = max_generation;
    this.problem = problem;
  }

  public Individual run() {
    Population pop = new Population(this.pop_size);

    for (Individual indiv : pop.getIndividuals()) {
      this.fitnessEvaluator.evaluate(indiv, this.problem);
    }

    int generation = 0;
    Individual best = null;

    while (generation < this.max_generation) {
      this.selection.updateProb(pop);
      best = pop.getFittest();

      if (best.getFitness() == 0) {
        return best;
      }

      int targetChildren = (int) (this.pop_size * this.crossoverRate);
      Population childrenPop = new Population();

      while (childrenPop.getSize() < targetChildren) {
        Individual parent1 = this.selection.select(pop);
        Individual parent2 = this.selection.select(pop);

        Individual[] children = this.crossover.crossover(parent1, parent2);

        if (Math.random() < this.mutationRate)
          Mutation.mutate(children[0]);

        if (Math.random() < this.mutationRate)
          Mutation.mutate(children[1]);

        this.fitnessEvaluator.evaluate(children[0], this.problem);
        this.fitnessEvaluator.evaluate(children[1], this.problem);

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
