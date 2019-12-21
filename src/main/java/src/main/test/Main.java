package src.main.test;

public class Main {
	
	
	public static void main(String[] args) {
		SharedPrinter printer = new  SharedPrinter();
	Thread1 oddNum = new Thread1(100, printer);
	Thread2 evenNum = new Thread2(100, printer);
	
	oddNum.start();
	evenNum.start();
	
	
	}

}
