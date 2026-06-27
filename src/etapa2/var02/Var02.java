package etapa2.var02;

import etapa2.core.*;
// alteração pro teste 2: população de 200
public class Var02 {
  private static final int RUNS = 1000;
  private static final double MUTATION_RATE = 0.20; // TM2
  private static final double CROSSOVER_RATE = 0.80; // R2 usa 80%
  private static final double ELITISM_RATE = 0.20; 
  private static final int POP_SIZE = 200; 
  private static final int MAX_GENERATION = 50; 

  public static void main(String[] args) {
    int convergenceCount = 0;
    long totalTime = 0;
    Individual result = null;

    for (int run = 0; run < RUNS; run++) {
      GeneticAlgorithm ga = new GeneticAlgorithm(
          new TourSelection(), // S1
          new PmxCrossover(), // C2
          new ElitismReinsertion(), // R2
          MUTATION_RATE,
          CROSSOVER_RATE,
          ELITISM_RATE,
          POP_SIZE,
          MAX_GENERATION);

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

    System.out.println("=== TM2 S1 C2 R2 ===");
    System.out.println("Melhor indivíduo: " + result.toString());
    System.out.printf("Convergência: %.2f%%%n", convergenceRate);
    System.out.printf("Tempo médio:  %.2f ms%n", avgTime);
  }
}
