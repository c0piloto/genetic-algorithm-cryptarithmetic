# Genetic Algorithm: Cryptarithmetic Solver

A Genetic Algorithm (GA) designed to solve 10-letter cryptarithmetic puzzles by mapping a unique digit from 0 to 9 to each letter, ensuring the mathematical equation is valid (e.g., SEND + MORE = MONEY).

# Results

## Stage 1

In the first stage, we implemented the necessary genetic operators for the GA and their respective methods. We then ran and evaluated the 16 different combinations of these methods.

The clear winner in this stage was **TM2S1C2R2**, achieving a 73.23% convergence rate in under 2 milliseconds.

| ID | Average Fitness | Average Convergence | Average Mean Time |
| --- | --- | --- | --- |
| **TM1S1C1R1** | 1.8 | 43.31% | 1.557 ms |
| **TM1S1C1R2** | 1.7 | 45.79% | 1.702 ms |
| **TM1S1C2R1** | 2.5 | 64.96% | 1.516 ms |
| **TM1S1C2R2** | 1.9 | 68.28% | 1.634 ms |
| **TM1S2C1R1** | 9.1 | 39.81% | 1.522 ms |
| **TM1S2C1R2** | 2.55 | 42.95% | 1.555 ms |
| **TM1S2C2R1** | 2.9 | 51.54% | 1.978 ms |
| **TM1S2C2R2** | 5.6 | 51.59% | 1.875 ms |
| **TM2S1C1R1** | 3.6 | 52.00% | 1.299 ms |
| **TM2S1C1R2** | 0.9 | 54.93% | 1.647 ms |
| **TM2S1C2R1** | 2.2 | 70.50% | 1.568 ms |
| **TM2S1C2R2** | 1.6 | 73.23% | 1.563 ms |
| **TM2S2C1R1** | 3.7 | 46.59% | 1.413 ms |
| **TM2S2C1R2** | 3.0 | 50.45% | 1.465 ms |
| **TM2S2C2R1** | 4.3 | 54.00% | 1.852 ms |
| **TM2S2C2R2** | 1.2 | 57.20% | 1.768 ms |

## Stage 2

In the second stage, we took the best configuration from Stage 1 (TM2S1C2R2) and attempted to optimize it further by tweaking parameters such as population size, generation count, and mutation/crossover rates.

* **Var01 (Population of 150):** Showed a significant increase in the average mean time and a slight improvement in overall convergence.
* **Var02 (Population of 200):** Yielded similar results to Var01, but with greater improvements to convergence.
* **Var03 (Population of 75):** Showed an improved average mean time but lower convergence rates.
* **Var04 (100 Max Generations):** Similar to increasing the total population (higher average mean time with a slight improvement to convergence rate), but mitigated by the early stop mechanism.
* **Var05 (90% Crossover Rate):** Resulted in a slight increase in the convergence rate.
* **Var06 (30% Mutation Rate):** Resulted in a slight increase in the convergence rate, performing around 3% better than Var05.
* **Var07 (30% Elitism):** Showed no significant changes.
* **Var08 (Population of 120 + 35% Mutation Rate):** Increased convergence with a slightly higher average mean time.
* **Var09 (60% Mutation Rate):** Yielded a significant increase in convergence.
* **Var10 (Population of 120 + 70% Mutation Rate):** Resulted in a significant increase in convergence while lowering the average mean time.

## Stage

In the third stage, we took the best configuration from Stage 2 (Var10) and refactored the fitness evaluation to be generic, in order to get more optimized results we created a parser that mapped the unique letters in the equations to a number in the chromosome, also FitnessEvaluator was refactored to Strategy Pattern as the Positional fitness evaluator was added as to try and optimize convergence. Each variation changes only one parameter at a time, combinations might result into better convergence per time.
The five problems tested were as follows:

**SEND + MORE = MONEY
EAT + THAT = APPLE
CROSS + ROADS = DANGER
COCA + COLA = OASIS
DONALD + GERALD = ROBERT**

* **Var01_Base (Same as Var10):** 

SEND + MORE = MONEY            | Convergence:  95.80% | Mean time: 0.63 ms
EAT + THAT = APPLE             | Convergence:  21.60% | Mean time: 1.37 ms
CROSS + ROADS = DANGER         | Convergence:   0.40% | Mean time: 1.72 ms
COCA + COLA = OASIS            | Convergence:  11.80% | Mean time: 1.40 ms
DONALD + GERALD = ROBERT       | Convergence:   5.50% | Mean time: 1.78 ms

=== OVERALL AVARAGES ===
Convergence avarage: 27.02%
Total time avarage:  1.38 ms

* **Var02_popsize (Population size increased to 180):**

SEND + MORE = MONEY            | Convergence:  98.60% | Mean time: 0.77 ms
EAT + THAT = APPLE             | Convergence:  31.10% | Mean time: 1.78 ms
CROSS + ROADS = DANGER         | Convergence:   1.60% | Mean time: 2.59 ms
COCA + COLA = OASIS            | Convergence:  20.90% | Mean time: 2.01 ms
DONALD + GERALD = ROBERT       | Convergence:   7.80% | Mean time: 2.63 ms

=== OVERALL AVARAGES ===
Convergence avarage: 32.00%
Total time avarage:  1.96 ms

* **Var03_maxgen (Generations increased to 80):**

SEND + MORE = MONEY            | Convergence:  96.40% | Mean time: 0.69 ms
EAT + THAT = APPLE             | Convergence:  24.20% | Mean time: 1.96 ms
CROSS + ROADS = DANGER         | Convergence:   1.10% | Mean time: 2.54 ms
COCA + COLA = OASIS            | Convergence:  11.90% | Mean time: 2.08 ms
DONALD + GERALD = ROBERT       | Convergence:   7.40% | Mean time: 2.72 ms

=== OVERALL AVARAGES ===
Convergence avarage: 28.20%
Total time avarage:  2.00 ms

* **Var04_mutrate (Mutation rate increased to 90%):**

SEND + MORE = MONEY            | Convergence:  97.80% | Mean time: 0.69 ms
EAT + THAT = APPLE             | Convergence:  24.80% | Mean time: 1.39 ms
CROSS + ROADS = DANGER         | Convergence:   1.00% | Mean time: 1.67 ms
COCA + COLA = OASIS            | Convergence:  15.30% | Mean time: 1.44 ms
DONALD + GERALD = ROBERT       | Convergence:   8.30% | Mean time: 1.83 ms

=== OVERALL AVARAGES ===
Convergence avarage: 29.44%
Total time avarage:  1.40 ms

* **Var05_properror (Fitness evaluator changed to Positional Error):**

SEND + MORE = MONEY            | Convergence:  77.20% | Mean time: 1.02 ms
EAT + THAT = APPLE             | Convergence:  13.10% | Mean time: 1.52 ms
CROSS + ROADS = DANGER         | Convergence:   4.60% | Mean time: 1.69 ms
COCA + COLA = OASIS            | Convergence:  31.80% | Mean time: 1.31 ms
DONALD + GERALD = ROBERT       | Convergence:  13.70% | Mean time: 1.76 ms

=== OVERALL AVARAGES ===
Convergence avarage: 28.08%
Total time avarage:  1.46 ms
