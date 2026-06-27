package etapa3.core;

import java.util.Random;

public class TourSelection implements SelectionStrategy {

  private static Random random = new Random();

  @Override
  public Individual select(Population pop) {
    // criamos uma população temporaria pra armazenar os individuos selecionados pro
    // torneio
    Population tournament = new Population();
    // sequestramos os individuos que vão participar do torneio
    for (int i = 0; i < 3; i++) { // tour size 3
      int randomIndex = random.nextInt(pop.getSize());
      tournament.addIndividual(pop.getIndividuals().get(randomIndex));
    }
    // retornamos o individuo mais apto
    return tournament.getFittest();
  }
}
