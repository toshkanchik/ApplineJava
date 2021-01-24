package HomeWork_1;

public class SweetsBox implements PresentInt {
	private Sweets[] arr = new Sweets[50];
	private int currIndex = 0;
	
	public SweetsBox () {
		
	}

	@Override
	public boolean addItem(Sweets toAdd) {
		if (currIndex >= 50) {
			return false;
			}
		else {
			arr[currIndex] = toAdd;
			currIndex ++;
			return true;
		}
	}

	@Override
	public boolean removeItem(int i) {
		if (currIndex == 0||currIndex <= i||i < 0) { //if the box is empty or we are trying to delete an item that is not in the box
			return false;
		}
		else { //transfer the last index item into the place of removed one, currIndex--
			currIndex --;
			arr[i] = arr[currIndex];
			return true;
		}
	}

	@Override
	public boolean removeItem() {
		if (currIndex == 0) {
			return false;
		}
		else {
			currIndex --;
			return true;
		}
	}

	@Override
	public void printInfo() {
		for(int i = 0; i < currIndex; i++) {
			System.out.println("Item nom: " + i + " name: " + arr[i].getName() + " price: " + arr[i].getPrice() + " weight: " + arr[i].getWeight() + arr[i].getinfo());
		}
		System.out.println("___________");
	}

	@Override
	public void printWeight() {
		int total = 0;
		for(int i = 0; i < currIndex; i++) {
			total += arr[i].getWeight();
		}
		System.out.println("Total weight is:" + total);
	}

	@Override
	public void printPrice() {
		int total = 0;
		for(int i = 0; i < currIndex; i++) {
			total += arr[i].getPrice();
		}
		System.out.println("Total price is:" + total);
	}
}
