package etapa1.core;

import etapa1.core.Individual;

public interface CrossoverStrategy {
  public Individual[] crossover(Individual p1, Individual p2);
}
