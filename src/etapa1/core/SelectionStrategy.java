package etapa1.core;

import etapa1.core.Individual;
import etapa1.core.Population;

public interface SelectionStrategy {

  public Individual select(Population pop, int tourSize);

  public Individual select(Population pop);

  default void updateProb(Population pop) {
    return;
  }
}
