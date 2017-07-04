import java.util.Random;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;
/* Data Structure that takes weighted objects and returns them with a probability
proportional to their weight.
	Insertion - O(1)
	Access - O(1)
	Deletion - O(N) 
	
	REF - http://mishranam.blogspot.com/2015/04/randomized-probabilistic-bag-of-objects.html
*/
public class ProbabilisticBag {
	private Random random;
	private NavigableMap<Integer,WeightedObj> map = new TreeMap<Integer,WeightedObj>();
	private int total = 0;	

	public ProbabilisticBag(){
		this(new Random());
	}
	
	private ProbabilisticBag(Random random){
		this.random = random;
	}

	public void add(WeightedObj o){
		total += o.getWeight();	
		map.put(total,o);
	}
	
	public Object next(){
		if(map.size()==0){
			return null;
		}
		int value = (int) (random.nextDouble() * total);
		return map.ceilingEntry(value).getValue().getObject();
	}
	public void delete(Object o){
		Map.Entry<Integer,WeightedObj> entry;
		for(Iterator<Map.Entry<Integer,WeightedObj>> itr = map.entrySet().iterator(); itr.hasNext(); ){
			entry = itr.next();
			// Decrement total and adjust weights
			if(entry.getValue().getObject().equals(o)){
				int removedWeight = entry.getValue().getWeight();
				total -= removedWeight;

				while(itr.hasNext()){
					entry = itr.next();
					map.put(entry.getKey()-removedWeight, entry.getValue());
				}
			}
		}
		// Remove elemnt
		for(Iterator<Map.Entry<Integer,WeightedObj>> itr = map.entrySet().iterator(); itr.hasNext(); ){
                        entry = itr.next();
                        // Remove entry and decrement total
                        if(entry.getValue().getObject().equals(o)){
				itr.remove();
				break;
			}	
		}
	}
}
