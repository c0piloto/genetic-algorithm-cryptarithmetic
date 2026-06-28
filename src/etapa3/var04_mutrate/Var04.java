package etapa3.var04_mutrate;

import etapa3.core.*;

// Variação em que vamos mudar a taxa de mutação
// Aumentamos para 90% isso introduz muita aleatoriedade
// O que aumenta a taxa de convergencia sem impactar muito o Tempo
// Mas o problema é que o aumento e minúsculo e não vale a pena
public class Var04 {
  private static final int RUNS = 1000;
  private static final double MUTATION_RATE = 0.90;
  private static final double CROSSOVER_RATE = 0.80;
  private static final double ELITISM_RATE = 0.20;
  private static final int POP_SIZE = 120;
  private static final int MAX_GENERATION = 50;

  private static final String[] PROBLEMS = {
      "SEND + MORE = MONEY",
      "EAT + THAT = APPLE",
      "CROSS + ROADS = DANGER",
      "COCA + COLA = OASIS",
      "DONALD + GERALD = ROBERT"
  };

  public static void main(String[] args) {
    double totalConvergence = 0;
    double totalTime = 0;

    for (String equation : PROBLEMS) {
      Problem problem = new Problem(equation);
      int convergenceCount = 0;
      long problemTime = 0;

      for (int run = 0; run < RUNS; run++) {
        GeneticAlgorithm ga = new GeneticAlgorithm(
            new TourSelection(),
            new PmxCrossover(),
            new ElitismReinsertion(),
            new FitnessEvaluator(),
            MUTATION_RATE,
            CROSSOVER_RATE,
            ELITISM_RATE,
            POP_SIZE,
            MAX_GENERATION,
            problem);

        long start = System.currentTimeMillis();
        Individual result = ga.run();
        long end = System.currentTimeMillis();

        problemTime += (end - start);
        if (result != null && result.getFitness() == 0)
          convergenceCount++;
      }

      double convergenceRate = (convergenceCount / (double) RUNS) * 100;
      double avgTime = problemTime / (double) RUNS;
      totalConvergence += convergenceRate;
      totalTime += avgTime;

      System.out.printf("%-30s | Convergence: %6.2f%% | Mean time: %.2f ms%n",
          equation, convergenceRate, avgTime);
    }

    System.out.println("\n=== OVERALL AVARAGES ===");
    System.out.printf("Convergence avarage: %.2f%%%n", totalConvergence / PROBLEMS.length);
    System.out.printf("Total time avarage:  %.2f ms%n", totalTime / PROBLEMS.length);
  }
}
