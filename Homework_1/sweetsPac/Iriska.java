package HomeWork_1;

public class Iriska extends Sweets {

	public float timeToChew;
	
	public Iriska() {
		super();
		this.timeToChew = 0;
	}

	public Iriska(String name, int weight, int price) {
		super(name, weight, price);
		this.timeToChew = 0;
	}
	public Iriska(String name, int weight, int price, float timeToChew) {
		super(name, weight, price);
		this.timeToChew = timeToChew;
	}
	
	@Override
	public  String getinfo() {
		String str = new String(" Time to chew: " + timeToChew);
		return str;
	}

}
