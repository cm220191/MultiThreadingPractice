package src.main.test;

public class EvenNumberPrinter extends Thread {
	
	int limit;
	NumberPrinter printer;
	
	public EvenNumberPrinter(int limit, NumberPrinter printer) {
		this.limit=limit;
		this.printer=printer;
	}

	@Override
	public void run() {
		int evenNumber = 2;
		while(evenNumber < limit) {
			printer.printEvenNumber(evenNumber);
			evenNumber=evenNumber+2;
		}
	}
}
