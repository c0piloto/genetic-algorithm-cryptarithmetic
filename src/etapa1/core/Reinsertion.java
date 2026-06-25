package etapa1.core;

public class Reinsertion {
    //r1
    public static Population orderedReinsertion(Population parents, Population children) {
        //criamos uma pop temporaria pra guardar os pais e os filhos
        Population combinedPop = new Population();
        for (Individual ind : parents.getIndividuals()){
            combinedPop.addIndividual(ind);
        }
        for (Individual ind : children.getIndividuals()){
            combinedPop.addIndividual(ind);
        }
        // ordenamos a população combinada
        combinedPop.sortByFitness();
        //criamos uma nova população
        Population nextGen = new Population();
        for (int i = 0; i < parents.getSize(); i++){
            nextGen.addIndividual(combinedPop.getIndividuals().get(i)); 

        }
        return nextGen;
    }

    //r2
    public static Population elitismReinsertion(Population parents, Population children, double elitismRate) {
        Population nextGen = new Population();
        // ordenamos ambas as populações
        parents.sortByFitness();
        children.sortByFitness(); 
        // calculamos o # de pais a serem mantidos
        int numElite=(int)(parents.getSize()*elitismRate);
        // colocamos os pais na proxima geração
        for (int i = 0; i < numElite; i++){
            nextGen.addIndividual(parents.getIndividuals().get(i));
        }
        //e dps colocamos os filhos
        int remainingSpots = parents.getSize() - numElite;
        for (int i = 0; i < remainingSpots; i++){
            nextGen.addIndividual(children.getIndividuals().get(i));
        }


        return nextGen;
    }
}