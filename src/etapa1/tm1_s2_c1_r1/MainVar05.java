package etapa1.tm1_s2_c1_r1;

import etapa1.core.*;

public class MainVar05 {
  private static final int RUNS = 1000;
  private static final double MUTATION_RATE = 0.10; // TM1
  private static final double CROSSOVER_RATE = 0.60; // R1 usa 60%

  public static void main(String[] args) {
    int convergenceCount = 0;
    long totalTime = 0;
    Individual result = null;
    long totalFitness = 0;

    for (int run = 0; run < RUNS; run++) {
      GeneticAlgorithm ga = new GeneticAlgorithm(
          new RouletteSelection(), // S2
          new CyclicCrossover(), // C1
          new OrderedReinsertion(), // R1
          MUTATION_RATE,
          CROSSOVER_RATE,
          0.0);

      long start = System.currentTimeMillis();
      result = ga.run();
      long end = System.currentTimeMillis();

      totalFitness = result.getFitness();
      totalTime += (end - start);

      if (result != null && result.getFitness() == 0) {
        convergenceCount++;
      }
    }

    double convergenceRate = (convergenceCount / (double) RUNS) * 100;
    double avgTime = totalTime / (double) RUNS;
    double avgFitness = totalFitness / (double) RUNS;

    System.out.println("=== TM1 S2 C1 R1 ===");
    System.out.println("Best individual:        " + result.toString());
    System.out.printf("Convergence:     %.2f%%%n", convergenceRate);
    System.out.printf("Avarage Time:    %.2f ms%n", avgTime);
    System.out.printf("Avarage Fitness: %.2f%n", avgFitness);
  }
}
