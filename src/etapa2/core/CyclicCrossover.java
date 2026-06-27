package etapa2.core;

import java.util.Arrays;

public class CyclicCrossover implements CrossoverStrategy {
  @Override
  public Individual[] crossover(Individual p1, Individual p2) {
    int[] parent1 = p1.getChromosome();
    int[] parent2 = p2.getChromosome();

    int[] child1 = new int[10];
    int[] child2 = new int[10];

    // preenchemos os filhos com -1 para indicar as posições q ainda não foram
    // preenchidas
    Arrays.fill(child1, -1);
    Arrays.fill(child2, -1);

    int currentIndex = 0;

    // Iniciando com o primeiro valor, é válido mas pode ser uma melhoria
    // escolher aleatóriamente, introduz mais variação
    int startingValue = parent1[0];

    while (true) {
      child1[currentIndex] = parent1[currentIndex];
      child2[currentIndex] = parent2[currentIndex];

      int valueFromParent2 = parent2[currentIndex];

      if (valueFromParent2 == startingValue) {
        break;
      }

      int nextIndex = -1;
      for (int i = 0; i < 10; i++) {
        if (parent1[i] == valueFromParent2) {
          nextIndex = i;
          break;
        }
      }
      currentIndex = nextIndex;
    }

    for (int i = 0; i < 10; i++) {
      if (child1[i] == -1) {
        child1[i] = parent2[i];
        child2[i] = parent1[i];
      }
    }

    return new Individual[] {
        new Individual(child1),
        new Individual(child2)
    };
  }
}