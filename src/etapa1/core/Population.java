package etapa1.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Population {
  private List<Individual> individuals;

  public Population(int size) {
    this.individuals = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      this.individuals.add(new Individual());
    }
  }

  // cria uma população vazia que pode ser preenchida em uma proxima geração
  public Population() {
    this.individuals = new ArrayList<>();
  }

  public void addIndividual(Individual ind) {
    this.individuals.add(ind);
  }

  public List<Individual> getIndividuals() {
    return individuals;
  }

  public int getSize() {
    return individuals.size();
  }

  // ordena em ordem crescente pra garantir que o melhor individuo fica na
  // primeira posição
  public void sortByFitness() {
    individuals.sort(Comparator.comparingInt(Individual::getFitness));
  }

  // encontra o melhor individuo atual sem ter que ordenar a população inteira
  public Individual getFittest() {
    Individual fittest = individuals.get(0);
    for (Individual ind : individuals) {
      if (ind.getFitness() < fittest.getFitness()) {
        fittest = ind;
      }
    }
    return fittest;
  }
}
