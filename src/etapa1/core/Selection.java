package etapa1.core;

import java.util.Random;

public class Selection {
    private static Random random = new Random();
    
    public static Individual tournamentSelection(Population pop, int tourSize) {
        // criamos uma população temporaria pra armazenar os individuos selecionados pro torneio
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
