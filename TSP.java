import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class TSP {
	
	public static int cities;
	public static int matrix[][];

	public static void main(String[] args) throws IOException {
		
		long numpop, numgen;
		double incrate,mutprob, numchild, eliprob;
		readMatrix(); 																																	 
		numpop = 1000;
		numgen = 100;
		numchild = 0.0;
		incrate = 0.0;
		mutprob = 0.1;
		eliprob = 0.1;
		List<Individual> p, c;
		
		Population solutions = new FirstPopulation(cities, numpop);								
		Individual bestIndividual = new Individual(cities);
		FitnessChart fitChart = new FitnessChart( "Highest Fitness vs Average Fitness" , "");
		PopulationChart popChart = new PopulationChart("Evolution of population", "");
		
		for (int i = 0; i < numgen; i++) {
			
			solutions.valid(matrix, cities);													
			popChart.addToDataset(solutions.getSurvivors(), "Population", i);					
			bestIndividual = solutions.comparison(bestIndividual);								
			solutions.setProb(matrix);															
			fitChart.addToDataset(solutions.getAverageFitness(), "Average Fitness", i);
			fitChart.addToDataset(solutions.getHighestFitness(), "Highest Fitness", i);
			Population solutions2 = new Population();
			numchild = solutions.getNumpop() * (1 + incrate);
			
			for (int j = 0; j < (int)numchild/2; j++) {
				
				p = solutions.pairing();
				c = solutions.crossover(p);
				c = solutions.mutuation(c, mutprob);
				c = solutions.elite(p, c, eliprob);
				solutions2.addIndividuals(c);
				
			}
			
			solutions = solutions2;
			
			System.out.println("Generation " + i);
		}
		
		System.out.println("");
		System.out.println("BEST PATH:");
		int[] bestcromosome = bestIndividual.getCromosome();
		for (int i = 0; i < bestcromosome.length; i++) {
			System.out.println(bestcromosome[i]);
		}
		System.out.println("");
		System.out.print("COST:");
		System.out.println((int)(1/bestIndividual.getFitness()));
	
		fitChart.pack( );
		RefineryUtilities.positionFrameOnScreen(fitChart, 0.5 , 0.19);
		fitChart.setVisible( true );
		
		popChart.pack( );
		RefineryUtilities.positionFrameOnScreen(popChart, 0.5 , 0.81);
		popChart.setVisible( true );
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
