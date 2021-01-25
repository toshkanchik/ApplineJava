package sweetsPac;

public abstract class Sweets {
	public String name;
	public int weight;
	public int price;
	
	
	
	public Sweets(){
	}
	
	public Sweets(String name, int weight, int price){
		this.name = name;
		this.weight = weight;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	
	public int getPrice() {
		return price;
	}
	
	public abstract String getinfo();
}
