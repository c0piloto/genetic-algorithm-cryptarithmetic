package etapa3.core;

import java.util.List;

public interface FitnessEvaluatorStrategy {

  public void evaluate(Individual ind, Problem problem);

  public default int wordToNumber(int[] chromo, char[] word, List<Character> uniqueLetters) {
    int number = 0;

    for (char c : word) {
      int index = uniqueLetters.indexOf(c);
      int digit = chromo[index];
      number = number * 10 + digit;
    }

    return number;
  }
}
