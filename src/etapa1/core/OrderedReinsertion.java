package etapa1.core;

import etapa1.core.Population;

public class OrderedReinsertion implements ReinsertionStrategy {
  @Override
  public Population reinsert(Population parentPop, Population childrenPop, double elitismRate) {
    // criamos uma pop temporaria pra guardar os pais e os filhos
    Population combinedPop = new Population();
    for (Individual ind : parentPop.getIndividuals()) {
      combinedPop.addIndividual(ind);
    }
    for (Individual ind : childrenPop.getIndividuals()) {
      combinedPop.addIndividual(ind);
    }
    // ordenamos a população combinada
    combinedPop.sortByFitness();
    // criamos uma nova população
    Population nextGen = new Population();
    for (int i = 0; i < parentPop.getSize(); i++) {
      nextGen.addIndividual(combinedPop.getIndividuals().get(i));
    }
    return nextGen;
  }
}
