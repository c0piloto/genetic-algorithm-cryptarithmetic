package etapa3.core;

import etapa3.core.FitnessEvaluatorStrategy;
import etapa3.core.Problem;
import java.util.List;

public class FitnessEvaluator implements FitnessEvaluatorStrategy {

  public void evaluate(Individual ind, Problem problem) {
    int[] chromo = ind.getChromosome();

    int sum = 0;
    for (int i = 0; i < problem.getWords().length - 1; i++) {
      sum += wordToNumber(chromo, problem.getWords()[i], problem.getUniqueLetters());
    }

    int result = wordToNumber(chromo, problem.getWords()[problem.getWords().length - 1], problem.getUniqueLetters());

    ind.setFitness(Math.abs(sum - result));
  }
}
