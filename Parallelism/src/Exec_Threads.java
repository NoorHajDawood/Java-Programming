import java.util.Random;
public class Exec_Threads implements Runnable {
    Random rand = new Random();
    double N = 1000;     int range = 10;
    Counter counter;

    public Exec_Threads(Counter c) {
        counter = c;
    }

    public void run() {
        int tid = ThreadID.get();
        //System.out.println("**** Thread ID: " + tid + " started ****");
        for (int i = 0; i < 10000; i++) {
            if (tid % 2 == 0) {
                counter.inc();
            } else {
                counter.dec();
            }
            Thread.yield();
        }
    }

}