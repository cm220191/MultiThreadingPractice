package src.main.test;

public class Main {
	
	
	public static void main(String[] args) {
		NumberPrinter printer = new  NumberPrinter();
	OddNumberPrinter oddNum = new OddNumberPrinter(100, printer);
	EvenNumberPrinter evenNum = new EvenNumberPrinter(100, printer);
	evenNum.start();
	oddNum.start();
	}

}
