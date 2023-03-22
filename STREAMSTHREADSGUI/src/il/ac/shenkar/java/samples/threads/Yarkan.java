package il.ac.shenkar.java.samples.threads;

public class Yarkan implements Runnable{

    private String nickname;
    private String product;

    public Yarkan(String nickname, String product) {
        this.nickname = nickname;
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println(this.nickname +
                    " sells " +
                    this.product +
                    " ..." +
                    Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
