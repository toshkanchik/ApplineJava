package HomeWork_1;

public class Sweets {
	public String name;
	public int weight;
	public int price;
	
	
	
	public Sweets(){
		name = "Default";
		weight = -1;
		price = -1;
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
	
	public  String getinfo() {
		//define this method in child classes to get additional info
		return null;	
	}
}
