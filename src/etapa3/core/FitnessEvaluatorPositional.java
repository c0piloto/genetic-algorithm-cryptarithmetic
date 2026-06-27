package etapa3.core;

import etapa3.core.FitnessEvaluatorStrategy;
import etapa3.core.Problem;
import java.util.List;

public class FitnessEvaluatorPositional implements FitnessEvaluatorStrategy {

  @Override
  public void evaluate(Individual ind, Problem problem) {
    int[] chromo = ind.getChromosome();

    int[] numbers = new int[problem.getWords().length];

    for (int i = 0; i < problem.getWords().length; i++) {
      numbers[i] = wordToNumber(chromo, problem.getWords()[i], problem.getUniqueLetters());
    }

    int result = numbers[problem.getWords().length - 1];

    int sum = 0;
    for (int i = 0; i < numbers.length - 1; i++) {
      sum += numbers[i];
    }

    int fitness = 0;
    int carry = 0;

    while (sum > 0 || result > 0 || carry > 0) {
      int colSum = (sum % 10) + carry;
      int expectedDigit = colSum % 10;
      int resultDigit = result % 10;

      fitness += Math.abs(expectedDigit - resultDigit);

      carry = colSum / 10;
      sum /= 10;
      result /= 10;
    }
    ind.setFitness(fitness);
  }
}
