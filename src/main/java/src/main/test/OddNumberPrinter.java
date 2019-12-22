package src.main.test;

public class OddNumberPrinter extends Thread {
	
	int limit;
	NumberPrinter printer;
	
	public OddNumberPrinter(int limit, NumberPrinter printer) {
		this.limit=limit;
		this.printer=printer;
	}
	
	@Override
	public void run() {
		int oddNumber=1;
		while(oddNumber<limit) {
			printer.printOddNumber(oddNumber);
			oddNumber=oddNumber+2;
		}
		
	}

}
