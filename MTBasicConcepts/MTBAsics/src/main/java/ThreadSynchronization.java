import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ThreadSynchronization {

    public static void main(String[] args) {
        BigInteger result;

        PowerCalculator thread1 = new PowerCalculator(121, 11);
        PowerCalculator thread2 = new PowerCalculator(512, 12);

        List<Thread> threads = new ArrayList<Thread>();
        threads.add(thread1);
        threads.add(thread2);
        for(Thread thread: threads){
            thread.start();
        }

        try{
            for(Thread thread: threads){
                thread.join();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        result = thread1.getResult().add( thread2.getResult());
        System.out.println("Total result :"+result);

    }

    public static class PowerCalculator extends Thread{
        private int base;
        private int power;
        private BigInteger result=BigInteger.ONE;

        public PowerCalculator(int base, int power){
            this.base=base;
            this.power=power;
        }

        @Override
        public void run(){
            BigInteger bigBase = BigInteger.valueOf(base);
            for(int i=power; i>0; i--){
                result= result.multiply(bigBase);
            }
            System.out.println("Calculated power of  :"+base +"to power :"+power+" is ->"+result.toString());
        }

        public BigInteger getResult(){
            return this.result;
        }

    }

}
