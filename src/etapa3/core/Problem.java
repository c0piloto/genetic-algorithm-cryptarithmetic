package etapa3.core;

import java.util.List;
import java.util.ArrayList;

public class Problem {
  private List<Character> uniqueLetters;
  private char[][] words;

  public Problem(String equation) {
    this.uniqueLetters = new ArrayList<>();

    String[] parts = equation.split("[+=]");
    this.words = new char[parts.length][];

    for (int i = 0; i < parts.length; i++) {
      this.words[i] = parts[i].trim().toCharArray();
    }

    for (char[] word : this.words) {
      for (char c : word) {
        if (!this.uniqueLetters.contains(c))
          uniqueLetters.add(c);
      }
    }
  }

  public List<Character> getUniqueLetters() {
    return this.uniqueLetters;
  }

  public char[][] getWords() {
    return this.words;
  }
}
