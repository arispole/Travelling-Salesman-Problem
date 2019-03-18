public class Individual {
	
	private int[] cromosome;
	private double fitness;
	private double reproduction;

	public Individual(int cities) {								
		cromosome = new int[cities];
		fitness = 0.0;
		reproduction = 0.0;
	}

	public int[] getCromosome() {
		return cromosome;
	}
	
	public void setCromosome(int[] cromosome) {
		this.cromosome = cromosome.clone();
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getReproduction() {
		return reproduction;
	}

	public void setReproduction(double reproduction) {
		this.reproduction = reproduction;
	}

}
