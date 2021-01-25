package sweetsPac;

public class BubbleGum extends Sweets {
	public String taste;

	public BubbleGum(String name, int weight, int price, String str) {
		super(name, weight, price);
		taste = str;
	}
	
	@Override
	public  String getinfo() {
		return " Taste: " + taste;
	}
	

}
