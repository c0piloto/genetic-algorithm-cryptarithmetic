package etapa1.core;
import java.util.Arrays;

public class Crossover {
    // pra auxiliar na escolha dos pontos de crossover no pmx
    private static java.util.Random random = new java.util.Random();

    public static Individual[] cyclicCrossover(Individual p1, Individual p2) {
        int[] parent1 = p1.getChromosome();
        int[] parent2 = p2.getChromosome();
        
        int[] child1 = new int[10];
        int[] child2 = new int[10];
        
        //preenchemos os filhos com -1 para indicar as posições q ainda não foram preenchidas
        Arrays.fill(child1, -1);
        Arrays.fill(child2, -1);
        
        int currentIndex = 0;
        int startingValue = parent1[0];
        while (true) {
            child1[currentIndex] = parent1[currentIndex];
            child2[currentIndex] = parent2[currentIndex];

            int valueFromParent2 = parent2[currentIndex];

            int nextIndex = -1;
            for (int i = 0; i < 10; i++) {
                if (parent1[i] == valueFromParent2) {
                    nextIndex = i;
                    break;
                }
            }
            currentIndex = nextIndex;
            if (parent1[currentIndex] == startingValue) {
                break;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (child1[i] == -1) {
                child1[i] = parent2[i];
                child2[i] = parent1[i];
            }
        }
        return new Individual[]{
            new Individual(child1), 
            new Individual(child2)
        };
    }

    public static Individual[] pmxCrossover(Individual p1, Individual p2) {
        int[] parent1 =p1.getChromosome();
        int[] parent2 = p2.getChromosome();
        int length = parent1.length;
        int[] child1 = new int[length];
        int[] child2 = new int[length];
        Arrays.fill(child1, -1);
        Arrays.fill(child2, -1);

        int pt1 = random.nextInt(length);
        int pt2 = random.nextInt(length);
    
        if (pt1 > pt2) { 
            int temp = pt1; 
            pt1 = pt2; 
            pt2 = temp; 
        }
        for (int i = pt1; i <= pt2; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
        }
        
        for (int i = 0; i < length; i++){
            if (i >= pt1 && i <= pt2) continue; //pula o segmento do meio
            int val = parent2[i];

            while (contains(child1, val,pt1,pt2)){
                int indexInP1 = indexOf(parent1,val,pt1,pt2);
                val = parent2[indexInP1];
            }
            child1[i] = val;
        }
        // rpetimos o processo pro segundo filho
        for (int i = 0; i < length; i++){
            if (i >= pt1 && i <= pt2) continue; //pula o segmento do meio
            int val = parent1[i];

            while (contains(child2,val,pt1, pt2)){
                int indexInP2 = indexOf(parent2, val,pt1,pt2);
                val = parent1[indexInP2];
            }
            child2[i] = val;
        }
        return new Individual[]{new Individual(child1),new Individual(child2)};
    }

    // metodos auxiliares do pmx
    private static boolean contains(int[] array, int val, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (array[i] == val) return true;
        }
        return false;
    }
    private static int indexOf(int[] array, int val, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (array[i] == val) return i;
        }
        return -1;
    }
}
