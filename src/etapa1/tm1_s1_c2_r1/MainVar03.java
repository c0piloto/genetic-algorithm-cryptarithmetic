package etapa1.tm1_s1_c2_r1;

import etapa1.core.*;

public class MainVar03 {
  private static final int RUNS = 1000;
  private static final double MUTATION_RATE = 0.10; // TM1
  private static final double CROSSOVER_RATE = 0.60; //R1 usa 60% R2 usa 80%

  public static void main(String[] args) {
    int convergenceCount = 0;
    long totalTime = 0;
    Individual result = null;

    for (int run = 0; run < RUNS; run++) {
      GeneticAlgorithm ga = new GeneticAlgorithm(
          new TourSelection(), // S1
          new PmxCrossover(), // C2
          new OrderedReinsertion(), // R1
          MUTATION_RATE,
          CROSSOVER_RATE,
          0.0);

      long start = System.currentTimeMillis();
      result = ga.run();
      long end = System.currentTimeMillis();

      totalTime += (end - start);

      if (result != null && result.getFitness() == 0) {
        convergenceCount++;
      }
    }

    double convergenceRate = (convergenceCount / (double) RUNS) * 100;
    double avgTime = totalTime / (double) RUNS;

    System.out.println("=== TM1 S1 C2 R1 ===");
    System.out.println("Melhor indivíduo: " + result.toString());
    System.out.printf("Convergência: %.2f%%%n", convergenceRate);
    System.out.printf("Tempo médio:  %.2f ms%n", avgTime);
  }
}
