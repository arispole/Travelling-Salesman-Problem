import java.util.ArrayList;
import java.util.List;

public class Population {
	
	protected List<Individual> l;
	protected long numpop;
	protected long lowestPathCost;
	protected long averagePathCost;

	public Population() {					
		l = new ArrayList<Individual>();
		numpop = 0;
	}
	
	public long getLowestPathCost() {
		return lowestPathCost;
	}

	public long getAveragePathCost() {
		return averagePathCost;
	}
	
	public long getNumpop() {
		return numpop;
	}
	
	public List<Individual> getList() {
		return l;
	}

	public void valid(int[][] matrix, int cities) {						//discards bad individuals and assigns the path cost
		
		int[] v;
		int r, c;
		long pathCost = 0;
		boolean valid;
		int i = 0;
		int end = l.size();
		
		while (i < end) {
			
			valid = true;
			pathCost = 0;
			v = l.get(i).getCromosome();
			
			c = v[0];
			if (matrix[0][c] == 0) valid = false;
			else pathCost += matrix[0][c];
			
			c = v[cities-1];
			if (matrix[0][c] == 0) valid = false;
			else pathCost += matrix[0][c];
			
			if (valid) {
			
				for (int j = 0; j < cities-1; j++) {
					
					r = v[j];
					c = v[j+1];
	
					if (matrix[r][c] == 0) {									
						valid = false;
						break;
					}
					
					else pathCost += matrix[r][c];
				}
				
			}

			if (valid) {
				l.get(i).setPathCost(pathCost);
				i++;
			}
			
			else {
				l.remove(i);
				end--;
				numpop--;
			}
		}
		
		if (l.isEmpty()) {
			System.out.println("THE POPULATION HAS BECOME EXTINT");
			System.exit(0);
		}
	}

	public void setProb(int[][] matrix) {								//probability of reproduction
		
		long totalPathCost = 0;
		long maxCost, minCost;
		double reproduction = 0.0;
		double prec = 0.0;
		long currentCost = 0;
		double averageCost = 0.0;
		
		for (int i = 0; i < l.size(); i++) {
			totalPathCost += l.get(i).getPathCost();
		}
		
		averageCost = totalPathCost / l.size();
		maxCost = worstIndividualP().getPathCost();
		minCost = bestIndividualP().getPathCost();
		
		for (int j = 0; j < l.size(); j++) {
			currentCost = l.get(j).getPathCost();
			reproduction = (1/(double)numpop) * (1 + coeff * ((averageCost - (double)currentCost)/(double)(maxCost - minCost)));
			reproduction += prec;
			l.get(j).setReproduction(reproduction);
			prec = reproduction;
		}
		
		l.get(l.size()-1).setReproduction(1.0);
		
		averagePathCost = (long) (averageCost);
		lowestPathCost = minCost;
	}

	public Individual bestIndividualP() {
		
		Individual bestIndividual = l.get(0);
		Individual temp;
		
		for (int i = 0; i < l.size(); i++) {
			
			temp = l.get(i);
			
			if (temp.getPathCost() < bestIndividual.getPathCost()) bestIndividual = temp;
			
		}
		
		return bestIndividual;
	}
	
	public Individual worstIndividualP() {
		
		Individual worstIndividual = l.get(0);
		Individual temp;
		
		for (int i = 0; i < l.size(); i++) {
			
			temp = l.get(i);
			
			if (temp.getPathCost() > worstIndividual.getPathCost()) worstIndividual = temp;
			
		}
		
		return worstIndividual;
	}
	
	public void addIndividuals(List<Individual> childs) {				//adds child to the new population
		
		for (int i = 0; i < childs.size(); i++) {
			l.add(child[i]);
			numpop++;
		}
		
	}
	
	public List<Individual> pairing() {											//chooses two parents based on their probability of reproduction
		
		List<Individual> parents = new ArrayList<Individual>();
		double rand;
		int i;
		
		for (int j = 0; j < 2; j++) {
			rand = Math.random();
			i = 0;
		
			while (rand > l.get(i).getReproduction()) {
				i++;
			}
		
			parents.add(l.get(i));
		
		}
		
		return parents;
		
	}
	
	public List<Individual> crossover(List<Individual> parents){				//generates two children by the crossover of the parents

		int cities = parents.get(0).getCromosome().length;
		Individual child1 = new Individual(cities);
		Individual child2 = new Individual(cities);
		
		int start = (int)(Math.random()*(cities-1));
		int end = (int)(Math.random()*(cities-2-start))+start+1;
		
		int[] tracep1 = parents.get(0).getCromosome();
		int[] tracep2 = parents.get(1).getCromosome();
		int[] tracec1 = new int[cities];
		int[] tracec2 = new int[cities];
		
		List<Integer> copy1 = new ArrayList<Integer>();
		List<Integer> copy2 = new ArrayList<Integer>();
		
		for (int i = start; i <= end; i++) {
			tracec1[i] = tracep1[i];
			copy1.add(tracep1[i]);
			tracec2[i] = tracep2[i];
			copy2.add(tracep2[i]);
		}
		
		int ci1 = 0;
		int ci2 = 0;
		
		for (int i = 0; i < cities; i++) {
			
			if (i == start) {
				if (end < cities-1) i = end+1;
				else break;
			}
			
			while (ci1 < cities && copy1.contains(tracep2[ci1])) {
				ci1++;
			}
			tracec1[i] = tracep2[ci1];
			ci1++;
			
			while (ci2 < cities && copy2.contains(tracep1[ci2])) {
				ci2++;
			}
			tracec2[i] = tracep1[ci2];
			ci2++;
		}
		
		child1.setCromosome(tracec1);
		child2.setCromosome(tracec2);
		List<Individual> childs = new ArrayList<Individual>();
		childs.add(child1);
		childs.add(child2);
		
		return childs;
		
	}
	
	public List<Individual> mutuation(List<Individual> childs, double mutprob){			//exchange the order visit
		
		double rand1;
		int gene, temp1, temp2;
		int cities = childs.get(0).getCromosome().length;
		int[] childtrace;
		
		for (int i = 0; i < 2; i++) {
			
			childtrace = childs.get(i).getCromosome();
			rand1 = Math.random();
			
			if (rand1 < mutprob) {
				gene = (int)(Math.random()*(cities-2));
				temp1 = childtrace[gene];
				temp2 = childtrace[gene+1];
				childs.get(i).setGene(temp2, gene);
				childs.get(i).setGene(temp1, gene+1);
			}
		}
	
		return childs;
		
	}

	public List<Individual> elite(List<Individual> parents, List<Individual> childs, double eliprob) {			//exchange the best parent with the worst child 
		
		Individual bestParent, worstChild;
		
		int indexWorstChild;
		
		if (parents.get(0).getPathCost() > parents.get(1).getPathCost()) bestParent = parents.get(1);

		else bestParent = parents.get(0);

		
		if (childs.get(0).getPathCost() > childs.get(1).getPathCost()) {
			worstChild = childs.get(0);
			indexWorstChild = 0;
		}
	
		else {
			worstChild = childs.get(1);
			indexWorstChild = 1;
		}
		
		double random = Math.random();
		
		if ((bestParent.getPathCost() < worstChild.getPathCost()) && random < eliprob) childs.set(indexWorstChild,bestParent);
		
		return childs;
	}

}
