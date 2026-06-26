package etapa1.core;

public interface ReinsertionStrategy {

  public Population reinsert(Population parentPop, Population childrenPop, double elitismRate);
}
