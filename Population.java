import java.util.ArrayList;
import java.util.List;

public class Population {
	
	static List<Individual> l;

	public Population(int cities, int numpop) {
		
		Individual a;
		l = new ArrayList<Individual>();
		int trace[] = new int[cities-1];
		
		for (int j = 0; j < cities-1; j++) {
			trace[j] = j+1;
		}
		
		for (int i = 0; i < numpop; i++) {				
			a = new Individual(trace);
			l.add(a);
		} 
	}
	
	public Population() {
		l = new ArrayList<Individual>();
	}

	public void valid(int[][] matrix, int cities) {
		
		int[] v;
		int r, c;
		int fitness = 0;
		boolean valid;
		
		for (int i = 0; i < l.size(); i++) {
			
			valid = true;
			v = l.get(i).getCromosome();
			
			for (int j = 0; j < cities; j++) {
				
				r = v[j];
				c = v[j+1];
				
				if (j == 0 || j == cities-1) {
					if (matrix[0][r] == 0) {								//non-existent path with the native city
						valid = false;
						l.remove(i);
						break;
					}
					else fitness += matrix[0][r];
				}
				
				else {
					if (matrix[r][c] == 0) {								//non-existent path 
						valid = false;
						l.remove(i);
						break;
					}
					else fitness += matrix[r][c];
				}
				
			}
			
			if (valid) {
				l.get(i).setFitness(1/fitness);
			}
			
		}
		
	}

	public void setProb(int[][] matrix) {
		
		int totalFitness = 0;
		int reproduction = 0;
		
		for (int i = 0; i < l.size(); i++) {
			totalFitness += l.get(i).getFitness();
		}
		
		for (int j = 0; j < l.size(); j++) {
			reproduction = l.get(j).getFitness() / totalFitness;
			l.get(j).setReproduction(reproduction);
		}
	}

	public void pairing() {
		
		
	}

	public Individual comparison(Individual bestIndividual) {
		
		Individual temp;
		
		for (int i = 0; i < l.size(); i++) {
			
			temp = l.get(i);
			
			if (temp.getFitness() > bestIndividual.getFitness()) {
				bestIndividual = temp;
			}
			
		}
		
		return bestIndividual;
	}


}
