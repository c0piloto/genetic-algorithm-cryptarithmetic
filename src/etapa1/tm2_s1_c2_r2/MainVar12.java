package etapa1.tm2_s1_c2_r2;

import etapa1.core.*;

public class MainVar12 {
  private static final int RUNS = 1000;
  private static final double MUTATION_RATE = 0.20; // TM2
  private static final double CROSSOVER_RATE = 0.80; // R2 usa 80%

  public static void main(String[] args) {
    int convergenceCount = 0;
    long totalTime = 0;
    Individual result = null;
    long totalFitness = 0;

    for (int run = 0; run < RUNS; run++) {
      GeneticAlgorithm ga = new GeneticAlgorithm(
          new TourSelection(), // S1
          new PmxCrossover(), // C2
          new ElitismReinsertion(), // R2
          MUTATION_RATE,
          CROSSOVER_RATE,
          0.20);

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

    System.out.println("=== TM2 S1 C2 R2 ===");
    System.out.println("Best individual:        " + result.toString());
    System.out.printf("Convergence:     %.2f%%%n", convergenceRate);
    System.out.printf("Avarage Time:    %.2f ms%n", avgTime);
    System.out.printf("Avarage Fitness: %.2f%n", avgFitness);
  }
}
