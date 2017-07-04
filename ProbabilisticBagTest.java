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
		Map<String, Double> probMap = new TreeMap<String,Double>();

		String letter;
		double weight;
		int totalWeight = 0;
		for(int i = 0; i<alphabet.size(); i++){
			letter = alphabet.get(i);
			weight = i+1;
			pBag.add(new WeightedObj<String>(letter, (int)weight));
			probMap.put(letter, weight);
			totalWeight += weight;
		}	
		
		// Probability Map Check
		for(Map.Entry<String,Double> entry : probMap.entrySet()){ entry.setValue(entry.getValue()/totalWeight); }

		// Obtain weighted Alphabet
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
						
			error = Math.abs(probMap.get(letter) - ((double)freq/testSize))/probMap.get(letter);
			if(error>0.05){
				System.out.println("Warning: Invalid observed proportions for key " + letter);
				System.out.println("\tError: " + Double.toString(error));
				System.out.println("\tExpected: " + Double.toString(probMap.get(letter)) + ", Actual: " + Double.toString((double)freq/testSize));
			}
		}

		// Delete - Should see more of the lower weighted objects
		for(int i = alphabet.size()-1; i>0; i--){
			pBag.delete(alphabet.get(i));
			System.out.println(pBag.next());
		}
		pBag.delete(alphabet.get(0));				
	}
}
