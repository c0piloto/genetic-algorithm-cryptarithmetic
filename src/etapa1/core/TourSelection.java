package etapa1.core;

import etapa1.core.Individual;
import etapa1.core.Population;
import java.util.Random;

public class TourSelection implements SelectionStrategy {

  private static Random random = new Random();

  @Override
  public Individual select(Population pop) {
    return null;
  }

  @Override
  public Individual select(Population pop, int tourSize) {
    // criamos uma população temporaria pra armazenar os individuos selecionados pro
    // torneio
    Population tournament = new Population();
    // sequestramos os individuos que vão participar do torneio
    for (int i = 0; i < tourSize; i++) {
      int randomIndex = random.nextInt(pop.getSize());
      tournament.addIndividual(pop.getIndividuals().get(randomIndex));
    }
    // retornamos o individuo mais apto
    return tournament.getFittest();
  }
}
