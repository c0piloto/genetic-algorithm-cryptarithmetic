package src.etapa1.core;

import java.util.Random;
import java.util.List;

import src.etapa1.core.Individual;

public class Selection {
  private static Random random = new Random();
  private static double[] cumulativeProb;

  public static Individual tournamentSelection(Population pop, int tourSize) {
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

  private static void updateProb(Population pop) {
    cumulativeProb = new double[pop.getSize()];

    double totalInverseProb = 0.0;
    double cumulative = 0.0;

    for (Individual indiv : pop.getIndividuals()) {
      totalInverseProb += 1.0 / (indiv.getFitness() + 1);
    }

    for (int i = 0; i < pop.getSize(); i++) {
      cumulative += (1.0 / (pop.getIndividuals().get(i).getFitness() + 1)) / totalInverseProb;
      cumulativeProb[i] = cumulative;
    }
  }
}
