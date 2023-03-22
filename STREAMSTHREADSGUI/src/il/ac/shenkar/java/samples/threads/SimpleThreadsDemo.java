package il.ac.shenkar.java.samples.threads;

public class SimpleThreadsDemo {
    public static void main(String[] args) {
        Runnable y1 = new Yarkan("mosh", "orange");
        Runnable y2 = new Yarkan("dave", "apples");
        Thread t1 = new Thread(y1);
        Thread t2 = new Thread(y2);
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    System.out.println("Water ... " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        for (int i = 0; i < 10; ++i) {
            System.out.println("carmel ... " + Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
