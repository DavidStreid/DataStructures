public class WeightedObj<O>{
	private O object;
	private int weight;

	public WeightedObj(O object, int weight){
		this.object = object;
		this.weight = weight;
	}

	public O getObject(){
		return object;
	}

	public int getWeight(){
		return weight;
	}
}
