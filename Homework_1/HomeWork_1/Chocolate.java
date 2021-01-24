package HomeWork_1;

public class Chocolate extends Sweets {

	public String filling;
	
	public Chocolate() {
		super();
		filling = new String("default filling");
	}

	public Chocolate(String name, int weight, int price) {
		super(name, weight, price);
		filling = new String("default filling");
	}
	
	public Chocolate(String name, int weight, int price, String str) {
		super(name, weight, price);
		filling = str;
	}
	
	@Override
	public  String getinfo() {
		String str = new String(" Filling: " + filling);
		return str;
	}
	

}
