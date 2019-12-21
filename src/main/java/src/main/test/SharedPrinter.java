package src.main.test;

public class SharedPrinter {
	
	boolean isEvenPrinted = true;
	
	synchronized void printOdd(int number) {
		while(! isEvenPrinted) {
			try {
				wait();
			}catch(Exception e) {
				System.out.println(Thread.currentThread().getName() +" intrupped !");
			}
		}
			
			System.out.println(Thread.currentThread().getName() + " : "+ number);
			isEvenPrinted= false;
			
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				System.out.println(Thread.currentThread().getName() +" intrupped !");
			}
			notify();
	}
	synchronized void printEven(int number) {
		while(isEvenPrinted) {
			try {
				wait();
			}catch(Exception e) {
				System.out.println(Thread.currentThread().getName() +" intrupped !");
			}
		}
			System.out.println(Thread.currentThread().getName() + " : "+ number);
			isEvenPrinted= true;
			
			try {
				Thread.sleep(1000);
				
			}catch(Exception e) {
				System.out.println(Thread.currentThread().getName() +" intrupped !");
			}
			notify();
		
	}

}
