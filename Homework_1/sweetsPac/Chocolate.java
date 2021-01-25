package sweetsPac;

public class Chocolate extends Sweets {

	public String filling;
	
	public Chocolate(String name, int weight, int price, String str) {
		super(name, weight, price);
		filling = str;
	}
	
	@Override
	public  String getinfo() {
		return " Filling: " + filling;
	}
	

}
