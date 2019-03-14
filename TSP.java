import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TSP {
	
	public static int cities;
	public static int matrix[][];

	public static void main(String[] args) throws IOException {
		
		int numpop, numgen;
		readMatrix(); 																	//reads the graph matrix from a file														 
		numpop = 100;
		numgen = 1000;
		
		Population solutions = new Population(cities, numpop);							//initial generation
		Individual bestIndividual = solutions.getList().get(0);
		bestIndividual = solutions.comparison(bestIndividual);
		
		for (int i = 0; i < numgen; i++) {
			solutions.valid(matrix, cities);											//discards bad individuals and assigns the fitness
			bestIndividual = solutions.comparison(bestIndividual);						//keeps track of the best solution
			solutions.setProb(matrix);													//probability of reproduction
			
			solutions.pairing();														
		}
	}
	
	public static void readMatrix() throws IOException {
		
		FileReader f;
	    f=new FileReader("graph.txt");

	    BufferedReader b;
	    b=new BufferedReader(f);

	    String s;
	    
	    s=b.readLine();
	    String[] dim = s.split(" ");
	    int r = Integer.parseInt(dim[0]);
	    int c = Integer.parseInt(dim[1]);
	    cities = r;
	    matrix = new int[r][c];

	    for (int i = 0; s != null; i++) {
	      s=b.readLine();
	      String[] a = s.split(" ");
	      for (int j = 0; j < a.length; j++) {
	    	  matrix[i][j] = Integer.parseInt(a[j]);
	      }
	    }
	}
	
	

}
