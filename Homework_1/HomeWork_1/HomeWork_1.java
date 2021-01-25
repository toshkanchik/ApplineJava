package HomeWork_1;

import boxes.SweetsBox;
import sweetsPac.*;

public class HomeWork_1 {

	public static void main(String[] args) {
		SweetsBox boxInstance = new SweetsBox();
		boxInstance.addItem(new Iriska("qwer",1,2,5));
		boxInstance.addItem(new BubbleGum("asdf",3,4,"mint"));
		boxInstance.addItem(new Chocolate("zxcv",5,6,"vanilla"));
		System.out.println("test addItem + print:");
		boxInstance.printInfo(); //test addItem + print
		
		
		//boxInstance.removeItem(10);
		System.out.println("test remove index out of bounds: " + boxInstance.removeItem(10));
		boxInstance.printInfo(); //test remove index out of bounds
		
//		boxInstance.removeItem(1);
		System.out.println("test remove index 1: " + boxInstance.removeItem(1));
		boxInstance.printInfo(); //test remove index
		
//		boxInstance.removeItem();
		System.out.println("test remove last: " + boxInstance.removeItem());
		boxInstance.printInfo(); //test remove last
		
		boxInstance.removeItem();
		boxInstance.removeItem();
		boxInstance.removeItem();
		System.out.println("test remove from empty box: " + boxInstance.removeItem());
		boxInstance.printInfo(); //test remove from empty box
		
		boxInstance.addItem(new Iriska("qwer",1,6,3));
		boxInstance.addItem(new Iriska("asdf",6,1,3));
		boxInstance.addItem(new Iriska("zxcv",3,4,3));
		boxInstance.addItem(new Iriska("zaq",2,5,3));
		boxInstance.addItem(new Iriska("xsw",5,2,3));
		boxInstance.addItem(new Iriska("cde",4,3,3));
		System.out.println("test for additional task");
		boxInstance.printInfo();
//		boxInstance.removeToWeightByPrice(16);//test for additional task
//		boxInstance.removeToWeightByWeight(16);//test for additional task
		boxInstance.removeToWeight(17, false);//test for additional task
		boxInstance.printInfo();
	}

}
