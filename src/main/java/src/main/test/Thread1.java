package src.main.test;

public class Thread1 extends Thread {
	
	int limit;
	SharedPrinter printer;
	
	public Thread1(int limit, SharedPrinter printer) {
		this.limit=limit;
		this.printer=printer;
	}
	
	@Override
	public void run() {
		int oddNumber=1;
		while(oddNumber<limit) {
			printer.printOdd(oddNumber);
			oddNumber=oddNumber+2;
		}
		
	}

}
