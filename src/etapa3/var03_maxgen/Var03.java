package etapa3.var03_maxgen;

import etapa3.core.*;

// Variação em que as gerações aumentaram de 50 para 80 neste caso tivemos
// um ganho de ~2% de convergência enquanto nos mantemos em média no limite
// dos 50% de ganho de tempo
public class Var03 {
  private static final int RUNS = 1000;
  private static final double MUTATION_RATE = 0.70;
  private static final double CROSSOVER_RATE = 0.80;
  private static final double ELITISM_RATE = 0.20;
  private static final int POP_SIZE = 120;
  private static final int MAX_GENERATION = 80;

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

      System.out.printf("%-30s | Convergência: %6.2f%% | Tempo médio: %.2f ms%n",
          equation, convergenceRate, avgTime);
    }

    System.out.println("\n=== MÉDIAS GERAIS ===");
    System.out.printf("Convergência média: %.2f%%%n", totalConvergence / PROBLEMS.length);
    System.out.printf("Tempo médio geral:  %.2f ms%n", totalTime / PROBLEMS.length);
  }
}
