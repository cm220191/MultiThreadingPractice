import java.util.ArrayList;
import java.util.List;

public class PrintEvenOdd {

    public static void main(String[] args) throws InterruptedException {
        PrintNumber printer = new PrintNumber();
        PrintNumber printNumber2 = new PrintNumber();
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(new PrintOddNumber(printer)));
        threads.add(new Thread(new EvenNumberPrinter(printNumber2)));

        for(Thread t : threads){
            t.start();
        }
        for(Thread t : threads){
            t.join();
        }

    }


    private static class PrintNumber{
        private boolean isOdd= false;
        private boolean isEven=false;

        public void printNumber(int boundry){
            for(int num=0; num<=boundry; num++){
                if(num%2 == 0 && isEven){
                    System.out.println(num);
                }else if(isOdd){
                    System.out.println(num);
                }
            }
        }

        public void setOdd(boolean odd) {
            isOdd = odd;
        }

        public void setEven(boolean even) {
            isEven = even;
        }
    }

    private static class EvenNumberPrinter implements Runnable {
        private PrintNumber printer;

        public EvenNumberPrinter(PrintNumber printer) {
            this.printer = printer;
        }

        public void run() {
            printer.setEven(true);
            printer.printNumber(100);
        }
    }

    private static class PrintOddNumber implements Runnable{
        private PrintNumber printer;

        public PrintOddNumber(PrintNumber printer) {
            this.printer = printer;
        }
        public void run() {
            printer.setOdd(true);
            printer.printNumber(100);
        }
    }

}
