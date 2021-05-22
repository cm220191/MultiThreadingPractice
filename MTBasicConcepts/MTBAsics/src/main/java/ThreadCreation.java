import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadCreation {
//Create Bank vault password protected,
// 2 thief thread to guess pass and and police thread which will arrive after 10 sec

    static int MAX_PASSWORD= 999;
    public static void main(String[] args) {

        BankVault vault = new BankVault(new Random().nextInt(MAX_PASSWORD));
        List<Thread> threads = new ArrayList<Thread>();
        threads.add(new AscendingThread(vault));
        threads.add(new DescendingThread(vault));
        threads.add(new PoliceThread());

        for(Thread th : threads){
            th.start();
        }

    }

    public static class BankVault{
        private int password;

        public BankVault(int password){
            System.out.println("Setting vault password"+password);
            this.password= password;
        }

        public boolean unlockVault(int guessPassword){
            try {
                Thread.sleep(10);

           } catch (InterruptedException e) {
               e.printStackTrace();
            }
            return this.password == guessPassword;
        }

    }


    public static abstract class HackerThread extends Thread{
        public BankVault vault;

        public HackerThread(BankVault vault){
            this.vault=vault;
            this.setName(this.getClass().getSimpleName());
            //this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread :"+this.getName());
            super.start();
        }
    }

    public static class AscendingThread extends HackerThread{

        public AscendingThread(BankVault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int i=0; i<=MAX_PASSWORD; i++) {
                 if(vault.unlockVault(i)){
                    System.out.println("Vault unlocked by "+this.getName());
                    System.exit(0);
                }
            }
        }
    }

    public static class DescendingThread extends HackerThread{

        public DescendingThread(BankVault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int i=MAX_PASSWORD; i>0; i--) {
               if(vault.unlockVault(i)){
                    System.out.println("Vault unlocked by "+this.getName());
                    System.exit(0);
                }
            }
        }
    }

    public static class PoliceThread extends Thread{

        @Override
        public void run(){
            for(int i=10; i >0; i--){
                try {
                    Thread.sleep(1000);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Police arrived, hackers Busted!");
            System.exit(0);
        }
    }
}
