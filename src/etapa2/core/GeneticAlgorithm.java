package etapa2.core;

public class GeneticAlgorithm {

  private SelectionStrategy selection;
  private CrossoverStrategy crossover;
  private ReinsertionStrategy reinsertion;
  private double mutationRate;
  private double crossoverRate;
  private double elitismRate;
  private int pop_size;
  private int max_generation;

  public GeneticAlgorithm(
    
      SelectionStrategy selection,
      CrossoverStrategy crossover,
      ReinsertionStrategy reinsertion,
      double mutationRate,
      double crossoverRate,
      double elitismRate,
      int pop_size,
      int max_generation) {

    this.selection = selection;
    this.crossover = crossover;
    this.reinsertion = reinsertion;
    this.mutationRate = mutationRate;
    this.crossoverRate = crossoverRate;
    this.elitismRate = elitismRate;
    this.pop_size = pop_size;
    this.max_generation = max_generation;
  }

  public Individual run() {
    Population pop = new Population(this.pop_size);

    for (Individual indiv : pop.getIndividuals()) {
      FitnessEvaluator.evaluate(indiv);
    }

    int generation = 0;
    Individual best = null;

    while (generation < this.max_generation) {
      this.selection.updateProb(pop);
      best = pop.getFittest();

      // System.out.println("\nGeneration: " + generation + "\nBest Individual: " +
      // best.toString());

      if (best.getFitness() == 0) {
        // System.out.println("Solution Found!");
        // System.out.println(best.toString());
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
