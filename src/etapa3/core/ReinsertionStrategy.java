package etapa3.core;

public interface ReinsertionStrategy {

  public Population reinsert(Population parentPop, Population childrenPop, double elitismRate);
}
