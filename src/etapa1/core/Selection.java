package src.etapa1.core;

import java.util.Random;

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

  public static Individual rouletteSelection(Population pop) {
    // Criamos a porcentagem de corte por sorteio entre 0% e 100%
    double cut = random.nextDouble(1.0);

    // Loop que procura o melhor individuo, como o calculo é feito
    // na mesma ordem em que está a população então o índice do
    // vetor de probabilidades acumuladas equivale a população,
    // ou seja, a probabilidade acumulada do item no indice i
    // é a probabilidade acumulada do item da população na posição de mesmo indice
    for (int i = 0; i < cumulativeProb.length; i++) {
      if (cumulativeProb[i] >= cut) {
        return pop.getIndividuals().get(i);
      }
    }

    // Retornamos o melhor indivíduo
    return pop.getIndividuals().getLast();
  }

  public static void updateProb(Population pop) {

    // Instancia o vetor de probabilidades acumuladas com o tamanho da população
    cumulativeProb = new double[pop.getSize()];

    // Atributos auxiliares para guardar o total inverso da probabilidade
    // e a probabilidade acumulada de cada individuo
    double totalInverseProb = 0.0;
    double cumulative = 0.0;

    // Calculo simples do total
    for (Individual indiv : pop.getIndividuals()) {
      totalInverseProb += 1.0 / (indiv.getFitness() + 1);
    }

    // Probabilidades calculadas no final, que é a probabilidade inversa de cada
    // individuo
    // dividida pela probabilidade total acumulada
    for (int i = 0; i < pop.getSize(); i++) {
      cumulative += (1.0 / (pop.getIndividuals().get(i).getFitness() + 1)) / totalInverseProb;
      cumulativeProb[i] = cumulative;
    }
  }
}
