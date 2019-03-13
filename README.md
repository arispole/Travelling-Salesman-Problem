# Travelling-Salesman-Problem

Problem description: A traveling salesman must visit a certain number of cities. He knows the distance from one city to another and wants to determine the shortest route that allows him to leave his home and return after visiting each city only once. How can he do that?

Choice of the data structure for the graph representation: matrix or list.
Representation of the solutions through the use of vectors (set of cities in order of visit).
Establish a number of solutions that are part of the first generation.
Definition of the fitness function as the inverse of the length of the path.
Establish criteria for selecting solutions by discarding invalid ones and those with a fitness function below a certain value.
Establish a rate of population increase with each generation, thus resulting in a mortality rate lower than the birth rate.
A pairing criterion must be established, attributing a higher probability of reproduction to higher fitness solutions.
Write a function that generates children using the order crossover criterion.
Introduce a probabilistic criterion of conservation of parents instead of children if they have a lower fitness.
Introduce a criterion for random mutations, for example simply reversing the visiting order of two cities.
Keep the best solution for every generation.
Draw a graph showing the evolution trend, ie the number of individuals in the population, the average fitness and the best fitness, for each generation.
Execute the procedure for a certain period or for a given number of generations.
