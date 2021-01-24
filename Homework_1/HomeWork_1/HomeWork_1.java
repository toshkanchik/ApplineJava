package HomeWork_1;

public class HomeWork_1 {

	public static void main(String[] args) {
		SweetsBox box1 = new SweetsBox();
		Sweets sweet1 = new Iriska("qwer",1,2);
		box1.addItem(sweet1);
		sweet1 = new BubbleGum("asdf",3,4);
		box1.addItem(sweet1);
		sweet1 = new Chocolate("zxcv",5,6);
		box1.addItem(sweet1);
		box1.printInfo(); //test addItem + print
		
		
		box1.removeItem(10);
		box1.printInfo(); //test remove index out of bounds
		
		box1.removeItem(1);
		box1.printInfo(); //test remove index
		
		box1.removeItem();
		box1.printInfo(); //test remove last
		
		sweet1 = new BubbleGum("zaq",7,8,"mint");
		box1.addItem(sweet1);
		box1.printInfo();
		box1.printPrice();
		box1.printWeight(); //test print total
		
		box1.removeItem();
		box1.removeItem();
		box1.removeItem();
		box1.printInfo(); //test remove from empty box
	}

}
