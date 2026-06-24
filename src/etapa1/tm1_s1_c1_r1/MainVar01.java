package etapa1.tm1_s1_c1_r1;

import etapa1.core.FitnessEvaluator;
import etapa1.core.Individual;
import etapa1.core.Population;
import etapa1.core.Selection;

public class MainVar01 {
    public static void main(String[] args) {
        System.out.println("iniciando teste......");
        Population pop = new Population(100);
        System.out.println("população de " + pop.getSize() + " criada.");
        for (Individual ind : pop.getIndividuals()) {
            FitnessEvaluator.evaluate(ind);
        }
        System.out.println("população avaliada.");
        pop.sortByFitness();
        System.out.println("\nTop 3 individuos na pop inicial:");
        System.out.println("1°: " + pop.getIndividuals().get(0));
        System.out.println("2°: " + pop.getIndividuals().get(1));
        System.out.println("3°: " + pop.getIndividuals().get(2));
        System.out.println("\ntestando seleção por torneio com tamanho 3");
        Individual parent1 = Selection.tournamentSelection(pop, 3);
        Individual parent2 = Selection.tournamentSelection(pop, 3);

        System.out.println("pai 1: " + parent1);
        System.out.println("pai 2: " + parent2);
        
        System.out.println("\ndeu certo pae");
    }
}