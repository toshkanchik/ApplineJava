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
		
		box1.addItem(new Iriska("qwer",1,6,3));
		box1.addItem(new Iriska("asdf",6,1,3));
		box1.addItem(new Iriska("zxcv",3,4,3));
		box1.addItem(new Iriska("zaq",2,5,3));
		box1.addItem(new Iriska("xsw",5,2,3));
		box1.addItem(new Iriska("cde",4,3,3));
		box1.printInfo();
		box1.printWeight();
//		box1.removeToWeightByPrice(16);//test for additional task
//		box1.removeToWeightByWeight(16);//test for additional task
		box1.removeToWeight(17, false);//test for additional task
		box1.printInfo();
		box1.printWeight();
	}

}
