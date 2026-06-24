package etapa1.core;

public class FitnessEvaluator {

    public static void evaluate(Individual ind) {
        int[] chromo = ind.getChromosome();
        int S = chromo[0];
        int E = chromo[1];
        int N = chromo[2];
        int D = chromo[3];
        int M = chromo[4];
        int O = chromo[5];
        int R = chromo[6];
        int Y = chromo[7];

        int send  = (S * 1000) + (E * 100) + (N * 10) + D;
        int more  = (M * 1000) + (O * 100) + (R * 10) + E;
        int money = (M * 10000) + (O * 1000) + (N * 100) + (E * 10) + Y;
        //|(SEND + MORE) - MONEY|
        // 0 é a solução perfeita
        int fitness = Math.abs((send + more) - money);
        ind.setFitness(fitness);
    }
}