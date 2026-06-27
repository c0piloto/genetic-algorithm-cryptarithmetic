package etapa2.core;

public class ElitismReinsertion implements ReinsertionStrategy {
  @Override
  public Population reinsert(Population parentPop, Population childrenPop, double elitismRate) {
    Population nextGen = new Population();
    // ordenamos ambas as populações
    parentPop.sortByFitness();
    childrenPop.sortByFitness();
    // calculamos o # de pais a serem mantidos
    int numElite = (int) (parentPop.getSize() * elitismRate);
    // colocamos os pais na proxima geração
    for (int i = 0; i < numElite; i++) {
      nextGen.addIndividual(parentPop.getIndividuals().get(i));
    }
    // e dps colocamos os filhos
    int remainingSpots = parentPop.getSize() - numElite;
    for (int i = 0; i < remainingSpots; i++) {
      nextGen.addIndividual(childrenPop.getIndividuals().get(i));
    }

    return nextGen;
  }
}