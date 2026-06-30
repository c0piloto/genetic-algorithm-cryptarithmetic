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


| Variant | Average Fitness | Average Convergence | Average Time |
| :--- | :--- | :--- | :--- |
| Variant 01 | 1.0  | 86.10%  | 1.928 ms |
| Variant 02 | 0.1  | 90.99%  | 2.118 ms |
| Variant 03 | 0.3  | 63.82%  | 1.738 ms |
| Variant 04 | 0.3  | 74.95%  | 2.311 ms |
| Variant 05 | 1.7  | 74.09%  | 1.781 ms |
| Variant 06 | 3.0 | 78.02% | 1.873 ms |
| Variant 07 | 0.2 | 74.65% | 2.742 ms |
| Variant 08 | 0 | 85.44% | 1.916 ms |
| Variant 09 | 0  | 89.62%  | 1.743 ms |
| Variant 10 | 0.45 | 96.20% | 1.465 ms |


* **Var01 (População de 150):** Showed a significant increase in the average execution time, but yielded a solid improvement in overall convergence (reaching 86.10%).
* **Var02 (População de 200):** Yielded similar trends to Var01, pushing convergence even higher (90.99%), but driving the execution time dangerously close to the 50% penalty limit.
* **Var03 (População de 75):** Resulted in a sharp drop in convergence. Because it struggled to find the solution, it failed to trigger early stopping, causing the average execution time to actually increase.
* **Var04 (100 Max Generations):** Resulted in a slight improvement to the convergence rate. The execution time increased but was effectively mitigated by the early stop mechanism, keeping it just under the penalty limit.
* **Var05 (90% Crossover Rate):** Resulted in a minor increase in the convergence rate compared to the Stage 1 baseline, with a slight time penalty.
* **Var06 (30% Mutation Rate):** Resulted in a slight increase in the convergence rate, performing roughly 4% better than Var05.
* **Var07 (30% Elitism):** While convergence saw no significant changes, this variant is disqualified as the execution time spiked to 2.742 ms, violating the assignment's rule of a maximum 50% time increase.
* **Var08 (Population 120 + 35% Mutation):** Increased convergence significantly (85.44%) while maintaining a safe and stable average execution time.
* **Var09 (60% Mutation Rate):** Yielded a massive jump in convergence (89.62%) while keeping the execution time highly optimized.
* **Var10 (Population 120 + 70% Mutation):** achieved a near-perfect convergence rate of 96.20%. Surprisingly, the average time decreased below the Stage 1 baseline (1.465 ms). The aggressive 70% mutation rate allowed the algorithm to find the global optimum so fast that early stopping was triggered in the earliest generations.

## Stage

In the third stage, we took the best configuration from Stage 2 (Var10) and refactored the fitness evaluation to be generic, in order to get more optimized results we created a parser that mapped the unique letters in the equations to a number in the chromosome, also FitnessEvaluator was refactored to Strategy Pattern as the Positional fitness evaluator was added as to try and optimize convergence. Each variation changes only one parameter at a time, combinations might result into better convergence per time.
The five problems tested were as follows:

* **SEND + MORE = MONEY**
* **EAT + THAT = APPLE**
* **CROSS + ROADS = DANGER**
* **COCA + COLA = OASIS**
* **DONALD + GERALD = ROBERT**

### Overall Results

| Variant | Avarage Convergence | Avarage Time |
| :--- | :--- | :--- |
| Var01_Base | 27.02% | 1.38 ms |
| Var02_popsize | 32.00% | 1.96 ms |
| Var03_maxgen | 28.20% | 2.00 ms |
| Var04_mutrate | 29.44% | 1.40 ms |
| Var05_properror | 28.08% | 1.46 ms | 

### Per-Problem Breakdown

| Problem | Var01_Base | Var02_popsize | Var03_maxgen | Var04_mutrate | Var05_properror |
| :--- | :--- | :--- | :--- | :--- | :--- |
| SEND + MORE = MONEY | 95.80% / 0.63 ms | 98.60% / 0.77 ms | 96.40% / 0.69 ms | 97.80% / 0.69 ms | 77.20% / 1.02 ms |
| EAT + THAT = APPLE | 21.60% / 1.37 ms | 31.10% / 1.78 ms | 24.20% / 1.96 ms | 24.80% / 1.39 ms | 13.10% / 1.52 ms |
| CROSS + ROADS = DANGER | 0.40% / 1.72 ms | 1.60% / 2.59 ms | 1.10% / 2.54 ms | 1.00% / 1.67 ms | 4.60% / 1.69 ms |
| COCA + COLA = OASIS | 11.80% / 1.40 ms | 20.90% / 2.01 ms | 11.90% / 2.08 ms | 15.30% / 1.44 ms | 31.80% / 1.31 ms |
| DONALD + GERALD = ROBERT | 5.50% / 1.78 ms | 7.80% / 2.63 ms | 7.40% / 2.72 ms | 8.30% / 1.83 ms | 13.70% / 1.76 ms |

*Format: Convergence % / Mean Time*


Note that the best increase in convergence is to increase the population size, when increasing the max number of generations it also wielded good results but the processing time got higher by more than 50% the previous times.
