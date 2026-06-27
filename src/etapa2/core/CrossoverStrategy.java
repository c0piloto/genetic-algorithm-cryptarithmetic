package etapa2.core;

public interface CrossoverStrategy {
  public Individual[] crossover(Individual p1, Individual p2);
}
