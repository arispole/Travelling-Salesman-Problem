import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

import org.jfree.ui.RefineryUtilities;

public class TSP {
	
	public static int cities;
	public static int matrix[][];

	public static void main(String[] args) throws IOException {
		
		long numpop, numgen, numchild;
		double incrate, mutprob, eliprob;
		readMatrix(); 																																	 
		numpop = 300;							// Population number
		numgen = 300;							// Generation number
		incrate = 0.005;						// Increase rate of the population
		mutprob = 0.001;						// Probability of mutation
		eliprob = 0.001;						// Probability of parent survival 
		double coeff = 2;						// Probability Distribution Coefficient [Range 0 to 2]
		numchild = numpop;
		List<Individual> p, c;
		
		Population solutions = new FirstPopulation(cities, numpop);								
		Individual bestIndividual = new Individual(cities);
		PathCostChart pathChart = new PathCostChart( "Lowest Path Cost vs Average Path Cost" , "");
		PopulationChart popChart = new PopulationChart("Evolution of population", "");
		
		for (int i = 0; i < numgen; i++) {
			
			System.out.println("Generation n° " + i);
			solutions.valid(matrix, cities);				
			System.out.println("Population number: " + solutions.getNumpop());
			popChart.addToDataset(solutions.getNumpop(), "Population", i);					
			if (solutions.bestIndividualP().getPathCost() < bestIndividual.getPathCost()) bestIndividual = solutions.bestIndividualP();								
			solutions.setProb(coeff);															
			pathChart.addToDataset(solutions.getAveragePathCost(), "Average Path Cost", i);
			pathChart.addToDataset(solutions.getLowestPathCost(), "Lowest Path Cost", i);
			Population solutions2 = new Population();
			numchild = (long) (numchild * (1 + incrate));
			
			for (int j = 0; j < (int)numchild/2; j++) {
				
				p = solutions.pairing();
				c = solutions.crossover(p);
				c = solutions.mutuation(c, mutprob);
				c = solutions.elite(p, c, eliprob);
				solutions2.addIndividuals(c);
				
			}
			
			solutions = solutions2;
			
		}
		
		System.out.println("");
		System.out.println("BEST PATH:");
		System.out.println("0");
		int[] bestcromosome = bestIndividual.getCromosome();
		for (int i = 0; i < bestcromosome.length; i++) {
			System.out.println(bestcromosome[i]);
		}
		System.out.println("0");
		System.out.println("");
		System.out.println("PATH COST: " + bestIndividual.getPathCost());
	
		pathChart.pack( );
		RefineryUtilities.positionFrameOnScreen(pathChart, 0.5 , 0.5);
		pathChart.setVisible( true );
		
		popChart.pack( );
		RefineryUtilities.positionFrameOnScreen(popChart, 0.5 , 0.9);
//		popChart.setVisible( true );
	}
	
	public static void readMatrix() throws IOException {															//reads the graph matrix from a file
		
		BufferedReader b=new BufferedReader(new InputStreamReader(new FileInputStream(new File("graph.txt"))));
		String s =b.readLine();
	
	    int r = Integer.parseInt(s);
	    cities = r-1;
	    matrix = new int[r][r];
	    
	    s=b.readLine();

	    for (int i = 0; s != null; i++) {
	   
	    	StringTokenizer st = new StringTokenizer(s," ");
	    	for (int j = 0; st.hasMoreTokens(); j++) {
	    		matrix[i][j] = Integer.parseInt(st.nextToken());
	    	}
	    	
	    	s=b.readLine();
	    }
	    
	}

}
