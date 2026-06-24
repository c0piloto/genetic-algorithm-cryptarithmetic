package etapa1.core;
import java.util.Random;

public class Mutation {
    private static Random random = new Random();

    public static void mutate(Individual ind) {
        int[] chromo = ind.getChromosome();
        //escolhemos as posições que vão ser mutadas
        int pos1 = random.nextInt(10);
        int pos2 = random.nextInt(10);
        while (pos1 == pos2) {
            pos2 = random.nextInt(10);
        }
        
        //trocamos os valores
        int temp = chromo[pos1];
        chromo[pos1] = chromo[pos2];
        chromo[pos2] = temp;
        
        // atualizamos o indivíduo
        ind.setChromosome(chromo);
        ind.setFitness(-1); // resetamos a fitness pra q o individuo seja reavaliado
    }
}