import java.util.Random;

public class FirstPopulation extends Population {

	public FirstPopulation(int cities, long numpop) {
		
		super();
		
		this.numpop = numpop;
		Individual a;
		int[] trace;
		
		int[] temp = new int[cities];
		for (int j = 0; j < cities; j++) {
			temp[j] = j+1;
		}
		
		for (int i = 0; i < numpop; i++) {				
			a = new Individual(cities);
			trace = temp.clone();
			FirstPopulation.shuffle(trace);
			a.setCromosome(trace);
			l.add(a);
		} 
		
	}

	public static void shuffle(int[] array) { 					// mix-up the array
		
		Random rand = new Random();
	    for (int i = array.length - 1; i > 0; --i) {
	        int j = rand.nextInt(i + 1);
	        int temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	    }
	    
	}

}