import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Random;
import java.lang.*;

public class ProbabilisticBagTest{
	private static List<String> alphabet = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"));
	private static Random random = new Random();
	private static int testSize = 100000;

	public static void main(String[] args){
		// Populate
		ProbabilisticBag pBag = new ProbabilisticBag();					
		Map<String, Double> letterWeights = new TreeMap<String,Double>();		// Maps Letters to their weights

		String letter;
		double weight;
		int totalWeight = 0;

		// Add letters to Probabilistic Bag and maps their weights
		for(int i = 0; i<alphabet.size(); i++){
			letter = alphabet.get(i);
			weight = i+1;
			pBag.add(new WeightedObj<String>(letter, (int)weight));
			letterWeights.put(letter, weight);
			totalWeight += weight;
		}	
		
		// Turns Letter Weights into expected proportions
		for(Map.Entry<String,Double> entry : letterWeights.entrySet()){ 
			entry.setValue(entry.getValue()/totalWeight); 
		}

		// Obtain weighted Alphabet
		System.out.println("Testing Observed Frequencies - Count: " + Integer.toString(testSize));
		Map<String, Integer> map = new TreeMap<String,Integer>();
		for(String l : alphabet){ map.put(l,0); }
		for(int i = 0; i<testSize; i++){
			letter = (String) pBag.next();
			map.put(letter, map.get(letter)+1);
		}

		// Check frequencies
		int freq;
		double error;
		for(Map.Entry<String,Integer> entry : map.entrySet()){
			letter = entry.getKey();
			freq = entry.getValue();
						
			error = Math.abs(letterWeights.get(letter) - ((double)freq/testSize))/letterWeights.get(letter);
			if(error>0.05){
				System.out.println("Warning: Invalid observed proportions for key " + letter);
				System.out.println("\tError: " + Double.toString(error));
				System.out.println("\tExpected: " + Double.toString(letterWeights.get(letter)) + ", Actual: " + Double.toString((double)freq/testSize));
			} else {

				System.out.println("\t" + letter + "\tExpected: " + Double.toString(letterWeights.get(letter)) + ", Actual: " + Double.toString((double)freq/testSize));
			}
		}

		// Delete - Should see more of the lower weighted objects
		System.out.print("DELETING - Should see more lower-weighted objects (beginning of alphabet)\n\t");
		for(int i = alphabet.size()-1; i>0; i--){
			pBag.delete(alphabet.get(i));
			System.out.print(pBag.next() + "->");
		}
		pBag.delete(alphabet.get(0));
		System.out.print(pBag.next() + "\n");
		pBag.delete(alphabet.get(0));				
	}
}
