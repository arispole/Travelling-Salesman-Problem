import java.util.Arrays;
import java.util.Collections;

public class Individual {
	
	private int[] cromosome;
	private int fitness;
	private int reproduction;

	public Individual(int[] trace) {								
		cromosome = trace;
		Collections.shuffle(Arrays.asList(cromosome));
		fitness = 0;
		reproduction = 0;
	}

	public int[] getCromosome() {
		return cromosome;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public int getReproduction() {
		return reproduction;
	}

	public void setReproduction(int reproduction) {
		this.reproduction = reproduction;
	}

}
