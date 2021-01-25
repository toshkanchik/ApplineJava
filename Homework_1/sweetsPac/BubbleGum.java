package HomeWork_1;

public class BubbleGum extends Sweets {
	public String taste;

	public BubbleGum() {
		super();
		taste = new String("default taste");
	}

	public BubbleGum(String name, int weight, int price) {
		super(name, weight, price);
		taste = new String("default taste");
	}
	
	public BubbleGum(String name, int weight, int price, String str) {
		super(name, weight, price);
		taste = str;
	}
	
	@Override
	public  String getinfo() {
		String str = new String(" Taste: " + taste);
		return str;
	}
	

}
