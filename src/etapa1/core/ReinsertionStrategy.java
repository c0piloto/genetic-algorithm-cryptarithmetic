package etapa1.core;

import etapa1.core.Population;

public interface ReinsertionStrategy {

  public Population reinsert(Population parentPop, Population childrenPop, double elitismRate);
}
