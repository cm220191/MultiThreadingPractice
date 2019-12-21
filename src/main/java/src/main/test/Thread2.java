package src.main.test;

public class Thread2 extends Thread {
	
	int limit;
	SharedPrinter printer;
	
	public Thread2(int limit, SharedPrinter printer) {
		this.limit=limit;
		this.printer=printer;
	}

	@Override
	public void run() {
		int evenNumber = 2;
		while(evenNumber < limit) {
			printer.printEven(evenNumber);
			evenNumber=evenNumber+2;
		}
	}
}
