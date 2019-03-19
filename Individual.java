public class Individual {
	
	private int[] cromosome;
	private long pathCost;
	private double reproduction;

	public Individual(int cities) {								
		cromosome = new int[cities];
		pathCost = Long.MAX_VALUE;
		reproduction = 0.0;
	}

	public int[] getCromosome() {
		return cromosome;
	}
	
	public void setCromosome(int[] cromosome) {
		this.cromosome = cromosome.clone();
	}

	public long getPathCost() {
		return pathCost;
	}

	public void setPathCost(long pathCost) {
		this.pathCost = pathCost;
	}

	public double getReproduction() {
		return reproduction;
	}

	public void setReproduction(double reproduction) {
		this.reproduction = reproduction;
	}
	
	public void setGene(int value, int index) {
		cromosome[index] = value;
	}

}
