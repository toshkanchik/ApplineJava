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
	
	public int getTotalPrice() {
		int total = 0;
		for(int i = 0; i < currIndex; i++) {
			total += arr[i].getPrice();
		}
		return total; 
	}
	
	public int getTotalWeight() {
		int total = 0;
		for(int i = 0; i < currIndex; i++) {
			total += arr[i].getWeight();
		}
		return total; 
	}

	@Override
	public void printWeight() {
//		int total = 0;
//		for(int i = 0; i < currIndex; i++) {
//			total += arr[i].getWeight();
//		}
		System.out.println("Total weight is:" + getTotalWeight());
	}

	@Override
	public void printPrice() {
//		int total = 0;
//		for(int i = 0; i < currIndex; i++) {
//			total += arr[i].getPrice();
//		}
		System.out.println("Total price is:" + getTotalPrice());
	}
	
	private int getMinPriceIndex() {//gets index of item with lowest price. -1 in case of error
		int minIndex = -1;
		int minPrice = Integer.MAX_VALUE;
		for(int i = 0; i < currIndex; i++) {
			if(arr[i].getPrice() <= minPrice) {
				minIndex = i;
				minPrice = arr[i].getPrice(); 
			}
		}
		return minIndex;
	}
	
	private int getMinWeightIndex() {//gets index of item with lowest weight. -1 in case of error
		int minIndex = -1;
		int minWeight = Integer.MAX_VALUE;
		for(int i = 0; i < currIndex; i++) {
			if(arr[i].getWeight() <= minWeight) {
				minIndex = i;
				minWeight = arr[i].getWeight(); 
			}
		}
		return minIndex;
	}
	
	public int removeToWeightByPrice(int targetWeight) {//returns -1 if removing is not possible, returns new total weight otherwise (can remove all items if targetWeight is too low)
		int total = this.getTotalWeight();
		if (targetWeight >= total) {
			return total;
		}
		else if ((targetWeight <= 0) || (currIndex == 0)) {
			return -1;
		}
		else {
			int remove = getMinPriceIndex();
			while ((remove >= 0) && (getTotalWeight() > targetWeight)) {
				this.removeItem(remove);
				remove = getMinPriceIndex();
			}
			return getTotalPrice();
		}
	}
	public int removeToWeightByWeight(int targetWeight) {//returns -1 if removing is not possible, returns new total weight otherwise (can remove all items if targetWeight is too low)
		int total = this.getTotalWeight();
		if (targetWeight >= total) {
			return total;
		}
		else if ((targetWeight <= 0) || (currIndex == 0)) {
			return -1;
		}
		else {
			int remove = getMinWeightIndex();
			while ((remove >= 0) && (getTotalWeight() > targetWeight)) {
				this.removeItem(remove);
				remove = getMinWeightIndex();
			}
			return getTotalPrice();
		}
	}
	
	public int removeToWeight(int targetWeight, boolean usePrice) {//This is only one method, but it is more rational use it instead of two above
		int total = this.getTotalWeight();
		if (targetWeight >= total) {
			return total;
		}
		else if ((targetWeight <= 0) || (currIndex == 0)) {
			return -1;
		}
		else {
			int remove = (usePrice) ? getMinPriceIndex() : getMinWeightIndex();
			while ((remove >= 0) && (getTotalWeight() > targetWeight)) {
				this.removeItem(remove);
				remove = (usePrice) ? getMinPriceIndex() : getMinWeightIndex();
			}
			return getTotalPrice();
		}
	}
}
