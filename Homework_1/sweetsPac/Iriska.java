package sweetsPac;

public class Iriska extends Sweets {

	public float timeToChew;

	public Iriska(String name, int weight, int price, float timeToChew) {
		super(name, weight, price);
		this.timeToChew = timeToChew;
	}
	
	@Override
	public  String getinfo() {
		return " Time to chew: " + timeToChew;
	}

}
