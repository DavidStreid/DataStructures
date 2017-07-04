import java.util.Random;
import java.util.NavigableMap;
import java.util.TreeMap;

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
		int value = (int) (random.nextDouble() * total);
		return map.ceilingEntry(value).getValue().getObject();
	}
}
