package etapa1.core;

public interface SelectionStrategy {

  public Individual select(Population pop);

  default void updateProb(Population pop) {
    return;
  }
}
